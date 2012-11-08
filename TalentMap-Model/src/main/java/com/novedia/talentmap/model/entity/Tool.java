package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class Tool implements Serializable {
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -6638545060221928596L;
	/**
	 * tool id
	 */
	private Integer id;
	/**
	 * collaborator id associated the tool
	 */
	private Integer concept_id;
	/**
	 * tool name
	 */
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
		
	/**
	 * allowed to display attribute of the object Skill
	 */	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[concept_id=").append(getConcept_id()).append(", ");
		strBld.append("[name=").append(getName()).append("]");
		return strBld.toString();	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getConcept_id());
		hashBuilder.append(this.getName());
		return hashBuilder.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;			
		}

		if(!(obj instanceof Profile)){
			return false;
		}
	
		Tool comparedObj = (Tool)obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getConcept_id(), comparedObj.getConcept_id());
		ebuilder.append(this.getName(), comparedObj.getName());
		return ebuilder.isEquals();
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