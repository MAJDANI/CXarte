package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;


/**
 * @author b.tiomofofou
 * @author j.maquin
 *
 */
@Controller
@RequestMapping(value = "/colleagues/")
public class SearchController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	ISkillService skillService;
	
	@RequestMapping(method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleagues(){
		List <Colleague> colleagues = new ArrayList<Colleague>();
		colleagues = colleagueService.getAllColleagues();
		return colleagues;
	}
	
	@RequestMapping(value = "{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByCm(@PathVariable final Integer managerId){
		List <Colleague> colleagues = new ArrayList<Colleague>();
		colleagues = colleagueService.getCmColleaguesByName(null, managerId);
		return colleagues;
	}
	
	@RequestMapping(value = "byclient/{clientId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByClient(@PathVariable final Integer clientId){
		List <Colleague> colleagues = new ArrayList<Colleague>();
		Client client = Client.builder().id(clientId).build();
		colleagues = colleagueService.getAllColleaguesByClient(client);
		return colleagues;
	}
	
	@RequestMapping(value = "byclient/{clientId}/{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllCmColleaguesByClient(@PathVariable final Integer clientId,@PathVariable final Integer managerId){
		List <Colleague> colleagues = new ArrayList<Colleague>();
		colleagues = colleagueService.getCmColleaguesByClient(clientId, managerId);
		return colleagues;
	}
	
	/**
	 * Get colleague by name
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "byname/{name}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByName(@PathVariable final String name){
		List <Colleague> colleagues = new ArrayList<Colleague>();
	
		colleagues = colleagueService.getAllColleaguesByName(name);
		
		return colleagues;
	}
	
	
	@RequestMapping(value = "byname/{name}/{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllCmColleaguesByName(@PathVariable final String name,@PathVariable final Integer managerId){
		List <Colleague> colleagues = new ArrayList<Colleague>();
		String colleagueName = name;
		if (name.equalsIgnoreCase(ConstantsValue.DEFAULT_STRING_VALUE)) {
			colleagueName = "";
		}
		colleagues = colleagueService.getCmColleaguesByName(colleagueName, managerId);
		return colleagues;
	}

	@RequestMapping(value = "bytool/{toolId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByTool(@PathVariable final Integer toolId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getAllColleagueIdByToolId(toolId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}
	
	@RequestMapping(value = "bytool/{toolId}/{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllCmColleaguesByTool(@PathVariable final Integer toolId,@PathVariable final Integer managerId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getCmColleagueIdByToolId(toolId,managerId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}
	
	@RequestMapping(value = "byconcept/{conceptId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByConcept(@PathVariable final Integer conceptId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getAllColleagueIdByConceptId(conceptId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}
	
	@RequestMapping(value = "byconcept/{conceptId}/{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllCmColleaguesByConcept(@PathVariable final Integer conceptId,@PathVariable final Integer managerId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getAllCmColleagueIdByConceptId(conceptId,managerId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}
	
	@RequestMapping(value = "bycategory/{categoryId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllColleaguesByCategory(@PathVariable final Integer categoryId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getAllColleagueIdByCategoryId(categoryId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}
	
	@RequestMapping(value = "bycategory/{categoryId}/{managerId}/", method =RequestMethod.GET)
	@ResponseBody
	public List<Colleague> getAllCmColleaguesByCategory(@PathVariable final Integer categoryId,@PathVariable final Integer managerId){
		List<Colleague> colleagues = new ArrayList<Colleague>();
		List<Integer> colleagueIds = skillService.getAllCmColleagueIdByCategoryId(categoryId,managerId);
		if(colleagueIds != null && !colleagueIds.isEmpty()){
			colleagues = colleagueService.getAllColleagueByColleagueIdList(colleagueIds);
		}
		return colleagues;
	}

	@Override
	@ExceptionHandler({TypeMismatchException.class})
	public JsonException handlerBadRequestException(Exception ex) {
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.BAD_REQUEST.value());
		jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		return jsonError;
	}

}
