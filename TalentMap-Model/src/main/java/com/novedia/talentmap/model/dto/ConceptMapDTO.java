package com.novedia.talentmap.model.dto;

import java.util.HashMap;
import java.util.Map;

import com.novedia.talentmap.model.entity.Concept;

/**
 * 
 * @author b.tiomofofou
 * 
 */

public class ConceptMapDTO {

	private Map<Concept, ToolSkillMap> mapConcept;

	/**
	 * Build ConceptMapDto class
	 */
	public ConceptMapDTO() {
		mapConcept = new HashMap<Concept, ToolSkillMap>();
	}

	/**
	 * Get the mapConcept
	 * 
	 * @return mapConcept
	 */
	public Map<Concept, ToolSkillMap> getMapConcept() {
		return mapConcept;
	}

	/**
	 * Set the mapConcept
	 * 
	 * @param mapConcept
	 *            mapConcept to set
	 */
	public void setMapConcept(Map<Concept, ToolSkillMap> mapConcept) {
		this.mapConcept = mapConcept;
	}

}
