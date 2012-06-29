package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Profile implements Serializable {

	private static final long serialVersionUID = 8766946810895701286L;
	private Integer id;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [id=" + id + ", type=" + type + "]";
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
