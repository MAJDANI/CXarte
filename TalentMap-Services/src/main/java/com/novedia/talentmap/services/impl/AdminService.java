package com.novedia.talentmap.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillDao;

public class AdminService implements IAdminService {

	/**
	 * The tool DAO
	 */
	private IDao<Tool> toolDao;
	
	/**
	 * The category DAO
	 */
	private IDao<Category> categoryDao;
	
	/**
	 * The concept DAO
	 */
	private IDao<Concept> conceptDao;
	
	/**
	 * The skill DAO
	 */
	private IVSkillDao vSkillDao;
	
	/**
	 * 
	 */
	private Category category;
	
	/**
	 * 
	 */
	private Concept concept;
	
	/**
	 * 
	 */
	private Tool tool;
	
	/**
	 * 
	 */
	private Map<String, Object> mapNotification;

	/**
	 * Get all tools
	 */
	@Override
	public List<Tool> getAllTools() throws DataAccessException{ 
		return toolDao.getAll();
	}

	/**
	 * Get all concepts
	 */
	@Override
	public List<Concept> getAllConcepts() throws DataAccessException {
		return conceptDao.getAll();
	}

	/**
	 * Get all categories
	 */
	@Override
	public List<Category> getAllCategories() throws DataAccessException {
		return categoryDao.getAll();
	}

	/**
	 * Add one category
	 * 
	 * @class AdminService.java
	 * @param skill
	 * @throws Exception
	 */
	private void saveCategory(VSkill skill) throws DataAccessException {

		int categoryId;
		this.category = this.categoryDao.check(skill.getCategory_name());

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
	private void saveConcept(VSkill skill) throws DataAccessException {

		int conceptId;
		
		if(this.category != null){
			// TODO : vérifier dans la précédente version du fichier la définition de cette méthode
			//this.concept = this.conceptDao.checkConcept(skill.getConcept_name(), this.category.getId());
			this.concept = this.conceptDao.check(skill.getConcept_name());
		}

		if (this.concept == null && this.tool == null) {
			
			// TODO : A virer !!!
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
	private Tool saveTool(VSkill skill) throws DataAccessException {

		if (this.tool == null) {
			this.tool = new Tool();
			tool.setName(skill.getTool_name());
			tool.setConcept_id(this.concept.getId());
			int toolId = this.toolDao.save(this.tool);
			tool.setId(toolId);
		} 
		else {
			VSkill sk = this.vSkillDao.getSkillByTool(this.tool.getName());	
			this.category = new Category();
			this.category.setName(sk.getCategory_name());
			this.concept = new Concept();
			this.concept.setName(sk.getConcept_name());
		}
		
		return tool;
	}

	/**
	 * Add one Skill
	 */
	@Override
	public Map<String, Object> addSkill(VSkill skill) throws DataAccessException {

		this.tool = this.toolDao.check(skill.getTool_name());
		
		saveCategory(skill);

		saveConcept(skill);

		if (saveTool(skill) !=null) {
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La compétence a bien été ajoutée");
		}
		else {

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
	public Map<String, Object> updateOneSkill(Category category, Concept concept, Tool tool) throws DataAccessException {
		
		if(category != null){
			
			this.categoryDao.save(category);
			
			if(concept != null){
				
				this.conceptDao.save(concept);
				
				if(tool != null){
					
					this.toolDao.save(tool);
				}
			}
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La compétence a bien été modifiée");
		}
		else {
			this.mapNotification.put("typeError", 2);
			this.mapNotification.put("messageError", "Aucune compétence n'a été modifiée");
		}
		
		return this.mapNotification;
	}
	
	@Override
	public Map<String, Object> deleteCategory(int category_id) throws DataAccessException {
		Category category = new Category();
		category.setId(category_id);
		if(this.categoryDao.delete(category) > 0){
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "La catégorie a bien été supprimée");
		}
		else{
			
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}

	@Override
	public Map<String, Object> deleteConcept(int concept_id) throws DataAccessException {

		Concept concept = new Concept();
		concept.setId(concept_id);
		
		if(this.conceptDao.delete(concept) > 0){
			
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "Le concept a bien été supprimé");
		}else{
			
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}

	@Override
	public Map<String, Object> deleteTool(int tool_id) throws DataAccessException {

		Tool tool = new Tool();
		tool.setId(tool_id);
		
		if(this.toolDao.delete(tool) > 0){	
			this.mapNotification.put("typeError", 1);
			this.mapNotification.put("messageError", "L'outil a bien été supprimé");
		}
		else{
			this.mapNotification.put("typeError", 3);
			this.mapNotification.put("messageError", "Un problème s'est produit lors de la suppression");
		}
		
		return this.mapNotification;
	}


	/**
	 * 
	 * @param categoryDao
	 */
	public void setCategoryDao(IDao<Category> categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 * 
	 * @param conceptDao
	 */
	public void setConceptDao(IDao<Concept> conceptDao) {
		this.conceptDao = conceptDao;
	}
	
	/**
	 * 
	 * @param toolDao
	 */
	public void setToolDao(IDao<Tool> toolDao) {
		this.toolDao = toolDao;
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

	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * 
	 * @param concept
	 */
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	/**
	 * 
	 * @param tool
	 */
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
}
