package com.novedia.talentmap.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.Mission.Builder;

public class MissionDto {
	
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
	 * Tools of the mission
	 */
	private Set<Tool> tools;
	
	public MissionDto() {
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getColleagueId() {
		return colleagueId;
	}

	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Tool> getTools() {
		return tools;
	}

	public void setTools(Set<Tool> tools) {
		this.tools = tools;
	}
	
	//--------------------------------------
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
		private MissionDto(final Builder builder) {
			
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
			 * tools of the mission
			 */
			private Set<Tool> tools;

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
			 * @param client the client name
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
			 * List the tools
			 * 
			 * @param tools
			 *            the mission's tools
			 * @return the builder
			 */
			public Builder tools(final Set<Tool> tools) {
				this.tools = tools;
				return this;
			}
			
			/**
			 * Build an immutable instance of mission.
			 * 
			 * @return mission
			 */
			public MissionDto build() {
				return new MissionDto(this);
			}
		}
}

