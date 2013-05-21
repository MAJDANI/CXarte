package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a Talent Map category.
 * 
 * @author j.marie-sainte
 */
@SuppressWarnings({ "unused" })
public class Category implements Serializable {

    /**
     * Serialization identifier.
     */
    private static final long serialVersionUID = -7751758709240675365L;

    /**
     * The category id.
     */
    private Integer id;

    /**
     * The category name.
     */
    private String name;

    /**
     * Default constructor
     */
    public Category() {
    }

    /**
     * Get the category id value.
     * 
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Set the id value.
     * 
     * @param id
     *            the id to set
     */
    private void setId(Integer id) {
	this.id = id;
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
    private void setName(String name) {
	this.name = name;
    }

    // ------------------------------------------
    // ------------ OVERRIDEN METHODS -----------
    // ------------------------------------------

    @Override
    public String toString() {
	StringBuilder strBld = new StringBuilder();
	strBld.append("[id=").append(getId()).append(", ");
	strBld.append("name=").append(getName()).append("]");
	return strBld.toString();
    }

    @Override
    public boolean equals(final Object o) {
	if (o == null) {
	    return false;
	}

	if (!(o instanceof Category)) {
	    return false;
	}

	Category comparedObj = (Category) o;
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

    /**
     * Static constructor for this class.
     * 
     * @return a builder instance
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * Build a category entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    private Category(final Builder builder) {
	this.id = builder.id;
	this.name = builder.name;
    }

    /**
     * Inner builder class.
     * 
     * @author j.marie-sainte
     */
    public static final class Builder {

	/**
	 * The category identifier.
	 */
	private Integer id;

	/**
	 * The category name.
	 */
	private String name;

	/**
	 * Constructor with parameter id.
	 * 
	 * @param id
	 *            the category identifier
	 * @return the builder
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * Constructor with parameter name
	 * 
	 * @param name
	 *            the category name
	 * @return the builder
	 */
	public Builder name(final String name) {
	    this.name = name;
	    return this;
	}

	/**
	 * Build category
	 * 
	 * @return category
	 */
	public Category build() {
	    return new Category(this);
	}
    }
}