package com.novedia.talentmap.model.dto;

import java.util.HashMap;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;

/**
 * 
 * @author b.tiomofofou
 * 
 */
public class CategoryMapDTO {

    private Map<Category, ConceptMapDTO> mapCategory;

    /**
     * Build the CategoryMapDTO class
     */
    public CategoryMapDTO() {
	mapCategory = new HashMap<Category, ConceptMapDTO>();
    }

    /**
     * Get the mapCategory
     * 
     * @return
     */
    public Map<Category, ConceptMapDTO> getMapCategory() {
	return mapCategory;
    }

    /**
     * Set the mapCategory
     * 
     * @param mapCategory
     *            mapcategory to set
     */
    public void setMapCategory(Map<Category, ConceptMapDTO> mapCategory) {
	this.mapCategory = mapCategory;
    }

}
