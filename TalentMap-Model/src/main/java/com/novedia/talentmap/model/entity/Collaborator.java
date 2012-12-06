package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The bean that map table collaborator
 * @author moumbe
 *
 */
public class Collaborator implements Serializable {
	
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
	 * profile id
	 */
	protected Integer profileId;
	/**
	 * collaborator last nane
	 */
	protected String lastName;
	/**
	 * collaborator first nane
	 */
	protected String firstName;
	/**
	 * collaborator email
	 */
	protected String email;
	/**
	 * collaborator phone number
	 */
	protected String phone;
	/**
	 * collaborator employement date
	 */
	protected Date employmentDate;
	/**
	 * collaborator experience
	 */
	protected Integer experience;
	/**
	 * collaborator job name
	 */
	protected String businessEngineer;
	
	/** Mission */
	private ArrayList<Mission> mission;
	
	/** Tool */
	private ArrayList<Tool> tool;
	
	/**
	 * Default Constructor
	 */
	public Collaborator() {
	}
	
	/**
	 * Build an immutable category entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public Collaborator(final Builder builder) {
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
	}
	
	// -------------------------------------
	// ------------ BUILDER PART -----------
	// -------------------------------------

	public static class Builder {

		/**
		 * The collab identifier
		 */
		private Integer id;

		/**
		 * The collab firstName
		 */
		private String firstName;

		/**
		 * Profile id
		 */
		private Integer profileId;

		/**
		 * Phone
		 */
		private String phone;
		
		/**
		 * Experience
		 */
		private Integer experience;

		/**
		 * manager id
		 */
		private Integer managerId;

		/**
		 * collaborator last nane
		 */
		protected String lastName;

		/**
		 * collaborator employement date
		 */
		protected Date employmentDate;

		/**
		 * collaborator job name
		 */
		protected String businessEngineer;

		/**
		 * collaborator email
		 */
		protected String email;
		
		/**
		 * set id
		 * @param id
		 * @return
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * set first name
		 * @param firstName
		 * @return
		 */
		public Builder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * set profileId
		 * @param id
		 * @return
		 */
		public Builder profileId(final Integer profileId) {
			this.profileId = profileId;
			return this;
		}
		
		/**
		 * set phone
		 * @param phone
		 * @return
		 */
		public Builder phone (final String phone) {
			this.phone = phone;
			return this;
		}
		
		/**
		 * set experience
		 * @param experience
		 * @return
		 */
		public Builder experience (final Integer experience) {
			this.experience = experience;
			return this;
		}
		
		/**
		 * set manager id
		 * @param managerId
		 * @return
		 */
		public Builder managerId (final Integer managerId) {
			this.managerId = managerId;
			return this;
		}
		
		/**
		 * set last name
		 * @param lastName
		 * @return
		 */
		public Builder lastName (final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		/**
		 * set employment date
		 * @param employmentDate
		 * @return
		 */
		public Builder employmentDate (final Date employmentDate) {
			this.employmentDate = employmentDate;
			return this;
		}
		
		/**
		 * set business engineer
		 * @param businessEngineer
		 * @return
		 */
		public Builder businessEngineer (final String businessEngineer) {
			this.businessEngineer = businessEngineer;
			return this;
		}
		
		/**
		 * set email
		 * @param email
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
		public Collaborator build() {
			return new Collaborator(this);
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
	 * @return the mission
	 */
	public ArrayList<Mission> getMission() {
		return mission;
	}

	/**
	 * @param mission
	 *            the mission to set
	 */
	public void setMission(ArrayList<Mission> mission) {
		this.mission = mission;
	}

	/**
	 * @return the tool
	 */
	public ArrayList<Tool> getTool() {
		return tool;
	}

	/**
	 * @param tool
	 *            the tool to set
	 */
	public void setTool(ArrayList<Tool> tool) {
		this.tool = tool;
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
//		HashCodeBuilder hashBuilder = new HashCodeBuilder();
//		hashBuilder.append(this.getFirst_name());
//		hashBuilder.append(this.getLast_name());
//		hashBuilder.append(this.getEmail());
//		hashBuilder.append(this.getExperience());
//		return hashBuilder.hashCode();
		return new HashCodeBuilder(17, 37).append(this.getId()).toHashCode();
	}

	/**
	 * Override equals, because we are need to compare collab by id
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj != null && !(obj instanceof Collaborator)) {
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
		
		Collaborator collab = (Collaborator) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(this.getId(), collab.getId()).isEquals();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the managerId
	 */
	public Integer getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * @param employmentDate the employmentDate to set
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * @return the businessEngineer
	 */
	public String getBusinessEngineer() {
		return businessEngineer;
	}

	/**
	 * @param businessEngineer the businessEngineer to set
	 */
	public void setBusinessEngineer(String businessEngineer) {
		this.businessEngineer = businessEngineer;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


}