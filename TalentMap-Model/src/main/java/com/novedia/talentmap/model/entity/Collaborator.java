package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Collaborator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1323210979848908088L;
	private String id;
	private String manager_id;
	private String profile_id;
	private String last_name;
	private String first_name;
	private String email;
	private Integer phone;
	private Date employment_date;
	private Integer experience;
	private String business_engineer;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Collaborator [id=" + id + ", manager_id=" + manager_id
				+ ", profil_id=" + profile_id + ", last_name=" + last_name
				+ ", first_name=" + first_name + ", email=" + email
				+ ", phone=" + phone + ", employment_date=" + employment_date
				+ ", experience=" + experience + ", business_engineer="
				+ business_engineer + "]";
	}
	/**
	 * Get the id value
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the id value
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get the last_name value
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * Set the last_name value
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * Get the first_name value
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * Set the first_name value
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * Get the email value
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Set the email value
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Get the manager_id value
	 * @return the manager_id
	 */
	public String getManager_id() {
		return manager_id;
	}
	/**
	 * Set the manager_id value
	 * @param manager_id the manager_id to set
	 */
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	/**
	 * Get the profil_id value
	 * @return the profil_id
	 */
	public String getProfile_id() {
		return profile_id;
	}
	/**
	 * Set the profil_id value
	 * @param profil_id the profil_id to set
	 */
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	/**
	 * Get the phone value
	 * @return the phone
	 */
	public Integer getPhone() {
		return phone;
	}
	/**
	 * Set the phone value
	 * @param phone the phone to set
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	/**
	 * Get the employment_date value
	 * @return the employment_date
	 */
	public Date getEmployment_date() {
		return employment_date;
	}
	/**
	 * Set the employment_date value
	 * @param employment_date the employment_date to set
	 */
	public void setEmployment_date(Date employment_date) {
		this.employment_date = employment_date;
	}
	/**
	 * Get the experience value
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}
	/**
	 * Set the experience value
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	/**
	 * Get the business_engineer value
	 * @return the business_engineer
	 */
	public String getBusiness_engineer() {
		return business_engineer;
	}
	/**
	 * Set the business_engineer value
	 * @param business_engineer the business_engineer to set
	 */
	public void setBusiness_engineer(String business_engineer) {
		this.business_engineer = business_engineer;
	}
	
}
