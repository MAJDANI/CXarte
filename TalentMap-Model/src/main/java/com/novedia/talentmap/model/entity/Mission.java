package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Bean Mission
 * 
 * @author moumbe
 *
 */
public class Mission implements Serializable {
	
	/**UID*/
	private static final long serialVersionUID = 1L;

	/**mission id*/
	private Integer id;
	
	/**
	 * collaborator id associated the mission
	 */
	private Integer collab_id;
	
	/**
	 * mission name
	 */
	private String name;
	
	/**
	 * mission take place
	 */
	private String place;
	
	/**
	 * mission id associated the client
	 */
	private String client;
	
	/**
	 * mission id associated the notes
	 */
	private String notes;
	
	/**
	 * date of start the mission
	 */
	private Date start_date;
	
	/**
	 * date of end the mission
	 */
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
	
	/**
	 * allowed to display attribute of the object Concept
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[collab_id=").append(getCollab_id()).append(", ");
		strBld.append("[name=").append(getName()).append(", ");
		strBld.append("[client=").append(getClient()).append("]");
		return strBld.toString();	
	}

	/**
	 * hash code method
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hBuilder =  new HashCodeBuilder();
		hBuilder.append(this.getId());
		hBuilder.append(this.getCollab_id());
		hBuilder.append(this.getName());
		hBuilder.append(this.getClient());
		return hBuilder.hashCode();
	}
	
	/**
	 * equals method
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
	 *Get the name value
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the name value
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Get the name value
	 * @return
	 */
	public Integer getCollab_id() {
		return collab_id;
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
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


}
