package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.novedia.talentmap.model.entity.BusinessEngineer;
/**
 * This entity represents a colleague.
 * 
 * @author j.marie-sainte
 */
public class Colleague implements Serializable {
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -1323210979848908088L;
	
	/**
	 * entity indentifier
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
	 * Colleague last name
	 */
	protected String lastName;
	
	/**
	 * Colleague first name
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
	 * Colleague employment date
	 */
	protected Date employmentDate;
	
	/**
	 * Colleague experience
	 */
	protected Integer experience;
	
	/**
	 * Colleague business Engineer
	 */
	protected BusinessEngineer businessEngineer;
	
	/**
	 * Colleague Sex
	 */
	protected String sex;

	/**
	 *  List of missions
	 */
	private List<Mission> missions;
	
	/** 
	 * List of tools 
	 */
	private List<Tool> tools;

	
	/**
	 * Default Constructor
	 */
	public Colleague() {
		
	}
	
	
	/**
	 * Get the colleague's identifier
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the colleague's identifier
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the colleague's manager
	 * 
	 * @return the managerId
	 */
	public Integer getManagerId() {
		return managerId;
	}

	/**
	 * Set the colleague's manager
	 * 
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	/**
	 * Get the colleague's profile
	 * 
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * Set the colleague's profile
	 * 
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	/**
	 * Get the colleague's last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the colleague's last name 
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the colleague's first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the colleague's first name
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the the colleague's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the colleague's email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the colleague's employment date
	 * 
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * Set the colleague's employment date
	 * 
	 * @param employmentDate the employmentDate to set
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * Get the colleague's experience
	 * 
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * Set the colleague's experience
	 * 
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * Get the the colleague's business engineer
	 * 
	 * @return the businessEngineer
	 */
	public BusinessEngineer getBusinessEngineer() {
		return businessEngineer;
	}

	/**
	 * Set the business engineer
	 * 
	 * @param businessEngineer the businessEngineer to set
	 */
	public void setBusinessEngineer(BusinessEngineer businessEngineer) {
		this.businessEngineer = businessEngineer;
	}

	/**
	 * Get the colleague's phone number
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the colleague's phone number
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Get the colleague's sex
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Set the colleague's sex
	 * 
	 * @param sex           
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * Get the list of the colleague's missions
	 * 
	 * @return the mission
	 */
	public List<Mission> getMissions() {
		return missions;
	}

	/**
	 * Set the colleague's mission list
	 * 
	 * @param mission
	 *            the mission to set
	 */
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	/**
	 * Get the the colleague's list of tools
	 * 
	 * @return the tool
	 */
	public List<Tool> getTool() {
		return tools;
	}

	/**
	 * Set the colleague's list of tools
	 * 
	 * @param tool
	 *            the tool to set
	 */
	public void setTool(List<Tool> tool) {
		this.tools = tool;
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
		strBld.append("[firstName=").append(getFirstName()).append(", ");
		strBld.append("[lastName=").append(getLastName()).append(", ");
		strBld.append("[email=").append(getEmail()).append(", ");
		strBld.append("[businessEngineer=").append(getBusinessEngineer())
				.append(", ");
		strBld.append("experience").append(getExperience()).append("] ");
		return strBld.toString();
	}
		
	/**
	 * {@inheritDoc}
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
	 * Override equals, because we are need to compare colleague by id
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
		
		Colleague colleague = (Colleague) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.getId(), colleague.getId()).isEquals();
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
	 * Build an immutable category entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	protected Colleague(final Builder builder) {
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
		this.sex = builder.sex;
		this.missions = builder.missions;
		this.tools = builder.tools;
	}
	
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
		protected BusinessEngineer businessEngineer;

		/**
		 * Colleague email
		 */
		protected String email;
		
		/**
		 * Colleague sex
		 */
		protected String sex;
		
		/**
		 * List of tools mastered by Colleague
		 */
		public List<Tool> tools = Lists.newArrayList();
		
		/**
		 * List of mission performed by Colleague
		 */
		public List<Mission> missions = Lists.newArrayList();
		
		/**
		 * Set id
		 * 
		 * @param id the colleague's identifier
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
		 * @param firstName the colleague's first name
		 * @return the builder
		 */
		public Builder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * Set profileId
		 * 
		 * @param profileId the colleague's profile
		 * @return the builder
		 */
		public Builder profileId(final Integer profileId) {
			this.profileId = profileId;
			return this;
		}
		
		/**
		 * Set phone
		 * 
		 * @param phone the colleague's phone
		 * @return the builder
		 */
		public Builder phone (final String phone) {
			this.phone = phone;
			return this;
		}
		
		/**
		 * Set experience
		 * 
		 * @param experience the colleague's experience
		 * @return the builder
		 */
		public Builder experience (final Integer experience) {
			this.experience = experience;
			return this;
		}
		
		/**
		 * Set manager
		 * 
		 * @param managerId the colleague's manager 
		 * @return the builder
		 */
		public Builder managerId (final Integer managerId) {
			this.managerId = managerId;
			return this;
		}
		
		/**
		 * Set last name
		 * 
		 * @param lastName the colleague's last name
		 * @return the builder
		 */
		public Builder lastName (final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		/**
		 * Set employment date
		 * 
		 * @param employmentDate the colleague's employment date
		 * @return the builder
		 */
		public Builder employmentDate (final Date employmentDate) {
			this.employmentDate = employmentDate;
			return this;
		}
		
		/**
		 * Set business engineer
		 * @param businessEngineer the colleague's business engineer
		 * 
		 * @return the builder
		 */
		public Builder businessEngineer (final BusinessEngineer businessEngineer) {
			this.businessEngineer = businessEngineer;
			return this;
		}
		
		/**
		 * Set email
		 * 
		 * @param email the colleague's email
		 * @return
		 */
		public Builder email (final String email) {
			this.email = email;
			return this;
		}
		
		/**
		 * Set sex
		 * 
		 * @param sex the colleague's sex
		 * @return
		 */
		public Builder sex (final String sex) {
			this.sex = sex;
			return this;
		}
		
		/**
		 * Set the mission list
		 * 
		 * @param missions
		 * @return
		 */
		public Builder missions(final Mission...missions){
			this.missions.addAll(Arrays.asList(missions));
			return this;
		}
		
		/**
		 * Set the tools list
		 * 
		 * @param tools
		 * @return
		 */
		public Builder tools(final Tool...tools){
			this.tools.addAll(Arrays.asList(tools));
			return this;
		}
		
		
		/**
		 * Build instance of colleague with immutable lists of missions and tools.
		 * 
		 * @return Colleague
		 */
		public Colleague build() {
			this.missions = ImmutableList.copyOf(this.missions);
			this.tools = ImmutableList.copyOf(this.tools);
			return new Colleague(this);
		}
	}
}