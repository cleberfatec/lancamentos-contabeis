/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.comexport.lancamentoscontabeis.model.EstatisticasModel;
import br.com.comexport.lancamentoscontabeis.model.LancamentoContabilModel;
import br.com.comexport.lancamentoscontabeis.model.ObjectIdModel;
import br.com.comexport.lancamentoscontabeis.util.Utils;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
@Repository
public class LancamentosContabeisRepository {

	private static Map<String, LancamentoContabilModel> repository = new HashMap<>();

	public ObjectIdModel cadastrarLancamento(LancamentoContabilModel lancamento) {
		lancamento.setId(Utils.gerarChave());
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
		
		EstatisticasModel stats = new EstatisticasModel();
		stats.setQtde(calcularQtde(contaContabil));
		stats.setSoma(calcularSoma(contaContabil));
		stats.setMax(calcularMax(contaContabil));
		stats.setMin(calcularMin(contaContabil));
		stats.setMedia(calcularMedia(contaContabil));
        
		return stats;
	}

	private int calcularQtde(Integer contaContabil) {
		return Objects.nonNull(contaContabil)
				? (int) repository.entrySet().stream().map(Map.Entry::getValue)
						.filter(filtroContaContabil(contaContabil)).count()
				: repository.size();
	}

	private BigDecimal calcularSoma(Integer contaContabil) {
		return Objects.nonNull(contaContabil)
				? repository.entrySet().stream()
					.map(Map.Entry::getValue)
					.filter(filtroContaContabil(contaContabil))
					.map(LancamentoContabilModel::getValor)
					.reduce(BigDecimal.ZERO, BigDecimal::add)
				: repository.entrySet().stream()
					.map(Map.Entry::getValue)			
					.map(LancamentoContabilModel::getValor)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		
	}

	private BigDecimal calcularMax(Integer contaContabil) {
		return Objects.nonNull(contaContabil)
				? repository.entrySet()
			        .stream()
			        .map(Map.Entry::getValue)
			        .filter(filtroContaContabil(contaContabil))
			        .max(LancamentoContabilModel::compareTo)
			        .get().getValor()
			     : repository.entrySet()
			        .stream()
			        .map(Map.Entry::getValue)
			        .max(LancamentoContabilModel::compareTo)
			        .get().getValor();
	}

	private BigDecimal calcularMin(Integer contaContabil) {
		return Objects.nonNull(contaContabil)
				? repository.entrySet()
			        .stream()
			        .map(Map.Entry::getValue)
			        .filter(filtroContaContabil(contaContabil))
			        .min(LancamentoContabilModel::compareTo)
			        .get().getValor()
		        : repository.entrySet()
			        .stream()
			        .map(Map.Entry::getValue)
			        .min(LancamentoContabilModel::compareTo)
			        .get().getValor();
	}

	private BigDecimal calcularMedia(Integer contaContabil) {
		return Objects.nonNull(contaContabil)
				? BigDecimal.valueOf(
						repository.entrySet().stream()
							.map(Map.Entry::getValue)
							.filter(filtroContaContabil(contaContabil))
							.map(LancamentoContabilModel::getValor)
							.mapToDouble(BigDecimal::doubleValue)
							.average()
							.getAsDouble())
				: BigDecimal.valueOf(
						repository.entrySet().stream()
							.map(Map.Entry::getValue)
							.map(LancamentoContabilModel::getValor)
							.mapToDouble(BigDecimal::doubleValue)
							.average()
							.getAsDouble());	
	}
	
	public static Predicate<LancamentoContabilModel> filtroContaContabil(Integer contaContabil) {		
		return l -> l.getContaContabil() == contaContabil;
	}
	

}
