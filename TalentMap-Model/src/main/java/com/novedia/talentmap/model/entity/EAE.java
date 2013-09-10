package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.novedia.talentmap.model.entity.Frequency.Builder;

public class EAE implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 8904800079555349662L;

	/**
	 * The identifier of the EAE
	 */
	private Integer id;
	/**
	 * The colleague concerned by the EAE
	 */
	private Colleague colleague;
	/**
	 * The manager of the colleague when the EAE occurred
	 */
	private Colleague manager;
	/**
	 * The date of the meeting for the EAE
	 */
	private Date dateEae;
	/**
	 * The label of the Profile the colleague has at the date of the EAE. As the
	 * profile of the colleague may change in future, and the database may
	 * change too, we have here the label and not the id
	 */
	private String profileLabel;
	/**
	 * The identifier of the previous EAE of the same colleague. We have the id
	 * instead of the Object EAE in order to avoid heavy loading of many EAE
	 * referencing other EAE years after years.
	 */
	private Integer previousEaeId;
	/**
	 * Synthese de l'année à remplir par le collaborateur dans l'onglet Bilan
	 */
	private String yearSynthesis;
	/**
	 * The Strengths of the colleague during the year
	 */
	private String colleaguesStrengths;
	/**
	 * The development axis of the colleague
	 */
	private String colleaguesWeaknesses;
	/**
	 * The current state of the EAE (open, validated, close)
	 */
	private EAEState eaeState;
	/**
	 * The synthesis of the year given by the colleague.
	 */
	private String colleaguesSynthesis;
	/**
	 * The synthesis of the year given by the manager.
	 */
	private String managersSynthesis;
	/**
	 * A free field in order to indicate any other subject.
	 */
	private String other;

	/**
	 * The salary of the colleague at the date of the EAE
	 */
	// private Integer salary;

	/**
	 * Build an immutable EAE entity.
	 * 
	 * @param builder
	 *            the builder inner class for this entity
	 */
	public EAE(final Builder builder) {
		this.id = builder.id;
		this.colleague = builder.colleague;

	}

	/**
	 * Builds an object EAE
	 */
	public EAE() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the colleague
	 */
	public Colleague getColleague() {
		return colleague;
	}

	/**
	 * @param colleague
	 *            the colleague to set
	 */
	public void setColleague(Colleague colleague) {
		this.colleague = colleague;
	}

	/**
	 * @return the manager
	 */
	public Colleague getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(Colleague manager) {
		this.manager = manager;
	}

	/**
	 * @return the dateEae
	 */
	public Date getDateEae() {
		return dateEae;
	}

	/**
	 * @param dateEae
	 *            the dateEae to set
	 */
	public void setDateEae(Date dateEae) {
		this.dateEae = dateEae;
	}

	/**
	 * @return the profileLabel
	 */
	public String getProfileLabel() {
		return profileLabel;
	}

	/**
	 * @param profileLabel
	 *            the profileLabel to set
	 */
	public void setProfileLabel(String profileLabel) {
		this.profileLabel = profileLabel;
	}

	/**
	 * @return the previousEaeId
	 */
	public Integer getPreviousEaeId() {
		return previousEaeId;
	}

	/**
	 * @param previousEaeId
	 *            the previousEaeId to set
	 */
	public void setPreviousEaeId(Integer previousEaeId) {
		this.previousEaeId = previousEaeId;
	}

	/**
	 * @return the yearSynthesis
	 */
	public String getYearSynthesis() {
		return yearSynthesis;
	}

	/**
	 * @param yearSynthesis the yearSynthesis to set
	 */
	public void setYearSynthesis(String yearSynthesis) {
		this.yearSynthesis = yearSynthesis;
	}

	/**
	 * @return the colleaguesStrengths
	 */
	public String getColleaguesStrengths() {
		return colleaguesStrengths;
	}

	/**
	 * @param colleaguesStrengths
	 *            the colleaguesStrengths to set
	 */
	public void setColleaguesStrengths(String colleaguesStrengths) {
		this.colleaguesStrengths = colleaguesStrengths;
	}

	/**
	 * @return the colleaguesWeaknesses
	 */
	public String getColleaguesWeaknesses() {
		return colleaguesWeaknesses;
	}

	/**
	 * @param colleaguesWeaknesses
	 *            the colleaguesWeaknesses to set
	 */
	public void setColleaguesWeaknesses(String colleaguesWeaknesses) {
		this.colleaguesWeaknesses = colleaguesWeaknesses;
	}

	/**
	 * @return the eaeState
	 */
	public EAEState getEaeState() {
		return eaeState;
	}

	/**
	 * @param eaeState
	 *            the eaeState to set
	 */
	public void setEaeState(EAEState eaeState) {
		this.eaeState = eaeState;
	}

	/**
	 * @return the colleaguesSynthesis
	 */
	public String getColleaguesSynthesis() {
		return colleaguesSynthesis;
	}

	/**
	 * @param colleaguesSynthesis
	 *            the colleaguesSynthesis to set
	 */
	public void setColleaguesSynthesis(String colleaguesSynthesis) {
		this.colleaguesSynthesis = colleaguesSynthesis;
	}

	/**
	 * @return the managersSynthesis
	 */
	public String getManagersSynthesis() {
		return managersSynthesis;
	}

	/**
	 * @param managersSynthesis
	 *            the managersSynthesis to set
	 */
	public void setManagersSynthesis(String managersSynthesis) {
		this.managersSynthesis = managersSynthesis;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other
	 *            the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	// /**
	// * @return the salary
	// */
	// public Integer getSalary() {
	// return salary;
	// }
	//
	// /**
	// * @param salary the salary to set
	// */
	// public void setSalary(Integer salary) {
	// this.salary = salary;
	// }

	// ------------------------------------------------------------------------
	// ------------ BUILDER PART ----------------------------------------------
	// ------------------------------------------------------------------------
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
	 */
	public static class Builder {

		/**
		 * The identifier of the EAE
		 */
		private Integer id;
		/**
		 * The colleague concerned by the EAE
		 */
		private Colleague colleague;
		/**
		 * The manager of the colleague when the EAE occurred
		 */
		private Colleague manager;
		/**
		 * The date of the meeting for the EAE
		 */
		private Date dateEae;
		/**
		 * The label of the Profile the colleague has at the date of the EAE. As
		 * the profile of the colleague may change in future, and the database
		 * may change too, we have here the label and not the id
		 */
		private String profileLabel;
		/**
		 * The identifier of the previous EAE of the same colleague. We have the
		 * id instead of the Object EAE in order to avoid heavy loading of many
		 * EAE referencing other EAE years after years.
		 */
		private Integer previousEaeId;
		/**
		 * The Strengths of the colleague during the year
		 */
		private String colleaguesStrengths;
		/**
		 * The development axis of the colleague
		 */
		private String colleaguesWeaknesses;
		/**
		 * The current state of the EAE (open, validated, close)
		 */
		private EAEState eaeState;
		/**
		 * The synthesis of the year given by the colleague.
		 */
		private String colleaguesSynthesis;
		/**
		 * The synthesis of the year given by the manager.
		 */
		private String managersSynthesis;
		/**
		 * A free field in order to indicate any other subject.
		 */
		private String other;

		/**
		 * The salary of the colleague at the date of the EAE
		 */
		// private Integer salary;

		/**
		 * Set id
		 * 
		 * @param id
		 *            EAE's identifier
		 * @return the builder
		 */
		public Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		/**
		 * Set colleague
		 * 
		 * @param colleague
		 *            the EAE's colleague
		 * @return the builder
		 */
		public Builder colleague(final Colleague colleague) {
			this.colleague = colleague;
			return this;
		}

		/**
		 * Set manager
		 * 
		 * @param manager
		 *            the EAE's manager
		 * @return the builder
		 */
		public Builder manager(final Colleague manager) {
			this.manager = manager;
			return this;
		}

		/**
		 * Set dateEae
		 * 
		 * @param dateEae
		 *            the EAE's date
		 * @return the builder
		 */
		public Builder dateEae(final Date dateEae) {
			this.dateEae = dateEae;
			return this;
		}

		/**
		 * Set profileLabel
		 * 
		 * @param profileLabel
		 *            the EAE's profileLabel
		 * @return the builder
		 */
		public Builder profilLabel(final String profileLabel) {
			this.profileLabel = profileLabel;
			return this;
		}

		/**
		 * Set previousEaeId
		 * 
		 * @param previousEaeId
		 *            the EAE's previousEaeId
		 * @return the builder
		 */
		public Builder previousEaeId(final Integer previousEaeId) {
			this.previousEaeId = previousEaeId;
			return this;
		}

		/**
		 * Set colleaguesStrengths
		 * 
		 * @param colleaguesStrengths
		 *            the EAE's colleaguesStrengths
		 * @return the builder
		 */
		public Builder colleaguesStrengths(final String colleaguesStrengths) {
			this.colleaguesStrengths = colleaguesStrengths;
			return this;
		}

		/**
		 * Set colleaguesWeaknesses
		 * 
		 * @param colleaguesWeaknesses
		 *            the EAE's colleaguesWeaknesses
		 * @return the builder
		 */
		public Builder colleaguesWeaknesses(final String colleaguesWeaknesses) {
			this.colleaguesWeaknesses = colleaguesWeaknesses;
			return this;
		}

		/**
		 * Set eaeState
		 * 
		 * @param eaeState
		 *            the EAE's eaeState
		 * @return the builder
		 */
		public Builder eaeState(final EAEState eaeState) {
			this.eaeState = eaeState;
			return this;
		}

		/**
		 * Set managersSynthesis
		 * 
		 * @param managersSynthesis
		 *            the EAE's managersSynthesis
		 * @return the builder
		 */
		public Builder managersSynthesis(final String managersSynthesis) {
			this.managersSynthesis = managersSynthesis;
			return this;
		}

		/**
		 * Set other
		 * 
		 * @param other
		 *            the EAE's other
		 * @return the builder
		 */
		public Builder other(final String other) {
			this.other = other;
			return this;
		}

		/**
		 * Set salary
		 * 
		 * @param salary
		 *            the EAE's salary
		 * @return the builder
		 */
		// public Builder other(final Integer salary) {
		// this.salary = salary;
		// return this;
		// }

		/**
		 * Build an immutable instance of EAE.
		 * 
		 * @return EAE
		 */
		public EAE build() {
			return new EAE(this);
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
