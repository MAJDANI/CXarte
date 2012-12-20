package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a Talent Map tool.
 * 
 * A tool is the finest level of skill. A tool could be, from a
 * technical view, a Framework like Spring, Vaadin an so on, it could be also
 * any kind of software using in the realization of a project (ERP, BDD, etc.)
 * 
 * @author j.marie-sainte 
 */
public class Tool implements Serializable, Comparable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -6638545060221928596L;

	/**
	 * Identifier of this tool.
	 */
	private Integer id;

	/**
	 * The tool name.
	 */
	private String name;

	/**
	 * The concept this tool is a part of.
	 */
	private Concept concept;
	
	/**
	 * Default constructor
	 */
	public Tool(){
		
	}
	
	/**
	 * Build an immutable tool entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public Tool(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.concept = builder.concept;
	}
	
	/**
	 * Get the identifier.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the identifier.
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the matching concept.
	 * 
	 * @return concept
	 */
	public Concept getConcept() {
		return concept;
	}
	
	/**
	 * Set the matching concept.
	 * 
	 * @param concept
	 */
	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	/**
	 * Get the name value.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name value.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
				.append(getName()).append(", ").append("Concept=")
				.append(getConcept().getName()).append("]");
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

		if (!(o instanceof Tool)) {
			return false;
		}

		Tool comparedObj = (Tool) o;
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

	/**
	 * Method to compare two Tools (name comparison)
	 */
	@Override
	public int compareTo(Object o) {
		Tool toolToCompare = (Tool)o; 
		int result = this.getName().compareToIgnoreCase(toolToCompare.getName());
		return result;
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
		 * The tool identifier.
		 */
		private Integer id;

		/**
		 * The tool name.
		 */
		private String name;
		
		/**
		 * The concept this tool is a part of.
		 */
		private Concept concept;
		
		/**
		 * Set the tool identifier
		 * 
		 * @param id
		 * @return the builder
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}
		
		/**
		 * Set the tool name
		 * 
		 * @param name
		 * @return the builder
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * Set the concept 
		 * 
		 * @param concept
		 * @return the builder
		 */
		public Builder concept(final Concept concept){
			this.concept = concept;
			return this;
		}
		
		/**
		 * Build an immutable instance of tool.
		 * 
		 * @return a tool
		 */
		public Tool build() {
			return new Tool(this);
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

}
