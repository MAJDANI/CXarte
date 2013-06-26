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
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
public class ConceptController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * Get concepts of a category
	 * @param categoryId
	 * @return a list of concepts
	 */
	@RequestMapping(value = "/concepts/{categoryId}/" , method=RequestMethod.GET)
	@ResponseBody
	public List<Concept> getAll(@PathVariable Integer categoryId) {
		Category category = Category.builder().id(categoryId).build();
		List<Concept> concepts = adminService.getAllConceptByCategory(category);
		return concepts;
	}
	
	/**
	 * Add a concept to a category
	 * @param categoryId
	 * @param concept_name
	 * @return concept
	 */
	@RequestMapping(value = "/concept/{categoryId}/{concept_name}/", method = RequestMethod.POST)
	@ResponseBody
	public Concept addConcept(@PathVariable Integer categoryId,@PathVariable String concept_name) {
		Category category = Category.builder().id(categoryId).build();
		Concept concept = Concept.builder().category(category).name(concept_name).build();
		Integer res = adminService.addConcept(concept);
		concept.setId(res);
		return concept;
	}
	
	/**
	 * Update a concept of a category
	 * @param categoryId
	 * @param conceptId
	 * @param concept_name
	 * @return concept
	 */
	@RequestMapping(value = "/concept/{categoryId}/{conceptId}/{concept_name}/", method = RequestMethod.PUT)
	@ResponseBody
	public Concept saveConcept(@PathVariable Integer categoryId,@PathVariable Integer conceptId,@PathVariable String concept_name) {
		Category category = Category.builder().id(categoryId).build();
		Concept concept = Concept.builder().category(category).id(conceptId).name(concept_name).build();
		adminService.saveConcept(concept);
		return concept;
	}
	
	/**
	 * Delete a concept
	 * @param conceptId
	 * @return response
	 */
	@RequestMapping(value = "/concept/{conceptId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteConcept(@PathVariable Integer conceptId) {
		Response response = new Response();
		response.setMessage(ConstantsValue.SUCCESSFUL_DELETE_MSG);
		
		Map<String, Object> result = adminService.deleteConcept(conceptId);
		
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
		jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase() + " : " +ex.getMessage());
		return jsonError;
	}

}
