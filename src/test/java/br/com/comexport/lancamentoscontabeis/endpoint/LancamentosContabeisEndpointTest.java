/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.endpoint;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.comexport.lancamentoscontabeis.model.LancamentoContabilModel;
import br.com.comexport.lancamentoscontabeis.repository.LancamentosContabeisRepository;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LancamentosContabeisEndpointTest.class)
public class LancamentosContabeisEndpointTest {
	
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private LancamentosContabeisRepository repository;

    
    @Test
    public void cadastrarLancamentosContabeisTest()
      throws Exception {
    	
    	LancamentoContabilModel lanc = new LancamentoContabilModel();
    	lanc.setContaContabil(1);
    	lanc.setData("20181227");
    	lanc.setValor(BigDecimal.valueOf(10));
    	 
        
     
//    	given(repository.buscarLancamentoPorId("123")).willReturn(lanc);
     
        mvc.perform(get("/lancamentos-contabeis/")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    	
    }
    
}
