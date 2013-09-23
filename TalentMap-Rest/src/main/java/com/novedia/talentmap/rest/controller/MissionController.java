package com.novedia.talentmap.rest.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.rest.utiils.DateFormat;
import com.novedia.talentmap.services.IColleagueService;


/**
 * 
 * @author b.tiomofofou
 *
 */
@Controller
public class MissionController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IColleagueService colleagueService;
	
	/**
	 * Get all  mission of collaborator
	 * @param colleagueId collaborator'id
	 * @return all mission of collaborator
	 */
	@RequestMapping(value = "/missions/{colleagueId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Mission> getAllMissionByColleague(@PathVariable final Integer colleagueId){
		List<Mission> missions = null; 
		missions = colleagueService.getAllMissions(colleagueId);
		return missions;
	}
	

	/**
	 * Delete one mission
	 * @param missionId missionId to delete
	 * @return Response 
	 */
	@RequestMapping(value = "/mission/{missionId}/", method =RequestMethod.DELETE)
	@ResponseBody
	public Response deleteMission(@PathVariable final Integer missionId){
		Response response = new Response();
		response.setMessage(ConstantsValue.UNSUCCESSFUL_DELETE_MSG);
		Mission missionToDelete = Mission.builder().id(missionId).build();
		int result = colleagueService.deleteMission(missionToDelete);
		if(result != 0){
			response.setMessage(ConstantsValue.SUCCESSFUL_DELETE_MSG);
		}
		return response;
	}
	
	
	
	/**
	 * Add a mission
	 * @param colleagueId colleagueId 
	 * @param title title of mission
	 * @param clientId clientId of mission
	 * @param place place of mission
	 * @param startDate startDate of mission
	 * @param endDate endDate of mission
	 * @param comment comment of mission
	 * @param toolId1 toolId1 of mission
	 * @param toolId2 toolId2 of mission
	 * @param toolId3 toolId3 of mission
	 * @return MissionDto
	 * @throws ParseException
	 * @throws DataAccessException
	 */
	@RequestMapping(value = "/mission/{colleagueId}/{title}/{clientId}/{place}/{startDate}/{endDate}" +
			"/{comment}/{toolId1}/{toolId2}/{toolId3}", 
			method =RequestMethod.POST)
	@ResponseBody
	public MissionDTO addMission(final @PathVariable Integer colleagueId, final @PathVariable String title,
			final @PathVariable Integer clientId, final @PathVariable String place,final @PathVariable String startDate,
			final @PathVariable String endDate,final @PathVariable String comment,final @PathVariable Integer toolId1,
			final @PathVariable Integer toolId2,final @PathVariable Integer toolId3) throws ParseException, DataAccessException{
		
		MissionDTO missionDto = null;
		
		missionDto = buildMissionDto(colleagueId,title,clientId,place,startDate,endDate,comment,toolId1,toolId2,toolId3);
		
		Integer result = colleagueService.addMission(missionDto);
		missionDto.setId(result);
		
		return missionDto;
	}
	
	
	
	/**
	 * Update a mission
	 * @param missionId missionId to update
	 * @param colleagueId colleagueId of mission
	 * @param title title of mission
	 * @param clientId clientId of mission
	 * @param place place of mission
	 * @param startDate startDate of mission
	 * @param endDate endDate of mission
	 * @param comment comment of mission
	 * @param toolId1 toolId1 of mission
	 * @param toolId2 toolId2 of mission
	 * @param toolId3 toolId3 of mission
	 * @return MissionDto
	 * @throws ParseException
	 * @throws DataAccessException
	 */
	@RequestMapping(value = "/mission/{missionId}/{colleagueId}/{title}/{clientId}/{place}/{startDate}/{endDate}" +
			"/{comment}/{toolId1}/{toolId2}/{toolId3}", 
			method =RequestMethod.PUT)
	@ResponseBody
	public MissionDTO updateMission(final @PathVariable Integer missionId,final @PathVariable Integer colleagueId, final @PathVariable String title,
			final @PathVariable Integer clientId, final @PathVariable String place,final @PathVariable String startDate,
			final @PathVariable String endDate,final @PathVariable String comment,final @PathVariable Integer toolId1,
			final @PathVariable Integer toolId2,final @PathVariable Integer toolId3) throws ParseException, DataAccessException{
		
		MissionDTO missionDto = null;
		
		missionDto = buildMissionDto(colleagueId,title,clientId,place,startDate,endDate,comment,toolId1,toolId2,toolId3);
		missionDto.setId(missionId);
		Integer result = colleagueService.saveMission(missionDto);
		if(result.equals(0)){
			missionDto = MissionDTO.builder().id(missionId).build();
		} 
		return missionDto;
	}
	
	
	/**
	 * build missionDto
	 * @param colleagueId colleagueId 
	 * @param title title of mission
	 * @param clientId clientId of mission
	 * @param place place of mission
	 * @param startDate startDate of mission
	 * @param endDate endDate of mission
	 * @param comment comment of mission
	 * @param toolId1 toolId1 of mission
	 * @param toolId2 toolId2 of mission
	 * @param toolId3 toolId3 of mission
	 * @return MissionDto
	 * @throws ParseException
	 * @throws DataAccessException
	 */
	private MissionDTO buildMissionDto(final Integer colleagueId, final String title,
			final Integer clientId, final String place,final String startDate,
			final String endDate,final String comment,final Integer toolId1,
			final Integer toolId2,final Integer toolId3) throws ParseException, DataAccessException{
		
		MissionDTO missionDto = null;
		
		Client client = Client.builder().id(clientId).build();
		Set<Tool> tools = new HashSet<Tool>(); 
		Tool tool1 = Tool.builder().id(toolId1).build();
		tools.add(tool1);
		if (!toolId2.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)) {
			Tool tool2 = Tool.builder().id(toolId2).build();
			tools.add(tool2);
		}
		if (!toolId3.equals(ConstantsValue.DEFAULT_NUMERIC_VALUE)) {
			Tool tool3 = Tool.builder().id(toolId3).build();
			tools.add(tool3);
		}
		
		String notes = comment ;
		if (comment.equalsIgnoreCase(ConstantsValue.DEFAULT_STRING_VALUE) ) {
			notes = null;
		}
		
		Date start = DateFormat.parseStringToDate(startDate);
		
		Date endDate1 = null;
		if (!endDate.equalsIgnoreCase(ConstantsValue.DEFAULT_STRING_VALUE) ) {
			endDate1 = DateFormat.parseStringToDate(endDate);
		}
		
		missionDto = MissionDTO.builder().colleagueId(colleagueId).title(title).place(place).client(client)
				.startDate(start).endDate(endDate1).tools(tools).notes(notes).build();
		
		return missionDto;
	}


	@Override
	@ExceptionHandler({TypeMismatchException.class, ParseException.class})
	public JsonException handlerBadRequestException(Exception ex) {
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.BAD_REQUEST.value());
		if (ex instanceof ParseException) {
			jsonError.setMessage(ConstantsValue.INCORRECT_DATE_FORMAT_MSG);
		} else {
			jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		
		return jsonError;
	}
	
	
}
