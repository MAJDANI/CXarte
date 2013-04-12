package com.novedia.talentmap.model.entity;

import java.util.Date;


/**
 * This entity represents a registration on TalentMap
 * @author y.rohr
 *
 */
public class Registration {
	
	/**
	 * Colleague Id
	 */
	private Integer colleagueId;
	
	/**
	 * AuthenticationId
	 */
	private Integer authenticationId;
	
	/**
	 * Sex
	 */
	private String title;
	
	/**
	 * First Name
	 */
	private String firstName;
	
	/**
	 * Last Name
	 */
	private String lastName;
	
	/**
	 * mail
	 */
	private String email;
	
	/**
	 * Phone number
	 */
	private String phone;
	
	/**
	 * User password
	 */
	private String password;
	
	/**
	 * User confirm password
	 */
	private String passwordConfirm;
	
	/**
	 * User login
	 */
	private String login;
	
	/**
	 * Employment Date
	 */
	private Date employmentDate;
	
	/**
	 * Experience (unit : years) 
	 */
	private Integer experience;
	
	/**
	 * The business Engineer
	 */
	private BusinessEngineer businessEngineer;
	
	/**
	 * Profile id
	 */
	private Integer profileId;
	
	/**
	 * Manager Id
	 */
	private Integer managerId;

	/**
	 * Role 
	 */
	private Authorization.Role role;

	/**
	 * Default constructor
	 */
	public Registration(){
		
	}
	
	
	public Registration(final Builder builder){
		this.setColleagueId(builder.colleagueId);
		this.setAuthenticationId(builder.authenticationId);
		this.setFirstName(builder.firstName);
		this.setLastName(builder.lastName);
		this.setEmail(builder.email);
		this.setPhone(builder.phone);
		this.setLogin(builder.login);
		this.setPassword(builder.password);
		this.setPasswordConfirm(builder.passwordConfirm);
		this.setEmploymentDate(builder.employmentDate);
		this.setExperience(builder.experience);
		this.setBusinessEngineer(builder.businessEngineer);
		this.setProfileId(builder.profileId);
		this.setManagerId(builder.managerId);
	}
	
	// -------------------------------------
		// ------------ BUILDER PART -----------
		// -------------------------------------
		


		/**
		 * Inner builder class.
		 * 
		 * @author y.rohr
		 */
		public static class Builder {
			
			private Integer colleagueId;
			
			private Integer authenticationId;
			
			private String firstName;
			
			private String lastName;
			
			private String email;
			
			private String phone;
			
			private String login;
			
			private String password;
			
			private String passwordConfirm;
			
			private Date employmentDate;
			
			private Integer experience;
			
			private BusinessEngineer businessEngineer;
			
			private Integer profileId;
			
			private Integer managerId;

			/**
			 * Set colleague id
			 * 
			 * @param colleague id
			 * @return the builder
			 */
			public Builder colleagueId(final Integer colleagueId) {
				this.colleagueId = colleagueId;
				return this;
			}
			
			/**
			 * Set authentication id
			 * 
			 * @param authentication id
			 * @return the builder
			 */
			public Builder authenticationId(final Integer authenticationId) {
				this.authenticationId = authenticationId;
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
			 * Set last name
			 * 
			 * @param lastName the coworker's first name
			 * @return the builder
			 */
			public Builder lastName(final String lastName) {
				this.lastName = lastName;
				return this;
			}
			
			/**
			 * Set password
			 * 
			 * @param password
			 * @return the builder
			 */
			public Builder password(final String password) {
				this.password = password;
				return this;
			}
			
			/**
			 * Set confirm password
			 * 
			 * @param password
			 * @return the builder
			 */
			public Builder passwordConfirm(final String passwordConfirm) {
				this.passwordConfirm = passwordConfirm;
				return this;
			}
			
			/**
			 * Set login
			 * 
			 * @param login
			 * @return the builder
			 */
			public Builder login(final String login) {
				this.login = login;
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
			public Builder businessEngineer (final BusinessEngineer businessEngineer) {
				this.businessEngineer= businessEngineer;
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
			public Registration build() {
				return new Registration(this);
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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	public BusinessEngineer getBusinessEngineer() {
		return businessEngineer;
	}


	/**
	 * @param businessEngineer the businessEngineer to set
	 */
	public void setBusinessEngineer(BusinessEngineer businessEngineer) {
		this.businessEngineer = businessEngineer;
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @return the colleagueId
	 */
	public Integer getColleagueId() {
		return colleagueId;
	}


	/**
	 * @param colleagueId the colleagueId to set
	 */
	public void setColleagueId(Integer colleagueId) {
		this.colleagueId = colleagueId;
	}


	/**
	 * @return the authenticationId
	 */
	public Integer getAuthenticationId() {
		return authenticationId;
	}


	/**
	 * @param authenticationId the authenticationId to set
	 */
	public void setAuthenticationId(Integer authenticationId) {
		this.authenticationId = authenticationId;
	}


	/**
	 * @return the role
	 */
	public Authorization.Role getRole() {
		return role;
	}


	/**
	 * @param role the Authorization.Role to set
	 */
	public void setRole(Authorization.Role role) {
		this.role = role;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


		
}
