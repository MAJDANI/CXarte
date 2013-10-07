package com.novedia.talentmap.web.utils;

import java.util.Hashtable;

/**
 * Cette classe permet de lister les différents profil connectés et connaitre le profil en cours
 * 
 * 
 * @author v.guillemain
 * 
 */
public enum ProfilConnectedEnum {

	MANAGER(1, "MANAGER"), COLLEAGUE(2, "COLLEAGUE");

	private int id;
	private String value;

	
	private ProfilConnectedEnum(int id, String value) {
		this.id = id;
		this.value = value;
	}


	/**
	 * Get the id value
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id value
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the value value
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value value
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
