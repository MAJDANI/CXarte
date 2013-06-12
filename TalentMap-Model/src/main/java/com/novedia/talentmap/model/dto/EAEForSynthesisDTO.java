/**
 * 
 */
package com.novedia.talentmap.model.dto;

import java.util.Date;

/**
 * @author v.guillemain
 *
 */
public class EAEForSynthesisDTO {
    /**
     * The identifier of the EAE
     */
    private Integer id;
    /**
     * The date of the meeting for the EAE
     */
    private Date dateEae;
    /**
     * Colleague last name
     */
    private String lastName;

    /**
     * Colleague first name
     */
    private String firstName;

    /**
     * The State of the EAE
     */
    private Integer eaeStateId;

    /**
     * The Label of the State of the EAE
     */
    private String eaeStateLabel;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dateEae
     */
    public Date getDateEae() {
        return dateEae;
    }

    /**
     * @param dateEae the dateEae to set
     */
    public void setDateEae(Date dateEae) {
        this.dateEae = dateEae;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the eaeStateId
     */
    public Integer getEaeStateId() {
        return eaeStateId;
    }

    /**
     * @param eaeStateId the eaeStateId to set
     */
    public void setEaeStateId(Integer eaeStateId) {
        this.eaeStateId = eaeStateId;
    }

    /**
     * @return the eaeStateLabel
     */
    public String getEaeStateLabel() {
        return eaeStateLabel;
    }

    /**
     * @param eaeStateLabel the eaeStateLabel to set
     */
    public void setEaeStateLabel(String eaeStateLabel) {
        this.eaeStateLabel = eaeStateLabel;
    }
    
    @Override
    public String toString() {
	StringBuilder strBld = new StringBuilder();
	strBld.append("[id=").append(getId()).append(", ");
	strBld.append("dateEae=").append(getDateEae()).append(", ");
	strBld.append("lastName=").append(getLastName()).append(", ");
	strBld.append("firstName=").append(getFirstName()).append(", ");
	strBld.append("eaeStateId=").append(getEaeStateId()).append(", ");
	strBld.append("eaeStateLabel=").append(getEaeStateLabel()).append("]");
	
	return strBld.toString();
    }

}
