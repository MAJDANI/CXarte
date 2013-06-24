package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a Talent Map tool.
 * 
 * @author e.moumbe
 * 
 */
public class Profile implements Serializable {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = 8766946810895701286L;

    /**
     * profile id
     */
    private Integer id;

    /**
     * profile type
     */
    private String type;

    /**
     * Default constructor
     */
    public Profile() {
    }

    /**
     * Get the id value
     * 
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Set the id value
     * 
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Get the profileType value
     * 
     * @return the profileType
     */
    public String getType() {
	return type;
    }

    /**
     * Set the profileType value
     * 
     * @param profileType
     *            the profileType to set
     */
    public void setType(String type) {
	this.type = type;
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
	strBld.append("[id=").append(getId()).append(", ");
	strBld.append("[type=").append(getType()).append("]");
	return strBld.toString();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}

	if (!(obj instanceof Profile)) {
	    return false;
	}

	Profile comparedObj = (Profile) obj;
	EqualsBuilder ebuilder = new EqualsBuilder();
	ebuilder.append(this.getId(), comparedObj.getId());
	ebuilder.append(this.getType(), comparedObj.getType());
	return ebuilder.isEquals();
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
     * Build an immutable profile entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    private Profile(final Builder builder) {
	this.id = builder.id;
	this.type = builder.type;
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
	private String type;

	/**
	 * Set the concept id
	 * 
	 * @param id
	 *            concept identifier
	 * @return
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * Set the concept id
	 * 
	 * @param id
	 *            concept identifier
	 * @return the builder
	 */
	public Builder type(final String type) {
	    this.type = type;
	    return this;
	}

	/**
	 * Build an immutable profile instance
	 * 
	 * @return profile
	 */
	public Profile build() {
	    return new Profile(this);
	}
    }
}
