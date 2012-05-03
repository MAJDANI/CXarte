package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Tool implements Serializable {
	
	private String id;
	private String concept_id;
	private String name;
	private Integer use_frequency;
	private Integer no_using_time;
	
	
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
	/**
	 * Get the use_frequency value
	 * @return the use_frequency
	 */
	public Integer getUse_frequency() {
		return use_frequency;
	}
	/**
	 * Set the use_frequency value
	 * @param use_frequency the use_frequency to set
	 */
	public void setUse_frequency(Integer use_frequency) {
		this.use_frequency = use_frequency;
	}
	/**
	 * Get the no_using_time value
	 * @return the no_using_time
	 */
	public Integer getNo_using_time() {
		return no_using_time;
	}
	/**
	 * Set the no_using_time value
	 * @param no_using_time the no_using_time to set
	 */
	public void setNo_using_time(Integer no_using_time) {
		this.no_using_time = no_using_time;
	}
	
	
}
