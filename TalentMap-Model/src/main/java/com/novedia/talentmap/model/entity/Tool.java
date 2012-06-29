package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Tool implements Serializable {
	
	private Integer id;
	private Integer concept_id;
	private String name;
	
	/**
	 * Build the class Tool.java 
	 * @param id
	 * @param concept_id
	 * @param name
	 */
	public Tool(Integer id, Integer concept_id, String name) {
		super();
		this.id = id;
		this.concept_id = concept_id;
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tool [id=" + id + ", concept_id=" + concept_id + ", name="
				+ name + "]";
	}
	/**
	 * Build the class Tool.java 
	 */
	public Tool() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get the tool_id value
	 * @return the tool_id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the tool_id value
	 * @param tool_id the tool_id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Get the concept_id value
	 * @return the concept_id
	 */
	public Integer getConcept_id() {
		return concept_id;
	}
	/**
	 * Set the concept_id value
	 * @param concept_id the concept_id to set
	 */
	public void setConcept_id(Integer concept_id) {
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
