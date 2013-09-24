/**
 * 
 */
package com.novedia.talentmap.model.dto;

import java.util.List;

import com.novedia.talentmap.model.entity.Objective;


/**
 * @author v.guillemain
 * 
 */
public class EAESynthesisDTO {

	/**
	 * The identifier of the EAE
	 */
	private Integer id;
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
	 * @return the colleaguesSynthesis
	 */
	public String getColleaguesSynthesis() {
		return colleaguesSynthesis;
	}

	/**
	 * @param colleaguesSynthesis the colleaguesSynthesis to set
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
	 * @param managersSynthesis the managersSynthesis to set
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
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("colleaguesSynthesis=").append(getColleaguesSynthesis()).append(", ");
		strBld.append("managersSynthesis=").append(getManagersSynthesis()).append(", ");
		strBld.append("other=").append(getOther()).append("]");

		return strBld.toString();
	}
}
