package com.novedia.talentmap.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.services.impl.SkillService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 * 
 */

@Controller
public class SkillController {

	@Autowired
	SkillService skillservice;

	/**
	 * 
	 * @param colleagueId
	 * @return skills
	 */
	@RequestMapping(value = "/skills/{colleagueId}/")
	@ResponseBody
	// à finir
	public List<Skill> getAllCollaboratorSkill(@PathVariable Integer colleagueId) {
		List<Skill> skills = null;
		skillservice.getAllCollaboratorSkill(colleagueId);

		return skills;
	}

	/**
	 * 
	 * @param colleagueId
	 * @param toolId
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 * @return skill
	 */
	@RequestMapping(value = "/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/", method = RequestMethod.POST)
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
	 * 
	 * @param colleagueId
	 * @param toolId
	 * @param score
	 * @param use_frequency
	 * @param no_using_time
	 * @return skill
	 */
	@RequestMapping(value = "/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/", method = RequestMethod.PUT)
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
