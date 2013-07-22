package com.novedia.talentmap.rest.utiils;

public class SkillTool {
	
	/**
	 * toolId
	 */
	private Integer toolId;
	
	/**
	 * toolName
	 */
	private String toolName;
	
	/**
	 * toolScore
	 */
	private Integer toolScore;
	
	/**
     * Frequency of competence
     */
    private Integer useFrequency;

    /**
     * Frequency of competence not use
     */
    private Integer noUsingTime;

    
    /**
     * Get the toolId
     * @return an Integer 
     */
	public Integer getToolId() {
		return toolId;
	}

	/**
	 * Set the toolId
	 * @param toolId toolId to set
	 */
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	/**
	 * Get the ToolNama
	 * @return the name of tool
	 */
	public String getToolName() {
		return toolName;
	}

	/**
	 * Set the toolName
	 * @param toolName toolName to set
	 */
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	/**
	 * Get the toolScore
	 * @return an Integer
	 */
	public Integer getToolScore() {
		return toolScore;
	}

	/**
	 * Set the toolScoe
	 * @param toolScore the toolScore to set
	 */
	public void setToolScore(Integer toolScore) {
		this.toolScore = toolScore;
	}

	/**
	 * Get the use Frequency
	 * @return an integer
	 */
	public Integer getUseFrequency() {
		return useFrequency;
	}

	/**
	 * Set the useFrequency
	 * @param useFrequency useFrequency to set
	 */
	public void setUseFrequency(Integer useFrequency) {
		this.useFrequency = useFrequency;
	}

	/**
	 * Get the not using time of tool
	 * @return an Integer
	 */
	public Integer getNoUsingTime() {
		return noUsingTime;
	}

	/**
	 * Set the not using time of tool
	 * @param noUsingTime not using time to set
	 */
	public void setNoUsingTime(Integer noUsingTime) {
		this.noUsingTime = noUsingTime;
	}
	
	
	
	
	

}
