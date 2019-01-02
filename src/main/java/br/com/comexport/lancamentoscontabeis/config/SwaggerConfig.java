/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

 
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("br.com.comexport.lancamentoscontabeis.endpoint"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Lançamentos Contábeis Teste API", 
          "API de avaliação para a empresa Comexport.", 
          "1.0", 
          "Terms of service", 
          new Contact("Cleber de Araujo Santos", "https://www.linkedin.com/in/cleber-de-araujo-santos/", "cleberfatec@yahoo.com.br"), 
          "License of API", "API license URL", Collections.emptyList());
    }
}
