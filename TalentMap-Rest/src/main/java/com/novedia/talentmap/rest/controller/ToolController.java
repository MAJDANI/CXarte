package com.novedia.talentmap.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;

/**
 * 
 * @author j.maquin
 *
 */

@Controller
public class ToolController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * Get all tools
	 * @return a list of tools
	 */
	@RequestMapping(value = "/tools/")
	@ResponseBody
	public List<Tool> getAll() {
		List<Tool> tools = adminService.getAllTools();
		return tools;
	}
	
	/**
	 * Get tools of a concept
	 * @param conceptId
	 * @return a list of tools
	 */
	@RequestMapping(value = "/tools/{conceptId}/", method=RequestMethod.GET)
	@ResponseBody
	public List<Tool> getToolsByConcept(@PathVariable Integer conceptId) {
		List<Tool> tools = adminService.getToolsByConcept(conceptId);
		return tools;
	}
	
	/**
	 * Add a tool
	 * @param conceptId
	 * @param tool_name
	 * @return tool
	 */
	@RequestMapping(value = "/tool/{conceptId}/{tool_name}/", method = RequestMethod.POST)
	@ResponseBody
	public Tool addTool(@PathVariable Integer conceptId,@PathVariable String tool_name) {
		Concept concept = Concept.builder().id(conceptId).build();
		Tool tool = Tool.builder().concept(concept).name(tool_name).build();
		Integer res = adminService.addTool(tool);
		tool.setId(res);
		return tool;
	}
	
	
	/**
	 * Update a tool
	 * @param conceptId
	 * @param toolId
	 * @param tool_name
	 * @return tool
	 */
	@RequestMapping(value = "/tool/{conceptId}/{toolId}/{tool_name}/", method = RequestMethod.PUT)
	@ResponseBody
	public Tool saveTool(@PathVariable Integer conceptId,@PathVariable Integer toolId,@PathVariable String tool_name) {
		Concept concept = Concept.builder().id(conceptId).build();
		Tool tool = Tool.builder().concept(concept).id(toolId).name(tool_name).build();
		adminService.updateTool(tool);
		return tool;
	}
	
	/**
	 * Delete a tool
	 * @param toolId
	 * @return response
	 */
	@RequestMapping(value = "/tool/{toolId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteTool(@PathVariable Integer toolId) {
		Response response = new Response();
		response.setMessage(ConstantsValue.SUCCESSFUL_DELETE_MSG);
		
		Map<String, Object> result = adminService.deleteTool(toolId);
		
		if(result.get(Constants.MSG_ERROR).equals((String)Constants.UNSUCCESSFUL_DELETE)){
			response.setMessage(ConstantsValue.UNSUCCESSFUL_DELETE_MSG);
		}
		return response;
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
