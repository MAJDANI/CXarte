package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Mission implements Serializable {

	private String id;
	private String collab_id;
	private String client;
	private Date start_date;
	private Date end_date;
	
	
	/**
	 * Build the class Mission.java 
	 * @param id
	 * @param collab_id
	 * @param client
	 * @param start_date
	 * @param end_date
	 */
	public Mission(String id, String collab_id, String client, Date start_date,
			Date end_date) {
		super();
		this.id = id;
		this.collab_id = collab_id;
		this.client = client;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	/**
	 * Build the class Mission.java 
	 */
	public Mission() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mission [id=" + id + ", collab_id=" + collab_id + ", client="
				+ client + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
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
	 * Get the collab_id value
	 * @return the collab_id
	 */
	public String getCollab_id() {
		return collab_id;
	}
	/**
	 * Set the collab_id value
	 * @param collab_id the collab_id to set
	 */
	public void setCollab_id(String collab_id) {
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
	
	
}
