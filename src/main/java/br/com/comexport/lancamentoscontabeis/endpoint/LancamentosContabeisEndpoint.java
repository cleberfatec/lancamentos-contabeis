/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.comexport.lancamentoscontabeis.model.LancamentoContabilModel;
import br.com.comexport.lancamentoscontabeis.model.ObjectIdModel;
import br.com.comexport.lancamentoscontabeis.model.EstatisticasModel;
import br.com.comexport.lancamentoscontabeis.repository.LancamentosContabeisRepository;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
@RestController
@RequestMapping(value = "/lancamentos-contabeis")
public class LancamentosContabeisEndpoint {

	@Autowired
	private LancamentosContabeisRepository repository;

	@PostMapping(value = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ObjectIdModel cadastrarLancamentosContabeis(@RequestBody LancamentoContabilModel requestBody) {

		return repository.cadastrarLancamento(requestBody);
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public LancamentoContabilModel buscarLancamentoContabilPorId(@PathVariable(value = "id") String id) {

		return repository.buscarLancamentoPorId(id);
	}

	@GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<LancamentoContabilModel> buscarLancamentosContabeisPorConta(
			@RequestParam(value = "contaContabil") Integer contaContabil) {
		return repository.buscarLancamentosContabeisPorConta(contaContabil);
	}

	@GetMapping(value = "/_stats", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public EstatisticasModel buscarEstatisticas(@RequestParam(value = "contaContabil", required=false) Integer contaContabil) {
		return repository.estatisticas(contaContabil);
	}
}
