/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Cleber de Araujo
 * @date 02 de Jan de 2019
 */

public class LancamentoContabilModel implements Comparable {

	@JsonIgnore
	private String id;
	private Integer contaContabil;
	private String data;
	private BigDecimal valor;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the contaContabil
	 */
	public Integer getContaContabil() {
		return contaContabil;
	}

	/**
	 * @param contaContabil the contaContabil to set
	 */
	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(Object o) {
		BigDecimal valor2 = ((LancamentoContabilModel) o).getValor();
		if (getValor().compareTo(valor2) > 0) {
			return 1;
		} else if (getValor().compareTo(valor2) < 0) {
			return -1;
		}
		return 0;
	}



}
