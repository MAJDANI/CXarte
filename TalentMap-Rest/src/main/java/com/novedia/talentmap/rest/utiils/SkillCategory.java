package com.novedia.talentmap.rest.utiils;

import java.util.List;

public class SkillCategory {
	
	private Integer categoryId;
	
	private String categoryName;
	
	private List<SkillConcept> concepts;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<SkillConcept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<SkillConcept> concepts) {
		this.concepts = concepts;
	}
	
	
	
	

}
