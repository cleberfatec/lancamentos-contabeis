package br.com.comexport.lancamentoscontabeis;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class LancamentosContabeisApplicationTests {

	@Value("${local.server.port}")
	protected int porta;
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = porta;
	}

}

