/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Repository;


import br.com.comexport.lancamentoscontabeis.model.EstatisticasModel;
import br.com.comexport.lancamentoscontabeis.model.LancamentoContabilModel;
import br.com.comexport.lancamentoscontabeis.model.ObjectIdModel;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
@Repository
public class LancamentosContabeisRepository {

	private static Map<String, LancamentoContabilModel> repository = new HashMap<>();

	public ObjectIdModel cadastrarLancamento(LancamentoContabilModel lancamento) {
		lancamento.setId(gerarChave());
		repository.put(lancamento.getId(), lancamento);
		return new ObjectIdModel(lancamento.getId());
	}

	public LancamentoContabilModel buscarLancamentoPorId(String id) {
		return repository.get(id);
	}

	public List<LancamentoContabilModel> buscarLancamentosContabeisPorConta(Integer contaContabil) {
		return repository.entrySet().stream()
				.map(m -> m.getValue())
				.filter(lc -> lc.getContaContabil() == contaContabil)
				.collect(Collectors.toList());
	}
	
	public EstatisticasModel estatisticas(Integer contaContabil) {
//		List<Integer> lancamentos = repository.entrySet().stream()
//			.map(Map.Entry::getValue)
//			.map(LancamentoContabilModel::getContaContabil)
//			.filter(l -> l == contaContabil)
//			.collect(Collectors.toList());
		

		EstatisticasModel stats = new EstatisticasModel();
		
		stats.setQtde(repository.size());
		
		stats.setSoma(repository.entrySet().stream()
			.map(Map.Entry::getValue)
			.filter(l-> {
				if (Objects.nonNull(contaContabil) && l.getContaContabil() == contaContabil) {
					return true;
				}
				return false;
			})
			.map(LancamentoContabilModel::getValor)
			.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		stats.setMax(repository.entrySet()
	        .stream()
	        .max(Map.Entry.comparingByValue(LancamentoContabilModel::compareTo))
	        .get().getValue().getValor());
		
		stats.setMin(repository.entrySet()
	        .stream()
	        .min(Map.Entry.comparingByValue(LancamentoContabilModel::compareTo))
	        .get().getValue().getValor());
		
		stats.setMedia(BigDecimal.valueOf(repository.entrySet()
	        .stream()
	        .map(Map.Entry::getValue)
	        .map(LancamentoContabilModel::getValor)
	        .mapToDouble(BigDecimal::doubleValue)
	        .average()
	        .getAsDouble()));
        
		return stats;
	}
	
	private String gerarChave() {
		int tamanho = 36;
		boolean usarLetras = true;
		boolean usarNumeros = true;
		return RandomStringUtils.random(tamanho, usarLetras, usarNumeros);

	}


}
