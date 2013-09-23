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
public class EAEResultsDTO {

	/**
	 * The identifier of the EAE
	 */
	private Integer id;
	/**
	 * The summary or synthesis of the year, given by the colleague
	 */
	private String yearSynthesis;
	/**
	 * The list of objectives defines for this EAE
	 */
	private List<Objective> listOjectives;
	/**
	 * The colleague's strengths, given by the colleague
	 */
	private String collabStrenghts;
	/**
	 * The colleague's weaknesses, given by the colleague
	 */
	private String collabWeaknesses;
	/**
	 * The means that will be given to the colleague to help him pregress, given
	 * by the manager
	 */
	private String meansToProgress;
	/**
	 * The comments that will be given by the colleague about this objective
	 */
	private String comments;
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
	 * @return the yearSynthesis
	 */
	public String getYearSynthesis() {
		return yearSynthesis;
	}

	/**
	 * @param yearSynthesis
	 *            the yearSynthesis to set
	 */
	public void setYearSynthesis(String yearSynthesis) {
		this.yearSynthesis = yearSynthesis;
	}

	/**
	 * @return the listOjectives
	 */
	public List<Objective> getListOjectives() {
		return listOjectives;
	}

	/**
	 * @param listOjectives
	 *            the listOjectives to set
	 */
	public void setListOjectives(List<Objective> listOjectives) {
		this.listOjectives = listOjectives;
	}

	/**
	 * @return the collabStrenghts
	 */
	public String getCollabStrenghts() {
		return collabStrenghts;
	}

	/**
	 * @param collabStrenghts
	 *            the collabStrenghts to set
	 */
	public void setCollabStrenghts(String collabStrenghts) {
		this.collabStrenghts = collabStrenghts;
	}

	/**
	 * @return the collabWeaknesses
	 */
	public String getCollabWeaknesses() {
		return collabWeaknesses;
	}

	/**
	 * @param collabWeaknesses
	 *            the collabWeaknesses to set
	 */
	public void setCollabWeaknesses(String collabWeaknesses) {
		this.collabWeaknesses = collabWeaknesses;
	}

	/**
	 * @return the meansToProgress
	 */
	public String getMeansToProgress() {
		return meansToProgress;
	}

	/**
	 * @param meansToProgress
	 *            the meansToProgress to set
	 */
	public void setMeansToProgress(String meansToProgress) {
		this.meansToProgress = meansToProgress;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[id=").append(getId()).append(", ");
		strBld.append("yearSynthesis=").append(getYearSynthesis()).append(", ");
		strBld.append("[");
		if(listOjectives != null) {
			int i = 0;
			for(Objective o : listOjectives) {
				i++;
				strBld.append("- Objective" + i + "=").append(o.getTitle()).append(", ");
			}
		} else {
			strBld.append("listOjectives est null");
		}
		strBld.append("]");
		strBld.append("collabStrenghts=").append(getCollabStrenghts()).append(", ");
		strBld.append("collabWeaknesses=").append(getCollabWeaknesses()).append(", ");
		strBld.append("meansToProgress=").append(getMeansToProgress()).append(", ");
		strBld.append("comments=").append(getMeansToProgress()).append("]");

		return strBld.toString();
	}
}
