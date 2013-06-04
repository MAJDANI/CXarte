package com.novedia.talentmap.rest.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.MissionDto;
import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;


/**
 * 
 * @author b.tiomofofou
 *
 */
@Controller
public class MissionController {
	
	Integer defaultInteger = -1;
	String defaultString = "null";
	
	
	@Autowired
	IColleagueService colleagueService;
	
	
	
	/**
	 * Get all collaborator's mission
	 * @param colleagueId collaborator'id
	 * @return all collaborator's mission
	 */
	@RequestMapping(value = "/missions/{colleagueId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Mission> getAllMissionByColleague(@PathVariable final Integer colleagueId){
		List<Mission> missions = null; 
		missions = colleagueService.getAllMissions(colleagueId);
		return missions;
	}
	

	/**
	 * 
	 * @param missionId
	 * @return
	 */
	@RequestMapping(value = "/mission/{missionId}/", method =RequestMethod.DELETE)
	@ResponseBody
	public Response deleteMission(@PathVariable final Integer missionId){
		Response response = new Response();
		response.setStatus("nok");
		Mission missionToDelete = Mission.builder().id(missionId).build();
		int result = colleagueService.deleteMission(missionToDelete);
		if(result != 0){
			response.setStatus("ok");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/mission/{colleagueId}/{title}/{clientId}/{place}/{startDate}/{toolId1}" +
			"/{endDate}/{comment}/{toolId2}/{toolId3}", 
			method =RequestMethod.POST)
	@ResponseBody
	public MissionDto addMission(final @PathVariable Integer colleagueId, final @PathVariable String title,
			final @PathVariable Integer clientId, final @PathVariable String place,final @PathVariable String startDate,
			final @PathVariable String endDate,final @PathVariable String comment,final @PathVariable Integer toolId1,
			final @PathVariable Integer toolId2,final @PathVariable Integer toolId3){
		MissionDto mission = null;
		Client client = Client.builder().id(clientId).build();
		Set<Tool> tools = new HashSet<Tool>(); 
		Tool tool1 = Tool.builder().id(toolId1).build();
		Tool tool2 = Tool.builder().id(toolId2).build();
		Tool tool3 = Tool.builder().id(toolId3).build();
		tools.add(tool1);
		tools.add(tool2);
		tools.add(tool3);
		Date start = DateFormat.parseStringToDate(startDate);
		Date end = DateFormat.parseStringToDate(endDate);
		
		mission = MissionDto.builder().colleagueId(colleagueId).title(title).place(place).client(client)
				.startDate(start).endDate(end).tools(tools).notes(comment).build();
		Integer result = colleagueService.addMission(mission);
		mission.setId(result);
		
		return mission;
	}
	
	

}
