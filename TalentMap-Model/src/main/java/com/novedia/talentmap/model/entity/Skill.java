package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Skill implements Serializable{
	
	private String collaborator_id;
	private String tool_id;
	private Integer score;
	
	/**
	 * Get the collaborator_id value
	 * @return the collaborator_id
	 */
	public String getCollaborator_id() {
		return collaborator_id;
	}
	/**
	 * Set the collaborator_id value
	 * @param collaborator_id the collaborator_id to set
	 */
	public void setCollaborator_id(String collaborator_id) {
		this.collaborator_id = collaborator_id;
	}
	/**
	 * Get the tool_id value
	 * @return the tool_id
	 */
	public String getTool_id() {
		return tool_id;
	}
	/**
	 * Set the tool_id value
	 * @param tool_id the tool_id to set
	 */
	public void setTool_id(String tool_id) {
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
	
	
}
