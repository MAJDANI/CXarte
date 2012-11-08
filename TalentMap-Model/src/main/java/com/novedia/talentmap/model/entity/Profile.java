package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Profile implements Serializable {
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 8766946810895701286L;
	/**
	 * profile id
	 */
	private Integer id;
	/**
	 * profile type
	 */
	private String type;
	
	
	/**
	 * Build the class Profile.java 
	 * @param id
	 * @param type
	 */
	public Profile(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	/**
	 * Build the class Profile.java 
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
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
	 * Get the profileType value
	 * @return the profileType
	 */
	public String getType() {
		return type;
	}
	/**
	 * Set the profileType value
	 * @param profileType the profileType to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * allowed to display attribute of the object Profile
	 */	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("[type=").append(getType()).append("]");
		return strBld.toString();	
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		return hashBuilder.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;			
		}

		if(!(obj instanceof Profile)){
			return false;
		}
	
		Profile comparedObj = (Profile)obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getId(), comparedObj.getId());
		ebuilder.append(this.getType(), comparedObj.getType());
		return ebuilder.isEquals();
	}
}
