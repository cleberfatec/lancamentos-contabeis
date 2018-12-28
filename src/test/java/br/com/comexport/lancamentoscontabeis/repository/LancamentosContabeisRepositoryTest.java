/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.comexport.lancamentoscontabeis.model.EstatisticasModel;
import br.com.comexport.lancamentoscontabeis.model.LancamentoContabilModel;
import br.com.comexport.lancamentoscontabeis.model.ObjectIdModel;

/** 
 * @author Cleber de Araujo
 * @date 28 de dez de 2018
 *
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LancamentosContabeisRepositoryTest {

	private LancamentosContabeisRepository repo;
	private LancamentoContabilModel lanc1;
	private LancamentoContabilModel lanc2;
	private LancamentoContabilModel lanc3;
	private ObjectIdModel ret1;
	private ObjectIdModel ret2;
	private ObjectIdModel ret3;

	@Before
	public void setUp() throws Exception {
		repo = new LancamentosContabeisRepository();		
		lanc1 = new LancamentoContabilModel();
		lanc1.setContaContabil(123);
		lanc1.setData("20181227");
		lanc1.setValor(new BigDecimal(100));
		
		lanc2 = new LancamentoContabilModel();
		lanc2.setContaContabil(123);
		lanc2.setData("20181227");
		lanc2.setValor(new BigDecimal(50));
		
		lanc3 = new LancamentoContabilModel();
		lanc3.setContaContabil(2);
		lanc3.setData("20181227");
		lanc3.setValor(new BigDecimal(60));
				
	}	

	@Test
	public void aCadastrarLancamento_e_buscarPorId() {		
		ret1 = repo.cadastrarLancamento(lanc1);
		ret2 = repo.cadastrarLancamento(lanc2);
		ret3 = repo.cadastrarLancamento(lanc3);		
		
		assertNotNull(ret1.getId());
		assertEquals(36, ret1.getId().length());
		
		assertNotNull(ret2.getId());
		assertEquals(36, ret2.getId().length());
		
		assertNotNull(ret3.getId());
		assertEquals(36, ret3.getId().length());
		
		bBuscarLancamentoPorId();
	}

	public void bBuscarLancamentoPorId() {
		LancamentoContabilModel lancamento1 = repo.buscarLancamentoPorId(lanc1.getId());
		LancamentoContabilModel lancamento2 = repo.buscarLancamentoPorId(lanc2.getId());
		LancamentoContabilModel lancamento3 = repo.buscarLancamentoPorId(lanc3.getId());
		
		assertNotNull(lancamento1);
		assertEquals(0, this.lanc1.getValor().compareTo(lancamento1.getValor()));
		
		assertNotNull(lancamento2);
		assertEquals(0, this.lanc2.getValor().compareTo(lancamento2.getValor()));
		
		assertNotNull(lancamento3);
		assertEquals(0, this.lanc3.getValor().compareTo(lancamento3.getValor()));
	}

	@Test
	public void cBuscarLancamentosContabeisPorConta() {
		List<LancamentoContabilModel> ret1 = repo.buscarLancamentosContabeisPorConta(lanc1.getContaContabil());
		List<LancamentoContabilModel> ret2 = repo.buscarLancamentosContabeisPorConta(lanc2.getContaContabil());
		List<LancamentoContabilModel> ret3 = repo.buscarLancamentosContabeisPorConta(lanc3.getContaContabil());
		assertEquals(2, ret1.size());
		assertEquals(2, ret2.size());
		assertEquals(1, ret3.size());
	}

	@Test
	public void dEstatisticas_com_contaContabil() {
		EstatisticasModel estatisticas = repo.estatisticas(lanc1.getContaContabil());
		assertEquals(BigDecimal.valueOf(100), estatisticas.getMax());
		assertEquals(BigDecimal.valueOf(50), estatisticas.getMin());
		assertEquals(BigDecimal.valueOf(75.0), estatisticas.getMedia()); // (100 + 50)/2 = 75
		assertEquals(BigDecimal.valueOf(150), estatisticas.getSoma());
		assertEquals(2, estatisticas.getQtde().intValue());
	}	
	
	@Test
	public void eEstatisticas_sem_contaContabil() {
		EstatisticasModel estatisticas = repo.estatisticas(null);
		assertEquals(BigDecimal.valueOf(100), estatisticas.getMax());
		assertEquals(BigDecimal.valueOf(50), estatisticas.getMin());
		assertEquals(BigDecimal.valueOf(70.0), estatisticas.getMedia()); // (100 + 50 + 60)/3 = 70
		assertEquals(BigDecimal.valueOf(210), estatisticas.getSoma());
		assertEquals(3, estatisticas.getQtde().intValue());		
	}	

}
