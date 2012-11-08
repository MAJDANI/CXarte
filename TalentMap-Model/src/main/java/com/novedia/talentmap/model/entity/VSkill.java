package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class VSkill  implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 1160292151144778521L;
	/**
	 * category name associated the VSKill
	 */
	private String category_name;
	/**
	 * concept name associated the VSKill
	 */
	private String concept_name;
	/**
	 * tool name associated the VSKill
	 */
	private String tool_name;
	
	/**
	 * Build the class VSkill.java 
	 * @param category_name
	 * @param concept_name
	 * @param tool_name
	 */
	public VSkill(String category_name, String concept_name, String tool_name) {
		super();
		this.category_name = category_name;
		this.concept_name = concept_name;
		this.tool_name = tool_name;
	}
	/**
	 * Build the class VSkill.java 
	 */
	public VSkill() {
		super();
		// TODO Auto-generated constructor stub
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
	 * hash code method
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getCategory_name());
		hashBuilder.append(this.getTool_name());
		hashBuilder.append(this.getConcept_name());
		return hashBuilder.hashCode();
	}
	/**
	 * equals method
	 */
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
}