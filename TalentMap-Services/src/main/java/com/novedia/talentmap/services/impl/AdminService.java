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

/**
 * @test AdminServiceTest.
 * @author v.dibi
 *
 */
public class AdminService implements IAdminService {

/**
 * The tool DAO.
 */
private IDao<Tool> toolDao;	
/**
 * The category DAO.
 */
private IDao<Category> categoryDao;

/**
 * The concept DAO.
 */
private IDao<Concept> conceptDao;

/**
 * The vskill DAO.
 */
private IVSkillDao vSkillDao;

/**
 * Entity:Category.
 */
private Category category;

/**
 * Entity:Concept.
 */
private Concept concept;

/**
 * Entity:Tool.
 */
private Tool tool;

/**
 * Map of notification.
 */
private Map<String, Object> mapNotification;

/**
 * {@inheritDoc}
 */
@Override
public List<Tool> getAllTools() throws DataAccessException { 
	return toolDao.getAll();
}

/**
 * {@inheritDoc}.
 */
@Override
public List<Concept> getAllConcepts() throws DataAccessException {
	return conceptDao.getAll();
}

/**
 * Get all categories.
 * @return list a of Category
 * @throws DataAccessException
 */
@Override
public List<Category> getAllCategories() throws DataAccessException {
	return categoryDao.getAll();
}

/**
 * Add one category.
 * @class AdminService.java
 * @param skill a skill
 * @throws DataAccessException
 */
void saveCategory(final VSkill skill) throws DataAccessException {

	int categoryId;
	this.category = this.categoryDao.check(skill.getCategory_name());

	if (this.category == null && this.tool == null) {
		this.category = Category.Builder.builder().build();
		this.category.setName(skill.getCategory_name());
		categoryId = this.categoryDao.save(this.category);
	} 
}

/**
 * Save a concept.
 * @param skill
 */
void saveConcept(final VSkill skill) {

	int conceptId;
	if (this.category != null) {
		this.concept = this.conceptDao.check(skill.getConcept_name());

		if (this.concept == null && this.tool == null) {

			this.concept = Concept.Builder.builder().build();
			this.concept.setName(skill.getConcept_name());
			this.concept.setCategory(category);

			conceptId = this.conceptDao.save(this.concept);
		}
	}
}
/**
 * Save a Tool.
 * @param skill
 * @return Tool
 * @throws DataAccessException
 */
Tool saveTool(final VSkill skill) throws DataAccessException {

	if (this.tool == null) {
		this.tool = Tool.Builder.builder().build();
		tool.setName(skill.getTool_name());
		tool.setConcept(concept);
		int toolId = this.toolDao.save(this.tool);
		tool.setId(toolId);
	} 
	else {
		VSkill sk = this.vSkillDao.getSkillByTool(this.tool.getName());	
		this.category = Category.Builder.builder().build();
		this.category.setName(sk.getCategory_name());
		this.concept.setName(sk.getConcept_name());
	}
	return tool;
}

/**
 * Add one Skill
 * @return <String, Object> map
 * @throws DataAccessException
 */
@Override
public Map<String, Object> addSkill(VSkill skill) throws DataAccessException {

	this.tool = this.toolDao.check(skill.getTool_name());
	saveCategory(skill);
	saveConcept(skill);

	if (saveTool(skill) != null) {
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

/**
 * This method allow to update one Skill.
 * @return a map<String, Object>
 * @param category a category
 * @param concept a concept
 * @param tool a tool
 * @throws DataAccessException
 */
@Override
public Map<String, Object> updateASkill(Category category, Concept concept, Tool tool) throws DataAccessException {

	if (category != null) {
		this.categoryDao.save(category);
		if (concept != null) {
			this.conceptDao.save(concept);
			if (tool != null) {
				this.toolDao.save(tool);
			}
	}
		this.mapNotification.put("typeError", 1);
		this.mapNotification.put("messageError", "successful update");
	}
	else {
		this.mapNotification.put("typeError", 2);
		this.mapNotification.put("messageError", "No skill update");
	}
	return this.mapNotification;
}
/**
 * This method allow to delete one category.
 * @param categoryId a category id
 * @return a map<String, Object>
 * @throws DataAccessException
 */
@Override
public Map<String, Object> deleteCategory(int categoryId) throws DataAccessException {

	Category category = Category.Builder.builder().build();
	category.setId(categoryId);
	if (this.categoryDao.delete(category) > 0) {
		this.mapNotification.put("typeError", 1);
		this.mapNotification.put("messageError", "successful delete");
	}
	else{
		this.mapNotification.put("typeError", 3);
		this.mapNotification.put("messageError", "unsuccessful delete");
	}		
	return this.mapNotification;
}

/**
 * This method allow to delete one concept.
 * @param conceptId a concept id
 * @return a map<String, Object>
 * @throws DataAccessException
 */
@Override
public Map<String, Object> deleteConcept(int conceptId) throws DataAccessException {

	Concept concept = Concept.Builder.builder().build();
	concept.setConcept_id(conceptId);

	if (this.conceptDao.delete(concept) > 0) {
		this.mapNotification.put("typeError", 1);
		this.mapNotification.put("messageError", "successful delete");
	} else {
		this.mapNotification.put("typeError", 3);
		this.mapNotification.put("messageError", "unsuccessful delete");
	}
	return this.mapNotification;
}

/**
 * This method allow to delete a tool.
 * @return a map<String, Object>
 * @param toolId a tool
 * @throws DataAccessException
 */
@Override
public Map<String, Object> deleteTool(int toolId) throws DataAccessException {

	Tool tool = Tool.Builder.builder().build();
	tool.setId(toolId);
	if (this.toolDao.delete(tool) > 0) {
		this.mapNotification.put("typeError", 1);
		this.mapNotification.put("messageError", "successful delete");
	}
	else{
		this.mapNotification.put("typeError", 3);
		this.mapNotification.put("messageError", "unsuccessful delete");
	}
	return this.mapNotification;
}


/**
 * This method allow to make the spring injection.
 * @param categoryDao.
 */
public void setCategoryDao(IDao<Category> categoryDao) {
	this.categoryDao = categoryDao;
}

/**
 * This method allow to make the spring injection.
 * @param conceptDao.
 */
public void setConceptDao(IDao<Concept> conceptDao) {
	this.conceptDao = conceptDao;
}

/**
 * This method allow to make the spring injection.
 * @param toolDao a tooldao
 */
public void setToolDao(IDao<Tool> toolDao) {
	this.toolDao = toolDao;
}

/**
 * This method allow to make the spring injection.
 * Set the vSkillDao value
 * @param vSkillDao the vSkillDao to set
 */
public void setvSkillDao(IVSkillDao vSkillDao) {
	this.vSkillDao = vSkillDao;
}

/**
 * This method allow to make the spring injection.
 * Set the mapNotification value
 * @param mapNotification the mapNotification to set
 */
public void setMapNotification(Map<String, Object> mapNotification) {
	this.mapNotification = mapNotification;
}

/**
 * This method allow to make the spring injection.
 * @param category
 */
public void setCategory(Category category) {
	this.category = category;
}

/**
 * This method allow to make the spring injection.
 * @param concept
 */
public void setConcept(Concept concept) {
	this.concept = concept;
}

/**
 * This method allow to make the spring injection.
 * @param tool
 */
public void setTool(Tool tool) {
	this.tool = tool;
}
}