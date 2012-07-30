package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Mission implements Serializable {

	private Integer id;
	private Integer collab_id;
	private String name;
	private String place;
	private String client;
	private String notes;
	private Date start_date;
	private Date end_date;
	
	/**
	 * Build the class Mission.java 
	 * @param id
	 * @param collab_id
	 * @param name
	 * @param place
	 * @param client
	 * @param notes
	 * @param start_date
	 * @param end_date
	 */
	public Mission(Integer id, Integer collab_id, String name, String place,
			String client, String notes, Date start_date, Date end_date) {
		super();
		this.id = id;
		this.collab_id = collab_id;
		this.name = name;
		this.place = place;
		this.client = client;
		this.notes = notes;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	/**
	 * Build the class Mission.java 
	 */
	public Mission() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mission [id=" + id + ", collab_id=" + collab_id + ", name="
				+ name + ", place=" + place + ", client=" + client + ", notes="
				+ notes + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}

	/**
	 * Get the id value
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the id value
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Get the collab_id value
	 * @return the collab_id
	 */
	public Integer getCollab_id() {
		return collab_id;
	}
	/**
	 * Set the collab_id value
	 * @param collab_id the collab_id to set
	 */
	public void setCollab_id(Integer collab_id) {
		this.collab_id = collab_id;
	}
	/**
	 * Get the client value
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * Set the client value
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * Get the start_date value
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	/**
	 * Set the start_date value
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	/**
	 * Get the end_date value
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	/**
	 * Set the end_date value
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * Get the name value
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name value
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the place value
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Set the place value
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Get the notes value
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Set the notes value
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
