package com.novedia.talentmap.model.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a frequency option.
 * 
 * @author j.maquin
 * 
 */
public class Frequency {

    /**
     * The id
     */
    private Integer id;

    /**
     * The name
     */
    private String name;

    /**
     * Get the frequency identifier
     * 
     * @return id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Set the frequency identifier
     * 
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Build an immutable Frequency entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    public Frequency(final Builder builder) {
	this.id = builder.id;
	this.name = builder.name;

    }

    public Frequency() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	HashCodeBuilder hBuilder = new HashCodeBuilder();
	hBuilder.append(this.getId());
	// hBuilder.append(this.getTitle());
	// hBuilder.append(this.getClient());
	return hBuilder.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}

	if (!(obj instanceof Frequency)) {
	    return false;
	}
	Frequency comparedObj = (Frequency) obj;
	EqualsBuilder ebuilder = new EqualsBuilder();
	ebuilder.append(this.getId(), comparedObj.getId());
	// ebuilder.append(this.getClient(), comparedObj.getClient());
	// ebuilder.append(this.getTitle(), comparedObj.getTitle());
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
     * Inner builder class.
     * 
     * @author j.maquin
     */
    public static class Builder {

	/**
	 * Frequency identifier
	 */
	private Integer id;

	/**
	 * Frequency name
	 */
	private String name;

	/**
	 * Set id
	 * 
	 * @param id
	 *            Frequency's identifier
	 * @return the builder
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * Set name
	 * 
	 * @param name
	 *            the Frequency's name
	 * @return the builder
	 */
	public Builder name(final String name) {
	    this.name = name;
	    return this;
	}

	/**
	 * Build an immutable instance of Frequency.
	 * 
	 * @return Frequency
	 */
	public Frequency build() {
	    return new Frequency(this);
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
