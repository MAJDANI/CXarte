package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class Category implements Serializable {
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 2472787207640474467L;
	/**
	 * id of Category
	 */
	private Integer id;
	/**
	 * category name
	 */
	private String name;
	
	
	/**
	 * Build the class Category.java 
	 * @param id
	 * @param name
	 */
	public Category(Integer id, String name) {
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
	public Integer getId() {
		return id;
	}
	/**
	 * Set the category_id value
	 * @param category_id the category_id to set
	 */
	public void setId(Integer id) {
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
		HashCodeBuilder hBuilder =  new HashCodeBuilder();
		hBuilder.append(this.getId());
		hBuilder.append(this.getName());
		return hBuilder.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if(!(obj instanceof Category)){
			return false;
		}
		Category comparedObj = (Category)obj;
		EqualsBuilder equalsBuilder =  new EqualsBuilder();
		equalsBuilder.append(this.getId(), comparedObj.getId());
		return equalsBuilder.isEquals();		
	}
	/**
	 * allowed to display attribute of the object Category
	 */
	@Override
	public String toString() {		
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("name").append(getName()).append("] ");
		return strBld.toString();		
	}	
}
