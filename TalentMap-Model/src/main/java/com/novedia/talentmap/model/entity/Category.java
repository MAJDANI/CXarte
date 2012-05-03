package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Category implements Serializable {
	
	private String id;
	private String name;
	
	
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
	
	
}
