package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.novedia.talentmap.model.json.serialize.JsonDateSerializer;

/**
 * This entity represents a mission.
 * 
 * @author e.moumbe
 * 
 */
public class Mission implements Serializable {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = 6695477652936529697L;

    /**
     * The mission id
     */
    private Integer id;

    /**
     * The colleague id
     */
    private Integer colleagueId;

    /**
     * Mission title
     */
    private String title;

    /**
     * Mission take place
     */
    private String place;

    /**
     * The mission customer
     */
    private Client client;

    /**
     * Information on this mission
     */
    private String notes;

    /**
     * Start date of the mission
     */
    private Date startDate;

    /**
     * End date of the mission
     */
    private Date endDate;
    
    /**
     * Role of the colleague during this mission
     */
    private String role;
    
    /**
     * Tools of the mission
     */
    private List<Tool> tools;

    /**
     * Build the class Mission.java
     */
    public Mission() {
    }

    /**
     * get tools of the mission
     * 
     * @return the tools
     */
    public List<Tool> getTools() {
	return tools;
    }

    /**
     * set tools of the mission
     * 
     * @param tools
     *            the tools to set
     */
    public void setTools(List<Tool> tools) {
	this.tools = tools;
    }

    /**
     * Get the mission id
     * 
     * @return id
     */
    public Integer getId() {
	return id;
    }

    /**
     * List the mission id
     * 
     * @param id
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Get the client value
     * 
     * @return the client
     */
    public Client getClient() {
	return client;
    }

    /**
     * List the client value
     * 
     * @param client
     *            the client to set
     */
    public void setClient(Client client) {
	this.client = client;
    }

    /**
     * Get the startDate value
     * 
     * @return the startDate
     */
     @JsonSerialize(using = JsonDateSerializer.class)
    public Date getStartDate() {
	return startDate;
    }

    /**
     * List the startDate value
     * 
     * @param startDate
     *            the startDate to set
     */
     @JsonSerialize(using = JsonDateSerializer.class)
    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    /**
     * Get the endDate value
     * 
     * @return the endDate
     */
     @JsonSerialize(using = JsonDateSerializer.class)
    public Date getEndDate() {
	return endDate;
    }

    /**
     * List the endDate value
     * 
     * @param endDate
     *            the endDate to set
     */
     @JsonSerialize(using = JsonDateSerializer.class)
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

     
    /**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
     * Get the name value
     * 
     * @return the name
     */
    public String getTitle() {
	return title;
    }

    /**
     * List the name value
     * 
     * @param name
     *            the name to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Get the place value
     * 
     * @return the place
     */
    public String getPlace() {
	return place;
    }

    /**
     * List the place value
     * 
     * @param place
     *            the place to set
     */
    public void setPlace(String place) {
	this.place = place;
    }

    /**
     * Get the notes
     * 
     * @return the notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * List the notes
     * 
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * Get the colleague id
     * 
     * @return
     */
    public Integer getColleagueId() {
	return colleagueId;
    }

    /**
     * List the colleague id
     * 
     * @param colleagueId
     */
    public void setColleagueId(Integer colleagueId) {
	this.colleagueId = colleagueId;
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
	strBld.append("colleagueId=").append(getColleagueId()).append(", ");
	strBld.append("title=").append(getTitle()).append(", ");
	strBld.append("place=").append(getPlace()).append(", ");
	strBld.append("client=").append(getClient()).append(", ");
	strBld.append("notes=").append(getNotes()).append(", ");
	strBld.append("startDate=").append(getStartDate()).append(", ");
	strBld.append("endDate=").append(getEndDate()).append(", ");
	strBld.append("role=").append(getRole()).append("]");
	return strBld.toString();
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

	if (!(obj instanceof Mission)) {
	    return false;
	}
	Mission comparedObj = (Mission) obj;
	EqualsBuilder ebuilder = new EqualsBuilder();
	ebuilder.append(this.getId(), comparedObj.getId());
	// ebuilder.append(this.getClient(), comparedObj.getClient());
	// ebuilder.append(this.getTitle(), comparedObj.getTitle());
	return ebuilder.isEquals();
    }

    // --------------------------------------
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
     * Build an immutable mission entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    private Mission(final Builder builder) {

	this.id = builder.id;
	this.colleagueId = builder.colleagueId;
	this.title = builder.title;
	this.place = builder.place;
	this.client = builder.client;
	this.notes = builder.notes;
	this.startDate = builder.startDate;
	this.endDate = builder.endDate;
	this.role = builder.role;
	this.tools = builder.tools;
    }

    /**
     * Inner builder class.
     * 
     * @author j.marie-sainte
     */
    public static final class Builder {

	/**
	 * The mission id
	 */
	private Integer id;

	/**
	 * Colleague associated the mission
	 */
	private Integer colleagueId;

	/**
	 * Mission name
	 */
	private String title;

	/**
	 * Mission take place
	 */
	private String place;

	/**
	 * The mission customer
	 */
	private Client client;

	/**
	 * Information on this mission
	 */
	private String notes;

	/**
	 * Start date of the mission
	 */
	private Date startDate;

	/**
	 * End date of the mission
	 */
	private Date endDate;

    /**
     * Role of the colleague during this mission
     */
    private String role;

	/**
	 * tools of the mission
	 */
	private List<Tool> tools;

	/**
	 * List the mission id
	 * 
	 * @param id
	 *            mission identifier
	 * 
	 * @return the builder
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * List the colleague
	 * 
	 * @param colleague
	 *            the linked colleague
	 * 
	 * @return the builder
	 */
	public Builder colleagueId(final Integer colleagueId) {
	    this.colleagueId = colleagueId;
	    return this;
	}

	/**
	 * List the mission title
	 * 
	 * @param title
	 *            the mission title
	 * 
	 * @return the builder
	 */
	public Builder title(final String title) {
	    this.title = title;
	    return this;
	}

	/**
	 * List place of the mission
	 * 
	 * @param place
	 *            the mission's place
	 * 
	 * @return the builder
	 */
	public Builder place(final String place) {
	    this.place = place;
	    return this;
	}

	/**
	 * List the client name
	 * 
	 * @param client
	 *            the client name
	 * 
	 * @return the builder
	 */
	public Builder client(final Client client) {
	    this.client = client;
	    return this;
	}

	/**
	 * List the notes about the mission
	 * 
	 * @param notes
	 *            the comments about the mission
	 * @return the builder
	 */
	public Builder notes(final String notes) {
	    this.notes = notes;
	    return this;
	}

	/**
	 * List the start date
	 * 
	 * @param startDate
	 *            the mission's start date
	 * @return the builder
	 */
	public Builder startDate(final Date startDate) {
	    this.startDate = startDate;
	    return this;
	}

	/**
	 * List the end date
	 * 
	 * @param endDate
	 *            the mission's end date
	 * @return the builder
	 */
	public Builder endDate(final Date endDate) {
	    this.endDate = endDate;
	    return this;
	}

	/**
	 * The role
	 * 
	 * @param role
	 *            the colleague's role in the mission
	 * @return the builder
	 */
	public Builder role(final String role) {
	    this.role = role;
	    return this;
	}

	/**
	 * List the tools
	 * 
	 * @param tools
	 *            the mission's tools
	 * @return the builder
	 */
	public Builder tools(final List<Tool> tools) {
	    this.tools = tools;
	    return this;
	}

	/**
	 * Build an immutable instance of mission.
	 * 
	 * @return mission
	 */
	public Mission build() {
	    return new Mission(this);
	}
    }
}
