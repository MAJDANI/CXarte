package com.novedia.talentmap.rest.utiils;

public class SkillTool {
	
	/**
	 * 
	 */
	private Integer toolId;
	
	/**
	 * 
	 */
	private String toolName;
	
	/**
	 * 
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
     * 
     * @return
     */
	public Integer getToolId() {
		return toolId;
	}

	/**
	 * 
	 * @param toolId
	 */
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	/**
	 * 
	 * @return
	 */
	public String getToolName() {
		return toolName;
	}

	/**
	 * 
	 * @param toolName
	 */
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getToolScore() {
		return toolScore;
	}

	/**
	 * 
	 * @param toolScore
	 */
	public void setToolScore(Integer toolScore) {
		this.toolScore = toolScore;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getUseFrequency() {
		return useFrequency;
	}

	/**
	 * 
	 * @param useFrequency
	 */
	public void setUseFrequency(Integer useFrequency) {
		this.useFrequency = useFrequency;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getNoUsingTime() {
		return noUsingTime;
	}

	/**
	 * 
	 * @param noUsingTime
	 */
	public void setNoUsingTime(Integer noUsingTime) {
		this.noUsingTime = noUsingTime;
	}
	
	
	
	
	

}
