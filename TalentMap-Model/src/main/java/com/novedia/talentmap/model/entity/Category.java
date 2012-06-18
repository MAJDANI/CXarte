package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Category implements Serializable {
	
	private String id;
	private String name;
	
	
	/**
	 * Build the class Category.java 
	 * @param id
	 * @param name
	 */
	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Build the class Category.java 
	 */
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get the category_id value
	 * @return the category_id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the category_id value
	 * @param category_id the category_id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
