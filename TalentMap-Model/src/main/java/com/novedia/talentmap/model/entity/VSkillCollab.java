package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class VSkillCollab implements Serializable{
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 8936460447975401894L;
	/**
	 * collaborator id associated the VSkillCollab
	 */
	private int collab_id;
	/**
	 * category name associated the VSkillCollab
	 */
	private String category_name;
	/**
	 * concept name associated the VSkillCollab
	 */
	private String concept_name;
	/**
	 * tool name associated the VSkillCollab
	 */
	private String tool_name;
	
	/**
	 * Build the class VSkillCollab.java 
	 * @param collab_id
	 * @param category_name
	 * @param concept_name
	 * @param tool_name
	 */
	public VSkillCollab(int collab_id, String category_name,
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
	 * allowed to display attribute of the object VSkill
	 */
	@Override
	public String toString() {

		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[category_name=").append(getCategory_name()).append(", ");
		strBld.append("[tool_name=").append(getTool_name()).append(", ");
		strBld.append("[concept_name=").append(getConcept_name()).append("]");
		return strBld.toString();	
	}
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getCategory_name());
		hashBuilder.append(this.getTool_name());
		hashBuilder.append(this.getConcept_name());
		return hashBuilder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;			
		}

		if(!(obj instanceof VSkill)){
			return false;
		}
	
		VSkill comparedObj = (VSkill)obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getCategory_name(), comparedObj.getCategory_name());
		ebuilder.append(this.getTool_name(), comparedObj.getTool_name());
		ebuilder.append(this.getConcept_name(), comparedObj.getConcept_name());
		return ebuilder.isEquals();
	}
	/**
	 * Get the collab_id value
	 * @return the collab_id
	 */
	public int getCollab_id() {
		return collab_id;
	}
	/**
	 * Set the collab_id value
	 * @param collab_id the collab_id to set
	 */
	public void setCollab_id(int collab_id) {
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
