package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Skill implements Serializable{
	
	private Integer collaborator_id;
	private Integer tool_id;
	private Integer score;
	private Integer use_frequency;
	private Integer no_using_time;
	
	
	/**
	 * Build the class Skill.java 
	 * @param collaborator_id
	 * @param tool_id
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 */
	public Skill(Integer collaborator_id, Integer tool_id, Integer score,
			Integer use_frequency, Integer no_using_time) {
		super();
		this.collaborator_id = collaborator_id;
		this.tool_id = tool_id;
		this.score = score;
		this.use_frequency = use_frequency;
		this.no_using_time = no_using_time;
	}
	/**
	 * Build the class Skill.java 
	 */
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
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
	/**
	 * Get the collaborator_id value
	 * @return the collaborator_id
	 */
	public Integer getCollaborator_id() {
		return collaborator_id;
	}
	/**
	 * Set the collaborator_id value
	 * @param collaborator_id the collaborator_id to set
	 */
	public void setCollaborator_id(Integer collaborator_id) {
		this.collaborator_id = collaborator_id;
	}
	/**
	 * Get the tool_id value
	 * @return the tool_id
	 */
	public Integer getTool_id() {
		return tool_id;
	}
	/**
	 * Set the tool_id value
	 * @param tool_id the tool_id to set
	 */
	public void setTool_id(Integer tool_id) {
		this.tool_id = tool_id;
	}
	/**
	 * Get the score value
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * Set the score value
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Skill [collaborator_id=" + collaborator_id + ", tool_id="
				+ tool_id + ", score=" + score + ", use_frequency="
				+ use_frequency + ", no_using_time=" + no_using_time + "]";
	}
	
}
