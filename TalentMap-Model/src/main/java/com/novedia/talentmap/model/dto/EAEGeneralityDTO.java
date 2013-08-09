/**
 * 
 */
package com.novedia.talentmap.model.dto;


/**
 * @author v.guillemain
 *
 */
public class EAEGeneralityDTO {

    /**
     * The identifier of the EAE
     */
    private Integer id;

    private String collabLastName;
    private String collabFirstName;
    
    /**
     * Colleague's name (Last + First)
     */
    private String collabLastAndFirstName;

    /**
     * Colleague profile
     */
    private String profile;
    
    /**
     * Manager's name (Last + First)
     */
    private String managerLastAndFirstName;

    /**
     * Manager's last name
     */
    private String managerLastName;
    /**
     * Manager's first name
     */
    private String managerFirstName;
    /**
     * EAE's date
     */
    private String eaeDate;
    /**
     * Colleague's employment date
     */
    private String employmentDate;
    /**
     * Previous EAE's date
     */
    private String previousEaeDate;

    /**
     * The State of the EAE
     */
    private Integer eaeStateId;
    
    
    /**
     * The salary of the colleague at the date of the EAE
     */
    private Integer salary;
    
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
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }


    /**
     * @return the eaeDate
     */
    public String getEaeDate() {
        return eaeDate;
    }

    /**
     * @param eaeDate the eaeDate to set
     */
    public void setEaeDate(String eaeDate) {
        this.eaeDate = eaeDate;
    }

    /**
     * @return the employmentDate
     */
    public String getEmploymentDate() {
        return employmentDate;
    }

    /**
     * @param employmentDate the employmentDate to set
     */
    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    /**
     * @return the previousEaeDate
     */
    public String getPreviousEaeDate() {
        return previousEaeDate;
    }

    /**
     * @param previousEaeDate the previousEaeDate to set
     */
    public void setPreviousEaeDate(String previousEaeDate) {
        this.previousEaeDate = previousEaeDate;
    }

    
    /**
     * @return the colleagueName
     */
    public String getColleagueName() {
        return collabLastName + "" +collabFirstName;
    }


    /**
     * @return the managerName
     */
    public String getManagerName() {
        return managerLastName + "" + managerFirstName;
    }


    /**
     * @return the collabLastName
     */
    public String getCollabLastName() {
        return collabLastName;
    }

    /**
     * @param collabLastName the collabLastName to set
     */
    public void setCollabLastName(String collabLastName) {
        this.collabLastName = collabLastName;
    }

    /**
     * @return the collabFirstName
     */
    public String getCollabFirstName() {
        return collabFirstName;
    }

    /**
     * @param collabFirstName the collabFirstName to set
     */
    public void setCollabFirstName(String collabFirstName) {
        this.collabFirstName = collabFirstName;
    }

    /**
	 * @return the collabLastAndFirstName
	 */
	public String getCollabLastAndFirstName() {
		return this.collabLastName + " " + this.collabFirstName;
	}

	/**
	 * @param collabLastAndFirstName the collabLastAndFirstName to set
	 */
	public void setCollabLastAndFirstName(String collabLastAndFirstName) {
		this.collabLastAndFirstName = collabLastAndFirstName;
	}

	/**
     * @return the managerLastName
     */
    public String getManagerLastName() {
        return managerLastName;
    }

    /**
     * @param managerLastName the managerLastName to set
     */
    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    /**
     * @return the managerFirstName
     */
    public String getManagerFirstName() {
        return managerFirstName;
    }

    /**
     * @param managerFirstName the managerFirstName to set
     */
    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    /**
	 * @return the managerLastAndFirstName
	 */
	public String getManagerLastAndFirstName() {
		return this.managerLastName + " " + this.managerFirstName;
	}

	/**
	 * @param managerLastAndFirstName the managerLastAndFirstName to set
	 */
	public void setManagerLastAndFirstName(String managerLastAndFirstName) {
		this.managerLastAndFirstName = managerLastAndFirstName;
	}

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
    public String toString() {
	StringBuilder strBld = new StringBuilder();
	strBld.append("[id=").append(getId()).append(", ");
	strBld.append("colleagueName=").append(getColleagueName()).append(", ");
	strBld.append("profile=").append(getProfile()).append(", ");
	strBld.append("managerName=").append(getManagerName()).append(", ");
	strBld.append("eaeDate=").append(getEaeDate()).append(", ");
	strBld.append("employmentDate=").append(getEmploymentDate()).append(", ");
	strBld.append("previousEaeDate=").append(getPreviousEaeDate()).append(", ");
	strBld.append("eaeStateId=").append(getEaeStateId()).append("]");

	return strBld.toString();
    }
}
