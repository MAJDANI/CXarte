package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Tool implements Serializable {
	
	private String id;
	private String concept_id;
	private String name;
	
	
	/**
	 * Get the tool_id value
	 * @return the tool_id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the tool_id value
	 * @param tool_id the tool_id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get the concept_id value
	 * @return the concept_id
	 */
	public String getConcept_id() {
		return concept_id;
	}
	/**
	 * Set the concept_id value
	 * @param concept_id the concept_id to set
	 */
	public void setConcept_id(String concept_id) {
		this.concept_id = concept_id;
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
