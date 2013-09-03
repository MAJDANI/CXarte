package com.novedia.talentmap.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.CategoryDao;
import com.novedia.talentmap.store.impl.ConceptDao;
import com.novedia.talentmap.store.impl.ToolDao;

/**
 * The Administration Service implementation.
 * 
 * @test AdminServiceTest.
 * @author v.dibi
 * 
 */
public class AdminService implements IAdminService {

    /**
     * The tool DAO.
     */
//    private IDao<Tool> toolDao;
	private ToolDao toolDao;
    /**
     * The category DAO.
     */
    private IDao<Category> categoryDao;

    /**
     * The concept DAO.
     */
    private IDao<Concept> conceptDao;

    private IDao<Colleague> colleagueDao;

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
    public Category getCategory(Integer categoryId) throws DataAccessException {
    	return categoryDao.get(categoryId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Concept getConcept(Integer conceptId) throws DataAccessException {
    	return conceptDao.get(conceptId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Tool getTool(Integer toolId) throws DataAccessException {
    	return toolDao.get(toolId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tool> getAllTools() throws DataAccessException {
	return toolDao.getAll();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tool> getToolsByConcept(Integer conceptId) throws DataAccessException {
	return toolDao.getToolsByConcept(conceptId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Integer addTool(Tool tool) throws DataAccessException {
	return toolDao.add(tool);
    }

    /**
     * Add one Category
     * 
     * @param category
     * @return
     */
    public Integer addCategory(Category category) throws DataAccessException {
	return categoryDao.add(category);
    }

    /**
     * Add one Concept
     * 
     * @param concept
     * @return
     */
    public Integer addConcept(Concept concept) throws DataAccessException {
	return conceptDao.add(concept);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Tool checkTool(Tool tool) throws DataAccessException {
	return ((ToolDao) toolDao).check(tool);
    }

    /**
     * Add one Category
     * 
     * @param category
     * @return
     */
    public Category checkCategory(Category category) throws DataAccessException {
	return ((CategoryDao) categoryDao).check(category);
    }

    /**
     * Add one Concept
     * 
     * @param concept
     * @return
     */
    public Concept checkConcept(Concept concept) throws DataAccessException {
	return ((ConceptDao) conceptDao).check(concept);
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
     * 
     * @return list a of Category
     * @throws DataAccessException
     */
    @Override
    public List<Category> getAllCategories() throws DataAccessException {
	return categoryDao.getAll();
    }

    /**
     * Get all concepts for a category
     * 
     * @return list of concept
     * @param category
     * @throws DataAccessException
     */
    public List<Concept> getAllConceptByCategory(Category category)
	    throws DataAccessException {
	return ((ConceptDao) conceptDao).getAllConceptByCategory(category);
    }

    /**
     * Save a Tool.
     * 
     * @param skill
     * @return Tool
     * @throws DataAccessException
     */
    Tool saveTool(final VSkill skill) throws DataAccessException {

	if (this.tool == null) {
	    this.tool = Tool.builder().name(skill.getToolName())
		    .concept(this.concept).build();
	    Tool.builder().id(this.toolDao.save(this.tool)).build();
	} else {
	    VSkill sk = this.vSkillDao.getSkillByTool(this.tool.getName());
	    this.category = Category.builder().name(sk.getCategoryName())
		    .build();

	    // TODO : vérifier la nécessité de ce code
	    // this.concept.setName(sk.getConcept_name());
	}
	return tool;
    }
    
    /**
     * Update a Tool.
     * 
     * @param Tool
     * @return Integer
     * @throws DataAccessException
     */
    public Integer updateTool(Tool tool) throws DataAccessException {
    	Integer result = -1;
    	if(tool.getId() != null){
    		result = this.toolDao.save(tool);
    	}
    	return result;
    }
    
    /**
     * Save a Concept.
     * 
     * @param Concept
     * @return Integer
     * @throws DataAccessException
     */
    public Integer saveConcept(Concept concept) throws DataAccessException {
    	Integer result = -1;
    	if(concept.getId() != null){
    		result = this.conceptDao.save(concept);
    	}
    	return result;
    }

    /**
     * This method allow to update one Skill.
     * 
     * @return a map<String, Object>
     * @param category
     *            a category
     * @param concept
     *            a concept
     * @param tool
     *            a tool
     * @throws DataAccessException
     */
    @Override
    public Map<String, Object> updateASkill(Category category, Concept concept,
	    Tool tool) throws DataAccessException {

	if (category != null) {
	    this.categoryDao.save(category);
	    if (concept != null) {
		this.conceptDao.save(concept);
		if (tool != null) {
		    this.toolDao.save(tool);
		}
	    }
	    this.mapNotification.put("typeError", 1);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_UPDATE);
	} else {
	    this.mapNotification.put("typeError", 2);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.UNSUCCESSFUL_UPDATE);
	}
	return this.mapNotification;
    }

    /**
     * This method allow to delete one category.
     * 
     * @param categoryId
     *            a category id
     * @return a map<String, Object>
     * @throws DataAccessException
     */
    @Override
    public Map<String, Object> deleteCategory(int categoryId)
	    throws DataAccessException {

	Category category = Category.builder().id(categoryId).build();
	if (this.categoryDao.delete(category) > 0) {
	    this.mapNotification.put("typeError", 1);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
	} else {
	    this.mapNotification.put("typeError", 3);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.UNSUCCESSFUL_DELETE);
	}
	return this.mapNotification;
    }

    /**
     * This method allow to delete one concept.
     * 
     * @param conceptId
     *            a concept id
     * @return a map<String, Object>
     * @throws DataAccessException
     */
    @Override
    public Map<String, Object> deleteConcept(int conceptId)
	    throws DataAccessException {

	Concept concept = Concept.builder().id(conceptId).build();
	if (this.conceptDao.delete(concept) > 0) {
	    this.mapNotification.put("typeError", 1);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
	} else {
	    this.mapNotification.put("typeError", 3);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.UNSUCCESSFUL_DELETE);
	}
	return this.mapNotification;
    }

    /**
     * This method allow to delete a tool.
     * 
     * @return a map<String, Object>
     * @param toolId
     *            a tool
     * @throws DataAccessException
     */
    @Override
    public Map<String, Object> deleteTool(int toolId)
	    throws DataAccessException {

	Tool tool = Tool.builder().id(toolId).build();
	if (this.toolDao.delete(tool) > 0) {
	    this.mapNotification.put("typeError", 1);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
	} else {
	    this.mapNotification.put("typeError", 3);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.UNSUCCESSFUL_DELETE);
	}
	return this.mapNotification;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteColleague(Set<Colleague> Colleagues) {
	try {
	    for (Colleague colleague : Colleagues) {
		colleagueDao.delete(colleague);
	    }
	    this.mapNotification.put("typeError", 1);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);

	} catch (DataAccessException e) {
	    this.mapNotification.put("typeError", 3);
	    this.mapNotification.put(Constants.MSG_ERROR, Constants.UNSUCCESSFUL_DELETE);
	}
	return mapNotification;
    }

    @Override
    public void updateManagerColleague(int colleagueID) {

    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param categoryDao
     *            .
     */
    public void setCategoryDao(IDao<Category> categoryDao) {
	this.categoryDao = categoryDao;
    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param conceptDao
     *            .
     */
    public void setConceptDao(IDao<Concept> conceptDao) {
	this.conceptDao = conceptDao;
    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param toolDao
     *            a tooldao
     */
    public void setToolDao(ToolDao toolDao) {
	this.toolDao = toolDao;
    }

    /**
     * This method allow to make the spring injection. Set the vSkillDao value
     * 
     * @param vSkillDao
     *            the vSkillDao to set
     */
    public void setvSkillDao(IVSkillDao vSkillDao) {
	this.vSkillDao = vSkillDao;
    }

    /**
     * This method allow to make the spring injection. Set the mapNotification
     * value
     * 
     * @param mapNotification
     *            the mapNotification to set
     */
    public void setMapNotification(Map<String, Object> mapNotification) {
	this.mapNotification = mapNotification;
    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param category
     */
    public void setCategory(Category category) {
	this.category = category;
    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param concept
     */
    public void setConcept(Concept concept) {
	this.concept = concept;
    }

    /**
     * This method allow to make the spring injection.
     * 
     * @param tool
     */
    public void setTool(Tool tool) {
	this.tool = tool;
    }

    /**
     * 
     * @param colleagueDao
     */
    public void setColleagueDao(IDao<Colleague> colleagueDao) {
	this.colleagueDao = colleagueDao;
    }

}