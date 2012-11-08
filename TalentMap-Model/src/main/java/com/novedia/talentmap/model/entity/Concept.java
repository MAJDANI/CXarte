package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Concept implements Serializable {
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -3363564791814357380L;
	/**
	 * concept id
	 */
	private Integer id;
	/**
	 * category id associated the concept
	 */
	private Integer category_id;
	/**
	 * concept name
	 */
	private String name;
	/**
	 * Not in database
	 */
	private double score;
	
	/**
	 * Build the class Concept.java 
	 * @param id
	 * @param category_id
	 * @param name
	 * @param score
	 */
	public Concept(Integer id, Integer category_id, String name, double score) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.score = score;
	}
	
	/**
	 * 
	 * Build the class Concept.java 
	 * @param id
	 * @param category_id
	 * @param name
	 */
	public Concept(Integer id, Integer category_id, String name) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.score = 0.0;
	}
	
	/**
	 * 
	 * Build the class Concept.java
	 */
	public Concept(){
		this.score = 0.0;
	}
	
	/**
	 * Get the score value
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * Set the score value
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
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
	 * Get the category_id value
	 * @return the category_id
	 */
	public Integer getCategory_id() {
		return category_id;
	}
	/**
	 * Set the category_id value
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(Integer category_id) {
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
	/**
	 * allowed to display attribute of the object Concept
	 * @return
	 */
	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[category_id=").append(getCategory_id()).append(", ");
		strBld.append("name").append(getName()).append("] ");
		return strBld.toString();		
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
		equalsBuilder.append(this.getName(), comparedObj.getName());
		return equalsBuilder.isEquals();		
	}	
}