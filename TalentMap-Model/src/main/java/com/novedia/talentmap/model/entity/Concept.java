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
	private Double score;
	
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
		this.score = builder.score;
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
	 * Set the score linked to this concept
	 * 
	 * @param score
	 */
	public void setScore(double score) {
		this.score = score;
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
	public void setCategory(Category category) {
		this.category = category;
	}

	// ------------------------------------------
	// ------------ OVERRIDEN METHODS -----------
	// ------------------------------------------
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ").append("name=")
				.append(getName()).append(", ").append("category=")
				.append(getCategory().getName()).append("]");
		return strBld.toString();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
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
	 * Inner builder class.
	 * 
	 * @author j.marie-sainte
	 */
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
	 * Get the concept identifier
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the concept identifier
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
