/**
 * 
 */
package br.com.comexport.lancamentoscontabeis.model;

/**
 * @author Cleber de Araujo
 * @date 27 de dez de 2018
 *
 */
public class ObjectIdModel {

	private String id;

	
	public ObjectIdModel(String id) {
		super();
		this.id = id;
	}

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
}
