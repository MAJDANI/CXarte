package com.novedia.talentmap.model.dto;

import java.util.HashMap;
import java.util.Map;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;

/**
 * 
 * @author b.tiomofofou
 * 
 */
public class ToolSkillMap {

    private Map<Tool, Skill> mapTool;

    /**
     * build ToolSkillMap class
     */
    public ToolSkillMap() {
	mapTool = new HashMap<Tool, Skill>();
    }

    /**
     * Get the mapTool
     * 
     * @return
     */
    public Map<Tool, Skill> getMapTool() {
	return mapTool;
    }

    /**
     * Set the mapTool
     * 
     * @param mapTool
     *            mapTool to set
     */
    public void setMapTool(Map<Tool, Skill> mapTool) {
	this.mapTool = mapTool;
    }

}
