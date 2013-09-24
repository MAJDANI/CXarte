package com.novedia.talentmap.web.utils;


/**
 * This enum represents the states of an EAE. An EAE may have 3 states : when
 * it is created by the manager, in anticipation of an early future EAE, the
 * state of the EAE is "Open". In this state "Open", the colleague is invited to
 * fill his part of informations about his activity during the year. When the
 * colleague's input is finished, the colleague validates his inputs : the EAE
 * is in state "validated" : the manager can open the EAE and 1.see the
 * colleague's input 2.fill the manager's part of informations. When the EAE is
 * finished and filled, the manager can close it. In state "close", the
 * colleague may consult the EAE but it's impossible to modify anything. The 3
 * states : open, validated, close.
 * 
 * @author v.guillemain
 * 
 */
public enum EAEStateEnum {

	OPEN(1, "OPEN"), VALIDATED(2, "VALIDATED"), CLOSED(3, "CLOSED");

	private int id;
	private String value;

	
	private EAEStateEnum(int id, String value) {
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
