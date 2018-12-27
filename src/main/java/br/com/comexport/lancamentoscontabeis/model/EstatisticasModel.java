
package br.com.comexport.lancamentoscontabeis.model;

import java.math.BigDecimal;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
public class EstatisticasModel {

	private BigDecimal soma;
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal media;
	private Integer qtde;

	/**
	 * @return the soma
	 */
	public BigDecimal getSoma() {
		return soma;
	}

	/**
	 * @param soma the soma to set
	 */
	public void setSoma(BigDecimal soma) {
		this.soma = soma;
	}

	/**
	 * @return the min
	 */
	public BigDecimal getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(BigDecimal min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public BigDecimal getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(BigDecimal max) {
		this.max = max;
	}

	/**
	 * @return the media
	 */
	public BigDecimal getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(BigDecimal media) {
		this.media = media;
	}

	/**
	 * @return the qtde
	 */
	public Integer getQtde() {
		return qtde;
	}

	/**
	 * @param qtde the qtde to set
	 */
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

}
