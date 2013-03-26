package com.novedia.talentmap.model.entity;

import java.io.Serializable;

/**
 * This entity represents an union between a mission and a tool.
 * @author maquin
 *
 */

public class MissionTool implements Serializable{

	/**
	 *  Serialization identifier
	 */
	private static final long serialVersionUID = 495162391337345554L;
	
	/**
	 * The mission id
	 */
	private Integer missionId;
	
	/**
	 * The tool id
	 */
	private Integer toolId;

	/**
	 * Get the mission id
	 * 
	 * @return missionId
	 */
	public Integer getMissionId() {
		return missionId;
	}

	/**
	 * Set the mission id
	 * 
	 * @param missionId
	 */
	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

	/**
	 * Get the tool id
	 * 
	 * @return toolId
	 */
	
	/**
	 * Set the tool id
	 * 
	 * @param toolId
	 */
	public Integer getToolId() {
		return toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

}
