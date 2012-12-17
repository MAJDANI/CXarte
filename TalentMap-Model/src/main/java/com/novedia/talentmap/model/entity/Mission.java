package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a mission.
 * 
 * @author e.moumbe
 *
 */
public class Mission implements Serializable {
	

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 6695477652936529697L;

	/**
	 * The mission id
	 */
	private Integer id;
	
	/**
	 * TODO : à supprimer
	 * 
	 * Colleague associated the mission
	 */
	private Integer colleague;
	
	/**
	 * Mission name
	 */
	private String name;
	
	/**
	 * Mission take place
	 */
	private String place;
	
	/**
	 * The mission customer
	 */
	private String client;
	
	/**
	 * Information on this mission
	 */
	private String notes;
	
	/**
	 * Start date of the mission
	 */
	private Date startDate;
	
	/**
	 * End date of the mission
	 */
	private Date endDate;
	
	/**
	 * Build the class Mission.java
	 * 
	 * @param id
	 * @param collab_id
	 * @param name
	 * @param place
	 * @param client
	 * @param notes
	 * @param start_date
	 * @param end_date
	 */
	public Mission(Integer id, Integer colleague, String name, String place,
			String client, String notes, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.colleague = colleague;
		this.name = name;
		this.place = place;
		this.client = client;
		this.notes = notes;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Build the class Mission.java 
	 */
	public Mission() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[collab_id=").append(getColleague()).append(", ");
		strBld.append("[name=").append(getName()).append(", ");
		strBld.append("[client=").append(getClient()).append("]");
		return strBld.toString();	
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hBuilder =  new HashCodeBuilder();
		hBuilder.append(this.getId());
		hBuilder.append(this.getColleague());
		hBuilder.append(this.getName());
		hBuilder.append(this.getClient());
		return hBuilder.hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;			
		}

		if(!(obj instanceof Mission)){
			return false;
		}	
		Mission comparedObj = (Mission)obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getId(), comparedObj.getId());
		ebuilder.append(this.getClient(), comparedObj.getClient());
		ebuilder.append(this.getName(), comparedObj.getName());
		return ebuilder.isEquals();
	}
	
	
	/**
	 * Set the collab_id value
	 * @param collab_id the collab_id to set
	 */
	public void setCollab_id(Integer collab_id) {
		this.colleague = collab_id;
	}
	/**
	 * Get the client value
	 * 
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * Set the client value
	 * 
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * Get the start_date value
	 * 
	 * @return the start_date
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Set the start_date value
	 * 
	 * @param start_date the start_date to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get the end_date value
	 * 
	 * @return the end_date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the end_date value
	 * 
	 * @param end_date the end_date to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Get the name value
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name value
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the id value
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the name value
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Get the colleague value
	 * 
	 * @return colleague
	 */
	public Integer getColleague() {
		return colleague;
	}

	/**
	 * Get the place value
	 * 
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Set the place value
	 * 
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Get the notes 
	 * 
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Set the notes
	 * 
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
