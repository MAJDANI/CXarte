package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.novedia.talentmap.model.entity.Category.Builder;

/**
 * This entity represents a vSkill
 * 
 * @author e.moumbe
 * 
 */
public class VSkill implements Serializable {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = 1160292151144778521L;

    /**
     * category id associated the VSKill
     */
    private Integer categoryId;

    /**
     * category name associated the VSKill
     */
    private String categoryName;

    /**
     * concept id associated the VSKill
     */
    private Integer conceptId;

    /**
     * concept name associated the VSKill
     */
    private String conceptName;

    /**
     * tool id associated the VSKill
     */
    private Integer toolId;

    /**
     * tool name associated the VSKill
     */
    private String toolName;

    /**
     * Build the class VSkill.java
     * 
     * @param categoryName
     * @param conceptName
     * @param toolName
     */
    public VSkill(Integer categoryId, String categoryName, Integer conceptId,
	    String conceptName, Integer toolId, String toolName) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
	this.conceptId = conceptId;
	this.conceptName = conceptName;
	this.toolId = toolId;
	this.toolName = toolName;
    }

    /**
     * Build the class VSkill.java
     */
    public VSkill() {
	super();
    }

    /**
     * Get the toolId value
     * 
     * @return the toolId
     */
    public Integer getToolId() {
	return toolId;
    }

    /**
     * Set the toolId value
     * 
     * @param toolId
     *            the toolId to set
     */
    public void setToolId(Integer toolId) {
	this.toolId = toolId;
    }

    /**
     * Get the toolName value
     * 
     * @return the toolName
     */
    public String getToolName() {
	return toolName;
    }

    /**
     * Set the toolName value
     * 
     * @param toolName
     *            the toolName to set
     */
    public void setToolName(String toolName) {
	this.toolName = toolName;
    }

    /**
     * Get the CategoryId value
     * 
     * @return the CategoryId
     */
    public Integer getCategoryId() {
	return categoryId;
    }

    /**
     * Set the CategoryId value
     * 
     * @param CategoryId
     *            the CategoryId to set
     */
    public void setCategoryId(Integer categoryId) {
	this.categoryId = categoryId;
    }

    /**
     * Get the CategoryName value
     * 
     * @return the CategoryName
     */
    public String getCategoryName() {
	return categoryName;
    }

    /**
     * Set the CategoryName value
     * 
     * @param CategoryName
     *            the CategoryName to set
     */
    public void setCategoryName(String CategoryName) {
	this.categoryName = CategoryName;
    }

    /**
     * Get the conceptId value
     * 
     * @return the conceptId
     */
    public Integer getConceptId() {
	return conceptId;
    }

    /**
     * Set the conceptId value
     * 
     * @param conceptId
     *            the conceptId to set
     */
    public void setConceptId(Integer conceptId) {
	this.conceptId = conceptId;
    }

    /**
     * Get the conceptName value
     * 
     * @return the conceptName
     */
    public String getConceptName() {
	return conceptName;
    }

    /**
     * Set the conceptName value
     * 
     * @param conceptName
     *            the conceptName to set
     */
    public void setConceptName(String conceptName) {
	this.conceptName = conceptName;
    }

    /**
     * hash code method
     */
    @Override
    public int hashCode() {
	HashCodeBuilder hashBuilder = new HashCodeBuilder();
	hashBuilder.append(this.getCategoryName());
	hashBuilder.append(this.getToolName());
	hashBuilder.append(this.getConceptName());
	return hashBuilder.hashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}

	if (!(obj instanceof VSkill)) {
	    return false;
	}

	VSkill comparedObj = (VSkill) obj;
	EqualsBuilder ebuilder = new EqualsBuilder();
	ebuilder.append(this.getCategoryName(), comparedObj.getCategoryName());
	ebuilder.append(this.getToolName(), comparedObj.getToolName());
	ebuilder.append(this.getConceptName(), comparedObj.getConceptName());
	return ebuilder.isEquals();
    }

    /**
     * allowed to display attribute of the object VSkill
     */
    @Override
    public String toString() {

	StringBuilder strBld = new StringBuilder();
	strBld.append("[categoryId=").append(getCategoryId()).append(", ");
	strBld.append("categoryName=").append(getCategoryName()).append(", ");
	strBld.append("toolId=").append(getToolId()).append(", ");
	strBld.append("toolName=").append(getToolName()).append(", ");
	strBld.append("conceptId=").append(getConceptId()).append(",");
	strBld.append("conceptName=").append(getConceptName()).append("]");
	return strBld.toString();
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
     * Build a VSkill entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    @SuppressWarnings("unused")
    private VSkill(final Builder builder) {
	this.categoryId = builder.categoryId;
	this.categoryName = builder.categoryName;
	this.conceptId = builder.conceptId;
	this.conceptName = builder.conceptName;
	this.toolId = builder.toolId;
	this.toolName = builder.toolName;
    }

    /**
     * Inner builder class.
     * 
     * @author v.dibi
     * 
     */
    public static final class Builder {

	/**
	 * category id associated the VSKill
	 */
	private Integer categoryId;
	/**
	 * category name associated the VSKill
	 */
	private String categoryName;
	/**
	 * concept id associated the VSKill
	 */
	private Integer conceptId;
	/**
	 * concept name associated the VSKill
	 */
	private String conceptName;
	/**
	 * tool id associated the VSKill
	 */
	private Integer toolId;
	/**
	 * tool name associated the VSKill
	 */
	private String toolName;

	/**
	 * Constructor with parameter categoryId.
	 * 
	 * @param categoryId
	 * @return the builder
	 */
	public Builder categoryId(final Integer categoryId) {
	    this.categoryId = categoryId;
	    return this;
	}

	/**
	 * Constructor with parameter categoryName.
	 * 
	 * @param categoryName
	 * @return the builder
	 */
	public Builder categoryName(final String categoryName) {
	    this.categoryName = categoryName;
	    return this;
	}

	/**
	 * Constructor with parameter conceptName.
	 * 
	 * @param conceptName
	 * @return the builder
	 */
	public Builder conceptName(final String conceptName) {
	    this.conceptName = conceptName;
	    return this;
	}

	/**
	 * Constructor with parameter conceptId.
	 * 
	 * @param conceptId
	 * @return the builder
	 */
	public Builder conceptId(final Integer conceptId) {
	    this.conceptId = conceptId;
	    return this;
	}

	/**
	 * Constructor with parameter toolName.
	 * 
	 * @param toolName
	 * @return the builder
	 */
	public Builder toolName(final String toolName) {
	    this.toolName = toolName;
	    return this;
	}

	/**
	 * Constructor with parameter toolId.
	 * 
	 * @param toolId
	 * @return the builder
	 */
	public Builder toolId(final Integer toolId) {
	    this.toolId = toolId;
	    return this;
	}

	/**
	 * Build VSkill
	 * 
	 * @return VSkill
	 */
	public VSkill build() {
	    return new VSkill(this);
	}
    }
}