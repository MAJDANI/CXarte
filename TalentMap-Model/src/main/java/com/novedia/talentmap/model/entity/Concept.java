package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a Talent Map concept.
 * 
 * @author j.marie-sainte
 */
public class Concept implements Serializable {

	/** 
	 * Serialization identifier 
	 */
	private static final long serialVersionUID = 2105607383674477226L;

	/**
	 *  The concept identifier.
	 */
	private Integer id;

	/**
	 * The concept name.
	 */
	private String name;

	/**
	 * The category this concept is a part of.
	 */
	private Category category;

	/** 
	 * The score 
	 */
	private double score;
	
	/**
	 * default constructor
	 */
	public Concept(){
		super ();
	}
	
	/**
	 * Build an immutable concept entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public Concept(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.category = builder.category;
	}

	/**
	 * Get the name value
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name value
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * set score
	 * @param conceptScore
	 */
	public void setScore(double conceptScore) {
		this.score = conceptScore;
	}
	
	/**
	 * Get score
	 * @return
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Get the category value
	 * 
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set the category value
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	// ------------------------------------------
	// ------------ OVERRIDEN METHODS -----------
	// ------------------------------------------

	@Override
	public String toString() {
		
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ").append("name=")
				.append(getName()).append(", ").append("category=")
				.append(getCategory().getName()).append("]");
		return strBld.toString();
	}

	@Override
	public boolean equals(Object o) {
		
		if (o == null) {
			return false;
		}

		if (!(o instanceof Concept)) {
			return false;
		}

		Concept comparedObj = (Concept) o;
		EqualsBuilder ebuilder = new EqualsBuilder();
		ebuilder.append(this.getId(), comparedObj.getId());
		return ebuilder.isEquals();
	}

	@Override
	public int hashCode() {
		
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		return hashBuilder.hashCode();
	}

	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------

	public static class Builder {

		/**
		 * The concept identifier
		 */
		private Integer id;

		/**
		 * The concept name
		 */
		private String name;
		
		/**
		 * The category this concept is a part of.
		 */
		private Category category;
		
		/**
		 * set id
		 * @param id
		 * @return
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * set name
		 * @param name
		 * @return
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * set category
		 * @param category
		 * @return
		 */
		public Builder category (final Category category) {
			this.category = category;
			return this;
		}
		
		/**
		 * Build an immutable instance of concept.
		 * 
		 * @return concept
		 */
		public Concept build() {
			return new Concept(this);
		}
		
		/**
		 * Static constructor for this class.
		 * 
		 * @return a builder instance
		 */
		public static Builder builder() {
			return new Builder();
		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
