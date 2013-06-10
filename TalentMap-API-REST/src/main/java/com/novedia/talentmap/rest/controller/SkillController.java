package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.SkillCategory;
import com.novedia.talentmap.rest.utiils.SkillConcept;
import com.novedia.talentmap.rest.utiils.SkillTool;
import com.novedia.talentmap.services.impl.SkillService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 * 
 */

@Controller
public class SkillController extends TalentMapRestHandlerException {

	@Autowired
	SkillService skillservice;

	/**
	 * Get  collaborators' skill
	 * @param colleagueId
	 * @return skills
	 */
	@RequestMapping(value = "/skills/{colleagueId}/" , method=RequestMethod.GET)
	@ResponseBody
	public List<SkillCategory> getAllCollaboratorSkill(@PathVariable Integer colleagueId) {
		List<SkillCategory> collaboratorSkill = new ArrayList<SkillCategory>();
		CategoryMapDTO categoryMapDto =  skillservice.getAllCollaboratorSkill(colleagueId);
		
		if(categoryMapDto != null && !categoryMapDto.getMapCategory().isEmpty()){
			for (Map.Entry<Category, ConceptMapDTO> categoryMap : categoryMapDto.getMapCategory().entrySet()) {
				SkillCategory skillCtegory = new SkillCategory();
				skillCtegory.setCategoryId(categoryMap.getKey().getId());
				skillCtegory.setCategoryName(categoryMap.getKey().getName());
				List<SkillConcept> concepts = new ArrayList<SkillConcept>();
				
				ConceptMapDTO conceptMapDto = categoryMap.getValue();
				
				for (Map.Entry<Concept, ToolSkillMap> conceptMap : conceptMapDto.getMapConcept().entrySet()) {
					SkillConcept concept = new SkillConcept();
					concept.setConceptId(conceptMap.getKey().getId());
					concept.setConceptName(conceptMap.getKey().getName());
					concept.setConceptScore((int) Math.round (conceptMap.getKey().getScore()));
					
					Map<Tool, Skill> mapTool = conceptMap.getValue().getMapTool();
					List<SkillTool> skills = new ArrayList<SkillTool>();
					for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
						SkillTool skillTool = new SkillTool();
						skillTool.setToolId(eTool.getKey().getId());
						skillTool.setToolName(eTool.getKey().getName());
						skillTool.setNoUsingTime(eTool.getValue().getNo_using_time());
						skillTool.setUseFrequency(eTool.getValue().getUse_frequency());
						skillTool.setToolScore(eTool.getValue().getAverageScore());
						skills.add(skillTool);
					}
					concept.setTools(skills);
					concepts.add(concept);
				}
				skillCtegory.setConcepts(concepts);
				collaboratorSkill.add(skillCtegory);
			}
		}

		return collaboratorSkill;
	}

	/**
	 * Add a skill
	 * @param colleagueId
	 * @param toolId
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 * @return skill
	 */
	@RequestMapping(value = "/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/", 
			method = RequestMethod.POST)
	@ResponseBody
	public Skill addSkill(@PathVariable Integer colleagueId,
			@PathVariable Integer toolId, @PathVariable Integer score,
			@PathVariable Integer use_frequency,
			@PathVariable Integer no_using_time) {

		double averageScore = skillservice.computeToolAverage(score,
				use_frequency, no_using_time);
		Skill skill = Skill.builder().colleagueId(colleagueId).tool_id(toolId)
				.score(score).use_frequency(use_frequency)
				.no_using_time(no_using_time).averageScore((int) averageScore)
				.build();
		skillservice.addSkill(skill);
		return skill;
	}
	
	/**
	 * Update a skill
	 * @param colleagueId
	 * @param toolId
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 * @return skill
	 */
	@RequestMapping(value = "/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/",
			method = RequestMethod.PUT)
	@ResponseBody
	public Skill saveSkill(@PathVariable Integer colleagueId,
			@PathVariable Integer toolId, @PathVariable Integer score,
			@PathVariable Integer use_frequency,
			@PathVariable Integer no_using_time) {
		
		double averageScore = skillservice.computeToolAverage(score,
				use_frequency, no_using_time);
		Skill skill = Skill.builder().colleagueId(colleagueId).tool_id(toolId)
				.score(score).use_frequency(use_frequency)
				.no_using_time(no_using_time).averageScore((int) averageScore)
				.build();
		skillservice.saveSkill(skill);
		return skill;
	}

}
