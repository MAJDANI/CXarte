/**
 * 
 */
package com.novedia.talentmap.model.dto;

import java.util.Date;


/**
 * @author v.guillemain
 * 
 */
public class EAEColleagueResumeForCMDTO {

	/**
	 * The identifier of the Colleague
	 */
	private Integer idColleague;
	/**
	 * The identifier of the EAE
	 */
	private Integer idEAE;
	/**
	 * The last Name of the colleague.
	 */
	private String collabLastName;
	/**
	 * The synthesis of the year given by the manager.
	 */
	private String collabFirstName;
	/**
	 * The id of the state of the EAE.
	 */
	private Integer eaeStateId;
	
	/**
	 * The date of the EAE.
	 */
	private Date eaeDate;
	
	/**
     * Colleague Sex
     */
	private String title;

	/**
	 * @return the idColleague
	 */
	public Integer getIdColleague() {
		return idColleague;
	}

	/**
	 * @param idColleague the idColleague to set
	 */
	public void setIdColleague(Integer idColleague) {
		this.idColleague = idColleague;
	}

	/**
	 * @return the idEAE
	 */
	public Integer getIdEAE() {
		return idEAE;
	}

	/**
	 * @param idEAE the idEAE to set
	 */
	public void setIdEAE(Integer idEAE) {
		this.idEAE = idEAE;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[idColleague=").append(getIdColleague()).append(", ");
		strBld.append("idEAE=").append(getIdEAE()).append(", ");
		strBld.append("title=").append(getTitle()).append(", ");
		strBld.append("collabLastName=").append(getCollabLastName()).append(", ");
		strBld.append("collabFirstName=").append(getCollabFirstName()).append(", ");
		strBld.append("eaeStateId=").append(getEaeStateId()).append(", ");
		strBld.append("dateEae=").append(getEaeDate()).append("]");

		return strBld.toString();
	}
}
