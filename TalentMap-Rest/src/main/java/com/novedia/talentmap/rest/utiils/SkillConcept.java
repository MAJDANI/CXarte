package com.novedia.talentmap.rest.utiils;

import java.util.List;

public class SkillConcept {
	
	private Integer conceptId;
	
	private String conceptName;
	
	private Integer conceptScore;
	
	private List<SkillTool> tools;

	public Integer getConceptId() {
		return conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public Integer getConceptScore() {
		return conceptScore;
	}

	public void setConceptScore(Integer conceptScore) {
		this.conceptScore = conceptScore;
	}

	public List<SkillTool> getTools() {
		return tools;
	}

	public void setTools(List<SkillTool> tools) {
		this.tools = tools;
	}
	
	
	
	

}
