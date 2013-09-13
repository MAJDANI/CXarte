package com.novedia.talentmap.web.utils;

public enum EAETabEnum {
	RESULTS_TAB(1, "RESULTS_TAB"), OBJECTIVE_TAB(2, "OBJECTIVE_TAB");

	private int id;
	private String value;

	
	private EAETabEnum(int id, String value) {
		this.id = id;
		this.value = value;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
