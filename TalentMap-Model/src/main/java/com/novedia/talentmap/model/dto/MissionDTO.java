package com.novedia.talentmap.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.json.serialize.JsonDateSerializer;

/**
 * This entity represents a missionDto. A missionDto is a mission with a Set of
 * tools instead of List of tools.
 * 
 * @author j.maquin
 * 
 */
public class MissionDTO {

	
    /**
     * The missionDto id
     */
    private Integer id;

    /**
     * The colleague id
     */
    private Integer colleagueId;

    /**
     * MissionDto title
     */
    private String title;

    /**
     * MissionDto take place
     */
    private String place;

    /**
     * The mission customer
     */
    private Client client;

    /**
     * Information on this missionDto
     */
    private String notes;

    /**
     * Start date of the missionDto
     */
    private Date startDate;

    /**
     * End date of the missionDto
     */
    private Date endDate;

    /**
     * Tools of the missionDto
     */
    private Set<Tool> tools;

    public MissionDTO() {
    }

    /**
     * Get missionDto id
     * 
     * @return id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Set the missionDto id
     * 
     * @param id
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Get the colleague id
     * 
     * @return colleagueId
     */
    public Integer getColleagueId() {
	return colleagueId;
    }

    /**
     * Set the colleague id
     * 
     * @param colleagueId
     */
    public void setColleagueId(Integer colleagueId) {
	this.colleagueId = colleagueId;
    }

    /**
     * Get the title
     * 
     * @return title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set the title
     * 
     * @param title
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Get the place
     * 
     * @return place
     */
    public String getPlace() {
	return place;
    }

    /**
     * Set the place
     * 
     * @param place
     */
    public void setPlace(String place) {
	this.place = place;
    }

    /**
     * Get the client
     * 
     * @return client
     */
    public Client getClient() {
	return client;
    }

    /**
     * Set the client
     * 
     * @param client
     */
    public void setClient(Client client) {
	this.client = client;
    }

    /**
     * Get the comment
     * 
     * @return notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * Set the comment
     * 
     * @param notes
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * Get the start date
     * 
     * @return startDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getStartDate() {
	return startDate;
    }

    /**
     * Set the start date
     * 
     * @param startDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    /**
     * Get the end date
     * 
     * @return endDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getEndDate() {
	return endDate;
    }

    /**
     * Set the end date
     * 
     * @param endDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    /**
     * Get the Set of tool
     * 
     * @return tools
     */
    public Set<Tool> getTools() {
	return tools;
    }

    /**
     * Set the Set of tool
     * 
     * @param tools
     */
    public void setTools(Set<Tool> tools) {
	this.tools = tools;
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
     * Build an immutable missionDto entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    private MissionDTO(final Builder builder) {

	this.id = builder.id;
	this.colleagueId = builder.colleagueId;
	this.title = builder.title;
	this.place = builder.place;
	this.client = builder.client;
	this.notes = builder.notes;
	this.startDate = builder.startDate;
	this.endDate = builder.endDate;
	this.tools = builder.tools;
    }

    /**
     * Inner builder class.
     * 
     * @author j.marie-sainte
     */
    public static final class Builder {

	/**
	 * The missionDto id
	 */
	private Integer id;

	/**
	 * Colleague associated the missionDto
	 */
	private Integer colleagueId;

	/**
	 * MissionDto name
	 */
	private String title;

	/**
	 * MissionDto take place
	 */
	private String place;

	/**
	 * The missionDto customer
	 */
	private Client client;

	/**
	 * Information on this missionDto
	 */
	private String notes;

	/**
	 * Start date of the missionDto
	 */
	private Date startDate;

	/**
	 * End date of the missionDto
	 */
	private Date endDate;

	/**
	 * tools of the missionDto
	 */
	private Set<Tool> tools;

	public Builder mission(final Mission mission) {
	    this.id = mission.getId();
	    this.colleagueId = mission.getColleagueId();
	    this.title = mission.getTitle();
	    this.place = mission.getPlace();
	    this.client = mission.getClient();
	    this.notes = mission.getNotes();
	    this.startDate = mission.getStartDate();
	    this.endDate = mission.getEndDate();
	    Set<Tool> toolsSet = new HashSet<Tool>();
	    List<Tool> tools = mission.getTools();
	    if (tools.size() > 0) {
		for (Tool t : tools) {
		    toolsSet.add(t);
		}
		this.tools = toolsSet;
	    }
	    return this;
	}

	/**
	 * List the missionDto id
	 * 
	 * @param id
	 *            missionDto identifier
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
	 * List the missionDto title
	 * 
	 * @param title
	 *            the missionDto title
	 * 
	 * @return the builder
	 */
	public Builder title(final String title) {
	    this.title = title;
	    return this;
	}

	/**
	 * List place of the missionDto
	 * 
	 * @param place
	 *            the missionDto's place
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
	 * List the notes about the missionDto
	 * 
	 * @param notes
	 *            the comments about the missionDto
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
	 *            the missionDto's start date
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
	 *            the missionDto's end date
	 * @return the builder
	 */
	public Builder endDate(final Date endDate) {
	    this.endDate = endDate;
	    return this;
	}

	/**
	 * List the tools
	 * 
	 * @param tools
	 *            the missionDto's tools
	 * @return the builder
	 */
	public Builder tools(final Set<Tool> tools) {
	    this.tools = tools;
	    return this;
	}

	/**
	 * Build an immutable instance of mission.
	 * 
	 * @return missionDto
	 */
	public MissionDTO build() {
	    return new MissionDTO(this);
	}
    }
}
