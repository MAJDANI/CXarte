/**
 * 
 */
package com.novedia.talentmap.model.dto;

import java.util.Date;


/**
 * @author v.guillemain
 * 
 */
public class NewEAEDTO {

	/**
	 * The identifier of the EAE
	 */
	private Integer id;
	/**
	 * The id of the colleague.
	 */
	private Integer colleagueId;
	/**
	 * The id of the manager.
	 */
	private Integer managerId;
	/**
	 * The id of the state of the EAE.
	 */
	private Integer eaeStateId;
	/**
	 * The date of the EAE
	 */
	private Date eaeDate;
	/**
	 * The Id of the previous EAE
	 */
	private Integer previousEaeId;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the colleagueId
	 */
	public Integer getColleagueId() {
		return colleagueId;
	}

	/**
	 * @param colleagueId the colleagueId to set
	 */
	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}

	/**
	 * @return the managerId
	 */
	public Integer getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the eaeDate
	 */
	public Date getEaeDate() {
		return eaeDate;
	}

	/**
	 * @param eaeDate the eaeDate to set
	 */
	public void setEaeDate(Date eaeDate) {
		this.eaeDate = eaeDate;
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
	 * @return the previousEaeId
	 */
	public Integer getPreviousEaeId() {
		return previousEaeId;
	}

	/**
	 * @param previousEaeId the previousEaeId to set
	 */
	public void setPreviousEaeId(Integer previousEaeId) {
		this.previousEaeId = previousEaeId;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("colleagueId=").append(getColleagueId()).append(", ");
		strBld.append("managerId=").append(getManagerId()).append(", ");
		strBld.append("eaeDate=").append(getEaeDate()).append(", ");
		strBld.append("eaeStateId=").append(getEaeStateId()).append(", ");
		strBld.append("previousEaeId=").append(getPreviousEaeId()).append("]");
		return strBld.toString();
	}
}
