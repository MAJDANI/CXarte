package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Concept implements Serializable {
	
	private String id;
	private String category_id;
	private String name;
	
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
	 * Get the category_id value
	 * @return the category_id
	 */
	public String getCategory_id() {
		return category_id;
	}
	/**
	 * Set the category_id value
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
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
