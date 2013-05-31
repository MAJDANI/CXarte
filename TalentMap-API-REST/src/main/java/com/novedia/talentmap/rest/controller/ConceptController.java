package com.novedia.talentmap.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.services.IAdminService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
public class ConceptController {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * @param categoryId
	 * @return concept
	 */
	@RequestMapping(value = "/concepts/{categoryId}/")
	@ResponseBody
	public List<Concept> getAll(@PathVariable Integer categoryId) {
		Category category = Category.builder().id(categoryId).build();
		List<Concept> concepts = adminService.getAllConceptByCategory(category);
		return concepts;
	}
	
	/**
	 * @param categoryId
	 * @param concept_name
	 * @return category
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
	 * @param conceptId
	 */
	@RequestMapping(value = "/concept/{conceptId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteConcept(@PathVariable Integer conceptId) {
		adminService.deleteConcept(conceptId);
	}
}
