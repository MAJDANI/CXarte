package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a Talent Map concept.
 * 
 * @author j.marie-sainte
 */
@SuppressWarnings("unused")
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
	private Double score;

	/**
	 * Default constructor
	 */
	public Concept(){
	}
	
	/**
	 * Get the concept id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the concept id.
	 * 
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
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
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get score linked to this concept
	 * 
	 * @return score
	 */
	public Double getScore() {
		return score;
	}
	
	/**
	 * Set the score for the concept
	 * 
	 * @param score
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * Get the category
	 * 
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set the category
	 * 
	 * @param category
	 *            the category to set
	 */
	private void setCategory(Category category) {
		this.category = category;
	}

	// ------------------------------------------
	// ------------ OVERRIDEN METHODS -----------
	// ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ").append("name=")
				.append(getName()).append(", ").append("category=")
				.append(getCategory().getName()).append("]");
		return strBld.toString();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		return hashBuilder.hashCode();
	}

	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------
	
	/**
	 * Static constructor for this class.
	 * 
	 * @return a builder instance
	 */
	public static Builder builder() {
		return new Builder();
	}
	
	/**
	 * Build an immutable concept entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	private Concept(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.category = builder.category;
		this.score = builder.score;
	}
	
	/**
	 * Inner builder class.
	 * 
	 * @author j.marie-sainte
	 */
	public static final class Builder {
		

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
		 * The concept score
		 */
		public Double score;
		
		/**
		 * Set the concept id
		 * 
		 * @param id concept identifier
		 * @return
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Set the concept name
		 * 
		 * @param name
		 * @return the builder
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * Set category matching this concept 
		 * 
		 * @param category
		 * @return the builder
		 */
		public Builder category (final Category category) {
			this.category = category;
			return this;
		}
		
		/**
		 * Set the score of this concept
		 * 
		 * @param score
		 * @return the builder
		 */
		public Builder score(final Double score){
			this.score = score;
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
	}
}
