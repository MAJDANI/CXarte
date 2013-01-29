package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


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
	 * Colleague associated the mission
	 */
	private Colleague colleague;

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
	 * Build the class Mission.java
	 */
	public Mission() {
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
	 * Set the mission id
	 * 
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the colleague
	 * 
	 * @return the colleague entity
	 */
	public Colleague getColleague() {
		return colleague;
	}
	
	/**
	 * Set colleague value
	 * 
	 * @param colleague
	 */
	public void setColleague(Colleague colleague) {
		this.colleague = colleague;
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
	 * Set the client value
	 * 
	 * @param client
	 *            the client to set
	 */
	private void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Get the startDate value
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set the startDate value
	 * 
	 * @param startDate
	 *            the startDate to set
	 */
	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get the endDate value
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set the endDate value
	 * 
	 * @param endDate
	 *            the endDate to set
	 */
	private void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * Set the name value
	 * 
	 * @param name
	 *            the name to set
	 */
	private void setTitle(String title) {
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
	 * Set the place value
	 * 
	 * @param place
	 *            the place to set
	 */
	private void setPlace(String place) {
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
	 * Set the notes
	 * 
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
		strBld.append("[colleague=").append(getColleague()).append(", ");
		strBld.append("[name=").append(getTitle()).append(", ");
		strBld.append("[client=").append(getClient()).append("]");
		return strBld.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hBuilder = new HashCodeBuilder();
		hBuilder.append(this.getId());
		hBuilder.append(this.getColleague().getId());
		hBuilder.append(this.getTitle());
		hBuilder.append(this.getClient());
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
		ebuilder.append(this.getClient(), comparedObj.getClient());
		ebuilder.append(this.getTitle(), comparedObj.getTitle());
		return ebuilder.isEquals();
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
	private Mission(final Builder builder) {
		
		this.id = builder.id;
		this.colleague = builder.colleague;
		this.title = builder.title;
		this.place = builder.place;
		this.client = builder.client;
		this.notes = builder.notes;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
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
		private Colleague colleague;

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
		 * Set the mission id
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
		 * Set the colleague
		 * 
		 * @param colleague
		 *            the linked colleague
		 *            
		 * @return the builder
		 */
		public Builder colleague(final Colleague colleague) {
			this.colleague = colleague;
			return this;
		}

		/**
		 * Set the mission title
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
		 * Set place of the mission
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
		 * Set the client name
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
		 * Set the notes about the mission
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
		 * Set the start date
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
		 * Set the end date
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
		 * Build an immutable instance of mission.
		 * 
		 * @return mission
		 */
		public Mission build() {
			return new Mission(this);
		}
	}
}
