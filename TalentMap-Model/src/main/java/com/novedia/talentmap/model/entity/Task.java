package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Contient les tâches à executer pour atteindre un objectif
 * @author moumbe
 *
 */
public class Task implements Serializable {

	/**UID*/
	private static final long serialVersionUID = 1L;
	/**The id*/
	private Integer id;
	/**The name*/
	private String name;
	/**The Task type*/
	private String taskType;
	/**The targetDate*/
	private Date targetDate;
	/**The priority*/
	private Integer priority;
	/**The effort*/
	private Integer effort;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the taskType
	 */
	public String getTaskType() {
		return taskType;
	}
	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	/**
	 * @return the targetDate
	 */
	public Date getTargetDate() {
		return targetDate;
	}
	/**
	 * @param targetDate the targetDate to set
	 */
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * @return the effort
	 */
	public Integer getEffort() {
		return effort;
	}
	/**
	 * @param effort the effort to set
	 */
	public void setEffort(Integer effort) {
		this.effort = effort;
	}
}
