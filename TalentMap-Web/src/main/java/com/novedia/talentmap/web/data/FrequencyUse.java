package com.novedia.talentmap.web.data;

/**
 * Enumeration for Frequency Use (FEW, OFTEN, REGULARLY)
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.data
 * @created 21 mai 2012
 */
public enum FrequencyUse {

    FEW(1, "very little"), OFTEN(2, "Often enough"), REGULARLY(3, "Regularly");

    private int id;
    private String value;

    private FrequencyUse(int id, String value) {
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
