package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.novedia.talentmap.model.entity.VSkill.Builder;

/**
 * This entity represents a skill.
 * 
 * @author j.marie-sainte
 *
 */
public class Skill implements Serializable{
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -4155288579083068658L;
	
	/**
	 * colleague id associated the skill
	 */
	private Integer colleagueId;
	
	// TODO : référence aux objets entant dans la relation
	// private Colleague colleague;

	/**
	 * tool id associated the skill
	 */
	private Integer tool_id;
	
	// TODO : référence aux objets entrant dans la relation
	// private Tool tool;
	
	/**
	 * th score associated the skill
	 */
	private Integer score;
	
	/**
	 * Frequency of competence
	 */
	private Integer use_frequency;
	
	/**
	 * Frequency of competence not use
	 */
	private Integer no_using_time;
	
	/**
	 * Average Score : calculated field
	 * TODO : champ provisoire, le temps de définir des objets (DTO)
	 * optimisés pour la vue. score, use_frequency, no_using_time
	 * et averageScore sont utilisés dans l'écran qui gère les compétences
	 * collaborateur
	 */
	private Integer averageScore;
	
	public Skill() {

	}
	
	/**
	 * Build the class Skill.java 
	 * @param colleagueId
	 * @param tool_id
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 */
	public Skill(Integer colleagueId, Integer tool_id, Integer score, Integer use_frequency, Integer no_using_time) {
		
		super();
		this.colleagueId = colleagueId;
		this.tool_id = tool_id;
		this.score = score;
		this.use_frequency = use_frequency;
		this.no_using_time = no_using_time;
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
	
	
	public Integer getColleagueId() {
		return colleagueId;
	}

	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}

	public Integer getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Integer averageScore) {
		this.averageScore = averageScore;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder(); 
		strBld.append("[colleagueId=").append(getColleagueId()).append(", ");
		strBld.append("tool_id=").append(getTool_id()).append(", ");
		strBld.append("score=").append(getScore()).append(", ");
		strBld.append("use_frequency=").append(getUse_frequency()).append(", ");
		strBld.append("no_using_time=").append(getNo_using_time()).append("]");
		return strBld.toString();	
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getTool_id());
		hashBuilder.append(this.getColleagueId());
		hashBuilder.append(this.getUse_frequency());
		return hashBuilder.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;			
		}

		if(!(obj instanceof Skill)){
			return false;
		}
	
		Skill comparedObj = (Skill)obj;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getTool_id(), comparedObj.getTool_id());
		ebuilder.append(this.getColleagueId(), comparedObj.getColleagueId());
		ebuilder.append(this.getUse_frequency(), comparedObj.getUse_frequency());
		return ebuilder.isEquals();
	}
	
	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------
	
	
	/**
	 * Static constructor for this class.
	 * @return a builder instance
	 */
	public static Builder builder(){
		return new Builder();
	}
	
	
	private Skill(final Builder builder){
		this.colleagueId = builder.colleagueId;
		this.tool_id = builder.tool_id;
		this.score = builder.score;
		this.use_frequency = builder.use_frequency;
		this.no_using_time = builder.no_using_time;
	}
	
	
	public static final class Builder{
		/**
		 * colleague id associated the skill
		 */
		private Integer colleagueId;
		/**
		 * tool id associated the skill
		 */
		private Integer tool_id;
		
		/**
		 * th score associated the skill
		 */
		private Integer score;
		
		/**
		 * Frequency of competence
		 */
		private Integer use_frequency;
		
		/**
		 * Frequency of competence not use
		 */
		private Integer no_using_time;

		
		
		/**
		 * Constructor with parameter colleagueId.
		 * @param colleagueId
		 * @return the builder
		 */
		public Builder colleagueId(final int colleagueId) {
			this.colleagueId = colleagueId;
			return this;
		}
		
		/**
		 * Constructor with parameter tool_id.
		 * @param tool_id
		 * @return the builder
		 */
		public Builder tool_id(final int tool_id) {
			this.tool_id = tool_id;
			return this;
		}
		
		/**
		 * Constructor with parameter score.
		 * @param score
		 * @return the builder
		 */
		public Builder score(final int score) {
			this.score = score;
			return this;
		}
		
		/**
		 * Constructor with parameter use_frequency.
		 * @param use_frequency
		 * @return the builder
		 */
		public Builder use_frequency(final int use_frequency) {
			this.use_frequency = use_frequency;
			return this;
		}
		
		/**
		 * Constructor with parameter no_using_time.
		 * @param no_using_time
		 * @return the builder
		 */
		public Builder no_using_time(final int no_using_time) {
			this.no_using_time = no_using_time;
			return this;
		}
		
		
		
		
		/**
		 * Build Skill
		 * @return Skill
		 */
		public Skill build (){
			return new Skill(this);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}