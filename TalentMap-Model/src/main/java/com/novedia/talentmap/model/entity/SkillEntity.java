package com.novedia.talentmap.model.entity;

/**
 * A skill entity.
 * @author j.marie-sainte
 *
 */
public class SkillEntity {
	
	/**
	 * Level of skill
	 */
	private Integer level;
	
	/**
	 * Domain of skill
	 */
	private String domain;
	
	/**
	 * Type of skill
	 */
	private Integer type;
	
	
	//===================//
	// Getters / Setters //
	//===================//
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
