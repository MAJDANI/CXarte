package com.novedia.talentmap.web.ui.colleague.eae;

public enum EAEConsultationMode {

	OPEN_COLLAB(1, "OPEN_COLLAB"), OPEN_MANAGER(2, "OPEN MANAGER"), VALIDATED_COLAB(
			3, "VALIDATED_COLAB"), CLOSED(4, "CLOSED");

	private int id;
	private String value;

	private EAEConsultationMode(int id, String value) {
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
