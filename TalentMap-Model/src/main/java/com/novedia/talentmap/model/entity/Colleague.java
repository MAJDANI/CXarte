package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This entity represents a coworker.
 * 
 * @author e.moumbe
 */
public class Colleague implements Serializable {
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -1323210979848908088L;
	
	/**
	 * entity indentify
	 */
	protected Integer id;
	
	/**
	 * manager id
	 */
	private Integer managerId;
	
	/**
	 * Profile id
	 */
	protected Integer profileId;
	
	/**
	 * Colleague last nane
	 */
	protected String lastName;
	
	/**
	 * Colleague first nane
	 */
	protected String firstName;
	
	/**
	 * Colleague email
	 */
	protected String email;
	
	/**
	 * Colleague phone number
	 */
	protected String phone;
	
	/**
	 * Colleague employement date
	 */
	protected Date employmentDate;
	
	/**
	 * Colleague experience
	 */
	protected Integer experience;
	
	/**
	 * Colleague job name
	 */
	protected String businessEngineer;
		
	/**
	 *  Missions
	 */
	private ArrayList<Mission> missions;
	
	/** 
	 * Tools 
	 */
	private ArrayList<Tool> tools;
	
	/**
	 * Default Constructor
	 */
	public Colleague() {
		
	}
	
	/**
	 * Build an immutable category entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public Colleague(final Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.profileId = builder.profileId;
		this.phone = builder.phone;
		this.experience = builder.experience;
		this.managerId = builder.managerId;
		this.lastName = builder.lastName;
		this.employmentDate = builder.employmentDate;
		this.businessEngineer = builder.businessEngineer;
		this.email = builder.email;
		this.missions = builder.missions;
		this.tools = builder.tools;
	}
	
	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------
	
	/**
	 * Inner builder class.
	 * 
	 * @author j.marie-sainte
	 */
	public static class Builder {
		
		/**
		 * Colleague identifier
		 */
		private Integer id;

		/**
		 * Colleague firstName
		 */
		private String firstName;

		/**
		 * Colleague Profile id
		 */
		private Integer profileId;

		/**
		 * Colleague phone
		 */
		private String phone;
		
		/**
		 * Colleague experience
		 */
		private Integer experience;

		/**
		 * Colleague manager id
		 */
		private Integer managerId;

		/**
		 * Colleague last name
		 */
		protected String lastName;

		/**
		 * Colleague employment date
		 */
		protected Date employmentDate;

		/**
		 * Colleague business engineer
		 */
		protected String businessEngineer;

		/**
		 * Colleague email
		 */
		protected String email;
		
		/**
		 * List of tools mastered by Colleague
		 */
		public ArrayList<Tool> tools;
		
		/**
		 * List of mission performed by Colleague
		 */
		public ArrayList<Mission> missions;
		
		/**
		 * Set id
		 * 
		 * @param id the coworker's identifier
		 * 
		 * @return the builder
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Set first name
		 * 
		 * @param firstName the coworker's first name
		 * @return the builder
		 */
		public Builder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * Set profileId
		 * 
		 * @param profileId the coworker's profile
		 * @return the builder
		 */
		public Builder profileId(final Integer profileId) {
			this.profileId = profileId;
			return this;
		}
		
		/**
		 * Set phone
		 * 
		 * @param phone the coworker's phone
		 * @return the builder
		 */
		public Builder phone (final String phone) {
			this.phone = phone;
			return this;
		}
		
		/**
		 * Set experience
		 * 
		 * @param experience the coworker's experience
		 * @return the builder
		 */
		public Builder experience (final Integer experience) {
			this.experience = experience;
			return this;
		}
		
		/**
		 * Set manager
		 * 
		 * @param managerId the coworker's manager 
		 * @return the builder
		 */
		public Builder managerId (final Integer managerId) {
			this.managerId = managerId;
			return this;
		}
		
		/**
		 * Set last name
		 * 
		 * @param lastName the coworker's last name
		 * @return the builder
		 */
		public Builder lastName (final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		/**
		 * Set employment date
		 * 
		 * @param employmentDate the coworker's employment date
		 * @return the builder
		 */
		public Builder employmentDate (final Date employmentDate) {
			this.employmentDate = employmentDate;
			return this;
		}
		
		/**
		 * Set business engineer
		 * @param businessEngineer the coworker's business engineer
		 * 
		 * @return the builder
		 */
		public Builder businessEngineer (final String businessEngineer) {
			this.businessEngineer = businessEngineer;
			return this;
		}
		
		/**
		 * Set email
		 * 
		 * @param email the coworker's email
		 * @return
		 */
		public Builder email (final String email) {
			this.email = email;
			return this;
		}
		
		/**
		 * Build an immutable instance of concept.
		 * 
		 * @return concept
		 */
		public Colleague build() {
			return new Colleague(this);
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

	/**
	 * Get the list of the coworker's missions
	 * 
	 * @return the mission
	 */
	public ArrayList<Mission> getMission() {
		return missions;
	}

	/**
	 * Set the the coworker's mission list
	 * 
	 * @param mission
	 *            the mission to set
	 */
	public void setMission(ArrayList<Mission> mission) {
		this.missions = mission;
	}

	/**
	 * Get the the coworker's list of tools
	 * 
	 * @return the tool
	 */
	public ArrayList<Tool> getTool() {
		return tools;
	}

	/**
	 * Set the coworker's list of tools
	 * 
	 * @param tool
	 *            the tool to set
	 */
	public void setTool(ArrayList<Tool> tool) {
		this.tools = tool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[profil_id=").append(getId()).append(", ");
		strBld.append("[first_name=").append(getFirstName()).append(", ");
		strBld.append("[last_name=").append(getLastName()).append(", ");
		strBld.append("[email=").append(getEmail()).append(", ");
		strBld.append("[business_engineer=").append(getBusinessEngineer())
				.append(", ");
		strBld.append("experience").append(getExperience()).append("] ");
		return strBld.toString();
	}
		
	/**
	 * hash code method
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder();
		hashBuilder.append(this.getId());
		hashBuilder.append(this.getFirstName());
		hashBuilder.append(this.getLastName());
		
		return hashBuilder.hashCode();
	}

	/**
	 * Override equals, because we are need to compare collab by id
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj != null && !(obj instanceof Colleague)) {
			return false;
		}

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		Colleague collab = (Colleague) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.getId(), collab.getId()).isEquals();
	}

	/**
	 * Get the coworker's identifier
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the coworker's identifier
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the coworker's manager
	 * 
	 * @return the managerId
	 */
	public Integer getManagerId() {
		return managerId;
	}

	/**
	 * Set the coworker's manager
	 * 
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	/**
	 * Get the coworker's profile
	 * 
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * Set the coworker's profile
	 * 
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	/**
	 * Get the coworker's last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the coworker's last name 
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the coworker's first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the coworker's first name
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the the coworker's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the coworker's email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the coworker's employment date
	 * 
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * Set the coworker's employment date
	 * 
	 * @param employmentDate the employmentDate to set
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * Get the coworker's experience
	 * 
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * Set the coworker's experience
	 * 
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * Get the the coworker's business engineer
	 * 
	 * @return the businessEngineer
	 */
	public String getBusinessEngineer() {
		return businessEngineer;
	}

	/**
	 * Set the business engineer
	 * 
	 * @param businessEngineer the businessEngineer to set
	 */
	public void setBusinessEngineer(String businessEngineer) {
		this.businessEngineer = businessEngineer;
	}

	/**
	 * Get the coworker's phone number
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the coworker's phone number
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}