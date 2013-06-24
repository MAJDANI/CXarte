package com.novedia.talentmap.store.impl;

import java.util.List;

/**
 * SkillParameter.
 */
public class SkillParameter {
	/**
	 * toolId.
	 */
	private Integer toolId;
	/**
	 * listCollaborators.
	 */
	private List<Integer> listCollaborators;
	/**
	 * listTools.
	 */
	private List<Integer> listTools;
	/**
	 * get tool by integer.
	 * 
	 * @return int
	 */
	/**
	 * colleagueId.
	 */
	private Integer managerId;

	public Integer getToolId() {
		return toolId;
	}

	/**
	 * set tool id.
	 * 
	 * @param toolId
	 */
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	/**
	 * @return a list id of collaborator.
	 */
	public List<Integer> getListCollaborators() {
		return listCollaborators;
	}

	/**
	 * @param listCollaborators
	 *            .
	 */
	public void setListCollaborators(List<Integer> listCollaborators) {
		this.listCollaborators = listCollaborators;
	}

	/**
	 * @return a list id of tool's id.
	 */
	public List<Integer> getListTools() {
		return listTools;
	}

	/**
	 * set a list of tool's id.
	 * 
	 * @param listTools
	 */
	public void setListTools(List<Integer> listTools) {
		this.listTools = listTools;
	}

	/**
	 * @return the manager id.
	 */
	public Integer getManagerId() {
		return managerId;
	}

	/**
	 * set a manager id.
	 * 
	 * @param managerId
	 */
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

}
