package com.novedia.talentmap.model.entity;

import java.io.Serializable;

/**
 * This entity represents a Business Engineer.
 * 
 * @author v.guillemain
 * @author j.marie-sainte
 */
public class BusinessEngineer implements Serializable {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = 4993051373357256973L;

    /**
     * Entity identifier
     */
    private Integer id;

    /**
     * Business Engineer first name
     */
    private String firstName;

    /**
     * Business Engineer last name
     */
    private String lastName;

    /**
     * Business Unit
     */
    private String businessUnit;

    /**
     * Default constructor
     */
    public BusinessEngineer() {

    }

    /**
     * Gets the Business Engineer's Id
     * 
     * @return the id of the Business Engineer
     */
    public Integer getId() {
	return id;
    }

    /**
     * Sets the Business Engineer's Id
     * 
     * @param id
     *            : the Business Engineer's Id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Gets the Business Engineer's name
     * 
     * @return the name of the Business Engineer
     */
    public String getFirstName() {
	return this.firstName;
    }

    /**
     * Sets the Business Engineer's firs name
     * 
     * @param firstName
     *            the Business Engineer's first name to set
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    /**
     * Get the business engineer's last name
     * 
     * @return lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * Set the last name
     * 
     * @param lastName
     *            the business engineer's last name
     */

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    /**
     * Get the business unit
     * 
     * @return businessUnit
     */

    public String getBusinessUnit() {
	return businessUnit;
    }

    /**
     * Set the business unit
     * 
     * @param businessUnit
     *            the business unit
     */

    public void setBusinessUnit(String businessUnit) {
	this.businessUnit = businessUnit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof BusinessEngineer)) {
	    return false;
	} else {
	    BusinessEngineer b = (BusinessEngineer) obj;
	    return (this.id.equals(b.getId()));
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	StringBuilder strBld = new StringBuilder();
	strBld.append("[id=").append(getId()).append(", ");
	strBld.append("firstName=").append(getFirstName()).append("] ");
	return strBld.toString();
    }

    /**
     * Build an immutable Business Engineer entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    public BusinessEngineer(final Builder builder) {
	this.id = builder.id;
	this.firstName = builder.firstName;
	this.lastName = builder.lastName;
	this.businessUnit = builder.firstName;
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
	 * Business Engineer first name
	 */
	private String firstName;

	/**
	 * Business Engineer last name
	 */
	private String lastName;

	/**
	 * The activity market of business engineer
	 */
	private String businessUnit;

	/**
	 * Set id
	 * 
	 * @param id
	 *            the Business Engineer's identifier
	 * @return the builder
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * Set firstName
	 * 
	 * @param name
	 *            the Business Engineer's name
	 * @return the builder
	 */
	public Builder firstName(final String name) {
	    this.firstName = name;
	    return this;
	}

	/**
	 * Set lastName
	 * 
	 * @param name
	 *            the Business Engineer's name
	 * @return the builder
	 */
	public Builder lastName(final String lastName) {
	    this.lastName = lastName;
	    return this;
	}

	/**
	 * Set lastName
	 * 
	 * @param name
	 *            the Business Engineer's name
	 * @return the builder
	 */
	public Builder businessUnit(final String businessUnit) {
	    this.businessUnit = businessUnit;
	    return this;
	}

	/**
	 * Build an immutable instance of BusinessEngineer.
	 * 
	 * @return BusinessEngineer
	 */
	public BusinessEngineer build() {
	    return new BusinessEngineer(this);
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
