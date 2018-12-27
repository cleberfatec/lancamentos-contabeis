package br.com.comexport.lancamentoscontabeis.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
public class Utils {
	
	public static String gerarChave() {
		int tamanho = 36;
		boolean usarLetras = true;
		boolean usarNumeros = true;
		return RandomStringUtils.random(tamanho, usarLetras, usarNumeros);
	}

}
