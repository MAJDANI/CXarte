package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.novedia.talentmap.model.entity.Colleague.Builder;

/**
 * This entity represents a Business Engineer.
 * 
 * @author v.guillemain
 */
public class BusinessEngineer implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 4993051373357256973L;

	/**
	 * Entity identifier
	 */
	protected Integer id;
	
	/**
	 * Business Engineer last name
	 */
	protected String name;
	
	/**
	 * Build an immutable category entity. the builder inner class for this
	 * entity
	 */
	public BusinessEngineer() {
	}

	/**
	 * Build an immutable Business Engineer entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public BusinessEngineer(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
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
	 * Inner builder class.
	 * 
	 * @author v.guillemain
	 */
	public static class Builder {
		
		/**
		 * Business Engineer identifier
		 */
		private Integer id;

		/**
		 * Business Engineer last name
		 */
		protected String name;

		/**
		 * Set id
		 * 
		 * @param id the Business Engineer's identifier
		 * @return the builder
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Set name
		 * 
		 * @param name the Business Engineer's name
		 * @return the builder
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}

		/**
		 * Build an immutable instance of BusinessEngineer.
		 * @return BusinessEngineer
		 */
		public BusinessEngineer build() {
			return new BusinessEngineer(this);
		}
	
		/**
		 * Static constructor for this class.
		 * @return a builder instance
		 */
		public static Builder builder() {
			return new Builder();
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("name=").append(getName()).append("] ");
		return strBld.toString();
	}

	/**
	 * Gets the Business Engineer's Id
	 * @return the id of the Business Engineer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the Business Engineer's Id
	 * @param id : the Business Engineer's Id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the Business Engineer's name
	 * @return the name of the Business Engineer
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Business Engineer's name
	 * @param name : the Business Engineer's name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof BusinessEngineer)){
			return false;
		}else {
			BusinessEngineer b = (BusinessEngineer) obj;
			return (this.id.equals(b.getId()) && this.name.equals(b.getName()));
		}
	}


}
