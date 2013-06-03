package com.novedia.talentmap.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;

/**
 * 
 * @author j.maquin
 *
 */

@Controller
public class ToolController {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * 
	 * @return tools
	 */
	@RequestMapping(value = "/tools/")
	@ResponseBody
	public List<Tool> getAll() {
		List<Tool> tools = adminService.getAllTools();
		return tools;
	}
	
	/**
	 * 
	 * @param conceptId
	 * @return tools
	 */
	@RequestMapping(value = "/tools/{conceptId}/")
	@ResponseBody
	public List<Tool> getToolsByConcept(@PathVariable Integer conceptId) {
		List<Tool> tools = adminService.getToolsByConcept(conceptId);
		return tools;
	}
	
	/**
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
	 * @param toolId
	 */
	@RequestMapping(value = "/tool/{toolId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteTool(@PathVariable Integer toolId) {
		adminService.deleteTool(toolId);
	}

}
