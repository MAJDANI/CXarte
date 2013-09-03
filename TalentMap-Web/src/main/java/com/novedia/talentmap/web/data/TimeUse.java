package com.novedia.talentmap.web.data;

/**
 * Enumeration for TimeUse (LESS_1_YEAR, LESS_2_YEAR, LESS_3_YEAR, LESS_4_YEAR,
 * LESS_5_YEAR)
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.data
 * @created 21 mai 2012
 */
public enum TimeUse {
    LESS_1_YEAR(1, "Less than 1 year"), LESS_2_YEAR(2, "Between 1 and 2 years"), LESS_3_YEAR(
	    3, "Between 2 and 3 years "), LESS_4_YEAR(4, "More than 3 years");

    private int id;
    private String value;

    private TimeUse(int id, String value) {
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
