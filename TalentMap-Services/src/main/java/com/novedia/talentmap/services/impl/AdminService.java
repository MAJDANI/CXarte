package com.novedia.talentmap.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.store.ICategoryDao;
import com.novedia.talentmap.store.IConceptDao;
import com.novedia.talentmap.store.IToolDao;
import com.novedia.talentmap.store.IVSkillDao;

public class AdminService implements IAdminService {

	private IToolDao toolDao;
	private IConceptDao conceptDao;
	private ICategoryDao categoryDao;
	private IVSkillDao vSkillDao;

	private Category category;
	private Concept concept;
	private Tool tool;
	
	private Map<String, Object> mapNotification;

	/**
	 * Get all tools
	 */
	@Override
	public List<Tool> getAllTools() throws Exception {

		return toolDao.selectAll();
	}

	/**
	 * Get all concepts
	 */
	@Override
	public List<Concept> getAllConcepts() throws Exception {

		return conceptDao.selectAll();
	}

	/**
	 * Get all categories
	 */
	@Override
	public List<Category> getAllCategories() throws Exception {

		return categoryDao.selectAll();
	}

	/**
	 * Add one category
	 * 
	 * @class AdminService.java
	 * @param skill
	 * @throws Exception
	 */
	private void saveCategory(VSkill skill) throws Exception {

		int categoryId;
		this.category = this.categoryDao
				.checkCategory(skill.getCategory_name());

		if (this.category == null && this.tool == null) {

			this.category = new Category();
			this.category.setName(skill.getCategory_name());

			categoryId = this.categoryDao.save(this.category);

		} else if(this.category != null){

			categoryId = this.category.getId();
			
			this.category.setId(categoryId);
		}
		
	}

	/**
	 * Add one concept
	 * 
	 * @class AdminService.java
	 * @param skill
	 * @throws Exception
	 */
	private void saveConcept(VSkill skill) throws Exception {

		int conceptId;
		
		if(this.category != null){
			this.concept = this.conceptDao.checkConcept(skill.getConcept_name(),
					this.category.getId());
		}

		if (this.concept == null && this.tool == null) {

			this.concept = new Concept();
			this.concept.setName(skill.getConcept_name());
			this.concept.setCategory_id(this.category.getId());

			conceptId = this.conceptDao.save(this.concept);

		} else if(this.concept != null && this.category != null) {

			conceptId = this.concept.getId();
			
			this.concept.setId(conceptId);
		}

		
	}

	/**
	 * Add one tool
	 * 
	 * @class AdminService.java
	 * @param skill
	 * @return
	 * @throws Exception
	 */
	private boolean saveTool(VSkill skill) throws Exception {

		if (this.tool == null) {

			this.tool = new Tool();
			tool.setName(skill.getTool_name());
			tool.setConcept_id(this.concept.getId());

			this.toolDao.save(this.tool);

			return true;
		} else {
			VSkill sk = this.vSkillDao.getSkillByTool(this.tool.getName());
			
			this.category = new Category();
			this.category.setName(sk.getCategory_name());
			
			this.concept = new Concept();
			this.concept.setName(sk.getConcept_name());
			
			return false;

		}
	}

	/**
	 * Add one Skill
	 */
	@Override
	public Map<String, Object> addOneSkill(VSkill skill) throws Exception {

		this.tool = this.toolDao.checkTool(skill.getTool_name());
		
		saveCategory(skill);

		saveConcept(skill);

		if (saveTool(skill)) {

			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La compétence a bien été ajoutée");
		} else {

			this.mapNotification.put("typeError", 2);
			this.mapNotification.put(
					"messageError",
					"Cet outil existe déjà pour la catégorie \""
							+ this.category.getName()
							+ "\" et le concept \""
							+ this.concept.getName() + "\" .");
		}

		return this.mapNotification;
	}
	
	@Override
	public Map<String, Object> updateOneSkill(Category category, Concept concept, Tool tool) throws Exception {
		
		if(category != null){
			
			this.categoryDao.update(category);
			
			if(concept != null){
				
				this.conceptDao.update(concept);
				
				if(tool != null){
					
					this.toolDao.update(tool);
				}
			}
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La compétence a bien été modifiée");
		}else{
		
			this.mapNotification.put("typeError", 2);
			this.mapNotification.put("messageError", "Aucune compétence n'a été modifiée");
		}
		
		
		return this.mapNotification;
	}
	
	@Override
	public Map<String, Object> deleteCategory(int category_id) throws Exception {
		
		if(this.categoryDao.delete(category_id) > 0){
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La catégorie a bien été supprimée");
		}else{
			
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}

	@Override
	public Map<String, Object> deleteConcept(int concept_id) throws Exception {

		if(this.conceptDao.delete(concept_id) > 0){
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "Le concept a bien été supprimé");
		}else{
			
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}

	@Override
	public Map<String, Object> deleteTool(int tool_id) throws Exception {

		if(this.toolDao.delete(tool_id) > 0){
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "L'outil a bien été supprimé");
		}else{
			
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}


	/**
	 * Set the toolDao value
	 * 
	 * @param toolDao
	 *            the toolDao to set
	 */
	public void setToolDao(IToolDao toolDao) {
		this.toolDao = toolDao;
	}

	/**
	 * Set the conceptDao value
	 * 
	 * @param conceptDao
	 *            the conceptDao to set
	 */
	public void setConceptDao(IConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}

	/**
	 * Set the categoryDao value
	 * 
	 * @param categoryDao
	 *            the categoryDao to set
	 */
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 * Set the vSkillDao value
	 * @param vSkillDao the vSkillDao to set
	 */
	public void setvSkillDao(IVSkillDao vSkillDao) {
		this.vSkillDao = vSkillDao;
	}
	
	/**
	 * Set the mapNotification value
	 * @param mapNotification the mapNotification to set
	 */
	public void setMapNotification(Map<String, Object> mapNotification) {
		this.mapNotification = mapNotification;
	}

}
