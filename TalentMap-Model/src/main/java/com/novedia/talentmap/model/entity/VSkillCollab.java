package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class VSkillCollab implements Serializable{
	
	private String collab_id;
	private String category_name;
	private String concept_name;
	private String tool_name;
	
	/**
	 * Build the class VSkillCollab.java 
	 * @param collab_id
	 * @param category_name
	 * @param concept_name
	 * @param tool_name
	 */
	public VSkillCollab(String collab_id, String category_name,
			String concept_name, String tool_name) {
		super();
		this.collab_id = collab_id;
		this.category_name = category_name;
		this.concept_name = concept_name;
		this.tool_name = tool_name;
	}
	/**
	 * Build the class VSkillCollab.java 
	 */
	public VSkillCollab() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get the collab_id value
	 * @return the collab_id
	 */
	public String getCollab_id() {
		return collab_id;
	}
	/**
	 * Set the collab_id value
	 * @param collab_id the collab_id to set
	 */
	public void setCollab_id(String collab_id) {
		this.collab_id = collab_id;
	}
	/**
	 * Get the category_name value
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * Set the category_name value
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * Get the concept_name value
	 * @return the concept_name
	 */
	public String getConcept_name() {
		return concept_name;
	}
	/**
	 * Set the concept_name value
	 * @param concept_name the concept_name to set
	 */
	public void setConcept_name(String concept_name) {
		this.concept_name = concept_name;
	}
	/**
	 * Get the tool_name value
	 * @return the tool_name
	 */
	public String getTool_name() {
		return tool_name;
	}
	/**
	 * Set the tool_name value
	 * @param tool_name the tool_name to set
	 */
	public void setTool_name(String tool_name) {
		this.tool_name = tool_name;
	}
	
	
}
