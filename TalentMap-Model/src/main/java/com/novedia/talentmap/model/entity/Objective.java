package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * This entity represents an objective. An Objective is defined <b>during an
 * EAE</b> by the <b>colleague</b> and his <b>manager</b>. Thats why an
 * Objective references a colleague, a manager and an EAE. When the objective is
 * created, the manager and the colleague define a title, a goal and a
 * targetDate. Indicators will be used to check if the objective is completed or
 * not. Means are the means the manager will give the colleague in order to
 * achieve his objective. During the EAE next year, the colleague and the
 * manager will estimate how much the objective is completed : they will give a
 * score ObjectiveScore. The Colleague may indicate the elements that were
 * motives or restraints to achieve his Objective.
 * 
 * @author v.guillemain
 * 
 */
public class Objective implements Serializable {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 4940722058013124579L;

	/**
	 * identifier of the Objective
	 */
	private Integer id;
	/**
	 * The Colleague that must achieve this Objective
	 */
	private Colleague colleague;
	/**
	 * The manager with who the colleague defined this Objective.
	 */
	private Colleague manager;
	/**
	 * The Eae during what the Objective was defined.
	 */
	private EAE eae;
	/**
	 * The title of the Objective
	 */
	private String title;
	/**
	 * The goal of the Objective
	 */
	private String goal;
	/**
	 * The Target Date of the Objective: the Objective must be achieved before
	 * this date.
	 */
	private Date targetDate;
	/**
	 * Indicators that will enable to evaluate if the Objective is achieved or
	 * not.
	 */
	private String indicators;
	/**
	 * Means the Manager will give the Colleague in order to achieve this
	 * Objective.
	 */
	private String means;
	/**
	 * The Score the Colleague gives to the fulfillment of the Objective, the
	 * year after the definition of the objective.
	 */
	private Integer colleaguesScore;
	/**
	 * The Score the Manager gives to the fulfillment of the Objective, the year
	 * after the definition of the objective.
	 */
	private Integer managersScore;
	/**
	 * The Colleague may indicate the elements during the year that either
	 * motived or retrained the achievement of the Objective.
	 */
	private String motivesOrRestraints;
	/**
	 * The Colleague may indicate comments about the achievement of the
	 * Objective.
	 */
	private String comments;

	/**
	 * Builds an object Objective
	 */
	public Objective() {
	}

	/**
	 * @return id the identifier of the Objective
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            : the identifier of the Objective to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return colleague the colleague concerned by the Objective
	 */
	public Colleague getColleague() {
		return colleague;
	}

	/**
	 * @param colleague
	 *            : the colleague concerned by the Objective to set.
	 */
	public void setColleague(Colleague colleague) {
		this.colleague = colleague;
	}

	/**
	 * @return manager the manager of the colleague when the Objective was
	 *         defined
	 */
	public Colleague getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            : the manager of the colleague when the Objective was defined
	 */
	public void setManager(Colleague manager) {
		this.manager = manager;
	}

	/**
	 * @return eae the EAE in which the Objective was defined
	 */
	public EAE getEae() {
		return eae;
	}

	/**
	 * @param eae
	 *            : the EAE in which the Objective was defined
	 */
	public void setEae(EAE eae) {
		this.eae = eae;
	}

	/**
	 * 
	 * @return title : the title of the Objective
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            : the title of the Objective
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return goal : the goal of the Objective
	 */
	public String getGoal() {
		return goal;
	}

	/**
	 * @param goal
	 *            : the goal of the Objective
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}

	/**
	 * @return targetDate : the target date of the Objective
	 */
	public Date getTargetDate() {
		return targetDate;
	}

	/**
	 * @param targetDate
	 *            : the target date of the Objective
	 */
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	/**
	 * @return indicators : the indicators of the Objective
	 */
	public String getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators
	 *            : the indicators of the Objective
	 */
	public void setIndicators(String indicators) {
		this.indicators = indicators;
	}

	/**
	 * @return means : the means of the Objective
	 */
	public String getMeans() {
		return means;
	}

	/**
	 * @param means
	 *            : the means of the Objective
	 */
	public void setMeans(String means) {
		this.means = means;
	}

	/**
	 * @return colleaguesScore : the colleaguesScore of the Objective
	 */
	public Integer getColleaguesScore() {
		return colleaguesScore;
	}

	/**
	 * @param colleaguesScore
	 *            : the colleaguesScore of the Objective
	 */
	public void setColleaguesScore(Integer colleaguesScore) {
		this.colleaguesScore = colleaguesScore;
	}

	/**
	 * @return managersScore : the managersScore of the Objective
	 */
	public Integer getManagersScore() {
		return managersScore;
	}

	/**
	 * @param managersScore
	 *            : the managersScore of the Objective
	 */
	public void setManagersScore(Integer managersScore) {
		this.managersScore = managersScore;
	}

	/**
	 * @return motivesOrRestraints : the motivesOrRestraints of the Objective
	 */
	public String getMotivesOrRestraints() {
		return motivesOrRestraints;
	}

	/**
	 * @param motivesOrRestraints
	 *            : the motivesOrRestraints of the Objective
	 */
	public void setMotivesOrRestraints(String motivesOrRestraints) {
		this.motivesOrRestraints = motivesOrRestraints;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("colleague=").append(getColleague()).append(", ");
		strBld.append("manager=").append(getManager()).append(", ");
		strBld.append("eae=").append(getEae()).append(", ");
		strBld.append("title=").append(getTitle()).append(", ");
		strBld.append("goal=").append(getGoal()).append(", ");
		strBld.append("targetDate=").append(getTargetDate()).append(", ");
		strBld.append("indicators=").append(getIndicators()).append(", ");
		strBld.append("means=").append(getMeans()).append(", ");
		strBld.append("colleaguesScore=").append(getColleaguesScore()).append(", ");
		strBld.append("managersScore=").append(getManagersScore()).append(", ");
		strBld.append("motivesOrRestraints=").append(getMotivesOrRestraints()).append(", ");
		strBld.append("comments=").append(getComments()).append("]");
		return strBld.toString();
    }

}
