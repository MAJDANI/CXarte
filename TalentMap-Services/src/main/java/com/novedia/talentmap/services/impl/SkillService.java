package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.SkillDao;
import com.novedia.talentmap.store.impl.VSkillDao;

/**
 * Skill Services.
 * 
 * @author j.collet
 */
public class SkillService implements ISkillService {

	/**
	 * TOOL_PONDERATION = 3.0.
	 */
	private static final Double TOOL_PONDERATION = 5.0;
	/**
	 * FREQUENCY_USE_PONDERATION = 1.0.
	 */
	private static final Double FREQUENCY_USE_PONDERATION = 3.0;
	/**
	 * NO_USING_TIME_PONDERATION = 5.0.
	 */
	private static final Double NO_USING_TIME_PONDERATION = 1.0;

	/**
	 * skill DAO.
	 */
	private SkillDao skillDao;

	/**
	 * tool DAO.
	 */
	private IDao<Tool> toolDao;

	/**
	 * concept DAO.
	 */
	private IDao<Concept> conceptDao;

	/**
	 * category DAO.
	 */
	private IDao<Category> categoryDao;

	/**
	 * VSkill DAO.
	 */
	private IVSkillDao vSkillDao;

	/**
	 * VSkillCollaborator DAO.
	 */
	private IVSkillCollabDao vSkillCollabDao;

	/**
	 * @return Skill.
	 * @param collaboratorID
	 * @param toolId
	 * @throws DataAccessException
	 */
	@Override
	public Skill getSkillByToolId(int collaboratorID, int toolId)
			throws DataAccessException {
		return this.skillDao.getCollaboratorSkillByTool(collaboratorID, toolId);
	}

	/**
	 * @return List<Integer>.
	 * @param toolId
	 * @throws DataAccessException
	 */
	@Override
	public List<Integer> getAllColleagueIdByToolId(Integer toolId)
			throws DataAccessException {
		return this.skillDao.getAllColleagueIdByToolId(toolId);
	}
	
	/**
	 * @return List<Integer>.
	 * @param toolId , managerId
	 * @throws DataAccessException
	 */
	@Override
	public List<Integer> getCmColleagueIdByToolId(Integer toolId,Integer managerId)
			throws DataAccessException {
		return this.skillDao.getCmColleagueIdByToolId(toolId,managerId);
	}

	/**
	 * @return List<Integer>.
	 * @param listToolId
	 * @throws DataAccessException
	 */
	@Override
	public List<Integer> getAllColleagueIdByListToolId(List<Integer> listToolId)
			throws DataAccessException {
		return this.skillDao.getAllColleagueIdByListToolId(listToolId);
	}
	
	/**
	 * @return List<Integer>.
	 * @param listToolId
	 * @throws DataAccessException
	 */
	@Override
	public List<Integer> getCmColleagueIdByListToolId(List<Integer> listToolId,int managerId)
			throws DataAccessException {
		return this.skillDao.getCmColleagueIdByListToolId(listToolId,managerId);
	}

	/**
	 * @param collabId
	 * @return a list of VSkillCollab
	 * @throws DataAccessException
	 */
	@Override
	public List<VSkillCollab> getAllSkillCollab(int collabId)
			throws DataAccessException {
		return this.vSkillCollabDao.getAllSkillCollab(collabId);
	}

	/**
	 * This method allow to return all collaborator skill.
	 * 
	 * @param collabId
	 * @return a map<Category, Map>
	 * @throws DataAccessException
	 */
	@Override
	public CategoryMapDTO getAllCollaboratorSkill(int collabId) {

		List<Skill> listSkill = new ArrayList<Skill>();
		ToolSkillMap toolSkillMap ;
		ConceptMapDTO conceptMapDto ;
		CategoryMapDTO categoryMapDto = new CategoryMapDTO();
		listSkill = skillDao.getAllCollaboratorSkill(collabId);
		if (listSkill != null && !listSkill.isEmpty()) {
			toolSkillMap = new ToolSkillMap();
			conceptMapDto = new ConceptMapDTO();
			buildTool(listSkill, toolSkillMap.getMapTool());
			buildConceptMapDto(toolSkillMap,conceptMapDto);
			buildCategoryMapDto(conceptMapDto,categoryMapDto);
			
		}
		return categoryMapDto;
	}
	

	/**
	 * 
	 * compute sum of average tool's concept
	 * 
	 * @param mapTools map of tools and skill
	 * @return sum of average tool's concept
	 */
	public static Integer sumAverageToolConcept(Map<Tool, Skill> mapTools) {
		Integer sum = 0;
		if (mapTools != null) {
			for (Entry<Tool, Skill> tool : mapTools.entrySet()) {
				sum += tool.getValue().getAverageScore();
			}
		}
		return sum;
	}

	/**
	 * 
	 * compute average's tool
	 * @param toolNote  score of tool
	 * @param usingFrequencyTool using frequency tool
	 * @param timeNotUsingTool time not using tool
	 * @return average's tool
	 */
	public static double computeToolAverage(final double toolNote,
			final double usingFrequencyTool, final double timeNotUsingTool) {

		double noUsingTimeValue = 0;
		int intTimeNotUsingTool = (int) timeNotUsingTool;
		switch (intTimeNotUsingTool) {
		case 1:
			noUsingTimeValue = 5;
			break;
		case 2:
			noUsingTimeValue = 3;
			break;
		case 3:
			noUsingTimeValue = 1;
			break;
		case 4:
			noUsingTimeValue = 0;
			break;
		}

		double usingFrequencyToolValue = 0;
		int intUsingFrequencyTool = (int) usingFrequencyTool;
		switch (intUsingFrequencyTool) {
		case 1:
			usingFrequencyToolValue = 1;
			break;
		case 2:
			usingFrequencyToolValue = 3;
			break;
		case 3:
			usingFrequencyToolValue = 5;
			break;
		}

		return Math.round((TOOL_PONDERATION * toolNote + FREQUENCY_USE_PONDERATION
						* usingFrequencyToolValue + NO_USING_TIME_PONDERATION
						* noUsingTimeValue)
						/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
	}

	/**
	 * Build CategoryMapDTO which contain each concept group by category
	 * @param conceptMapDto  conceptMapDto which contain all concept skill of colleague
	 * @param categoryMapDto categoryMapDto to return
	 */
	public void buildCategoryMapDto(ConceptMapDTO conceptMapDto, CategoryMapDTO categoryMapDto){
		
		for (Map.Entry<Concept, ToolSkillMap> entry : conceptMapDto.getMapConcept().entrySet()) {
			Category category = getCategoryById(entry.getKey().getCategory().getId());
			
			if(!categoryMapDto.getMapCategory().containsKey(category)){
				ConceptMapDTO conceptMapDtoTmp = new ConceptMapDTO();
				conceptMapDtoTmp.getMapConcept().put(entry.getKey(), entry.getValue());
				categoryMapDto.getMapCategory().put(category, conceptMapDtoTmp);
			} else{
				ConceptMapDTO conceptMapDtoTmp = categoryMapDto.getMapCategory().get(category);
				conceptMapDtoTmp.getMapConcept().put(entry.getKey(), entry.getValue());
				categoryMapDto.getMapCategory().put(category, conceptMapDtoTmp);
			}
		}
		
	}

	
	/**
	 * Build conceptMapDto which contain each skill group by concept
	 * @param toolSkillMap map which contain all skill's colleague
	 * @param conceptMapDto conceprMapDto to return
	 */
	public void buildConceptMapDto(ToolSkillMap toolSkillMap, ConceptMapDTO conceptMapDto){
		
		for (Map.Entry<Tool, Skill> entry : toolSkillMap.getMapTool().entrySet()) {
			Integer conceptId = entry.getKey().getConcept().getId();
			Concept concept = getConceptById(conceptId);
			
			if(!conceptMapDto.getMapConcept().containsKey(concept)){
				ToolSkillMap toolSkillMapTmp = new ToolSkillMap();
				toolSkillMapTmp.getMapTool().put(entry.getKey(), entry.getValue());
				conceptMapDto.getMapConcept().put(concept, toolSkillMapTmp);
				
			} else{
				ToolSkillMap toolSkillMapTmp = conceptMapDto.getMapConcept().get(concept);
				toolSkillMapTmp.getMapTool().put(entry.getKey(), entry.getValue());
				conceptMapDto.getMapConcept().put(concept, toolSkillMapTmp);
			}
		}
		
		//Compute score of each concept
		for (Map.Entry<Concept, ToolSkillMap> conceptEntry : conceptMapDto.getMapConcept().entrySet()) {
			double scoreConcept = 0;
			List<Tool> toolByConcept = getAllToolsByConcept(conceptEntry.getKey().getId());
			Integer sum = sumAverageToolConcept(conceptEntry.getValue().getMapTool());
			scoreConcept = sum / (toolByConcept.size() * 1.0);
			scoreConcept = Math.round(scoreConcept);
			conceptEntry.getKey().setScore(scoreConcept);
		}
		
	}
	
	
	/**
	 * build mapTool given in second parameter : a map of skills the colleague
	 * has.. Uses listSkill, the list of skills of the colleague, and gets for
	 * each skill the tool.
	 * 
	 * @param listSkill
	 * @param mapTool
	 *            : the map to build
	 */
	void buildTool(List<Skill> listSkill, Map<Tool, Skill> mapTool) {
		for (Skill skill : listSkill) {
			Tool tool1 = toolDao.get(skill.getTool_id());
			if (tool1 != null) {
				int score = (int) computeToolAverage(skill.getScore(),
						skill.getUse_frequency(), skill.getNo_using_time());
				skill.setAverageScore(score);
				mapTool.put(tool1, skill);
			}
		}
	}

	/**
	 * This method allow to get all tools.
	 * 
	 * @return a list of Tool
	 * @throws DataAccessException
	 */
	@Override
	public List<Tool> getAllTools() throws DataAccessException {
		return toolDao.getAll();
	}

	public List<Tool> getAllToolsByConcept(int conceptId) {
		return skillDao.getAllByConcept(conceptId);
	}

	/**
	 * This method allow to get all VSkill ordered by category and concept
	 * 
	 * @return a list of VSkill
	 * @throws DataAccessException
	 */
	public List<VSkill> getAllVSkillOrdered() throws DataAccessException {
		VSkillDao vSkillDao = (VSkillDao) this.vSkillDao;
		return vSkillDao.getAllVSkillOrdered();
	}

	/**
	 * This method allow to get all tools.
	 * 
	 * @return a list of Tool
	 * @param toolName
	 * @throws DataAccessException
	 */
	@Override
	public VSkill getSkillByTool(final String toolName)
			throws DataAccessException {
		return vSkillDao.getSkillByTool(toolName);
	}

	/**
	 * This method allow to add skill.
	 * 
	 * @param skilla skill
	 * @throws DataAccessException
	 */
	@Override
	public void addSkill(Skill skill) throws DataAccessException {
		this.skillDao.add(skill);

	}

	/**
	 * This method allow to save skill.
	 * 
	 * @param skill
	 *            a skill
	 * @throws DataAccessException
	 */
	@Override
	public void saveSkill(Skill skill) throws DataAccessException {
		skillDao.save(skill);
	}

	/**
	 * This method allow to get a tool by name.
	 * 
	 * @return Tool one tool
	 * @param name
	 * @throws DataAccessException
	 */
	@Override
	public Tool getToolByName(String name) throws DataAccessException {
		return toolDao.getByName(name);
	}

	// INTERNAL METHODS

	/**
	 * Get a concept by the value of its id.
	 * 
	 * @param conceptId
	 */
	Concept getConceptById(Integer conceptId) {
		// return conceptDao.get(concept_id);

		List<Concept> conceptList = conceptDao.getAll();
		Concept currentConcept = null;
		for (Concept concept : conceptList) {
			if (concept.getId().equals(conceptId)) {
				currentConcept = concept;
			}
		}
		return currentConcept;
	}

	/**
	 * Get a concept by the value of its id.
	 * 
	 * @param categoryId
	 * @return a category
	 */
	Category getCategoryById(Integer categoryId) {

		List<Category> categoryList = categoryDao.getAll();
		Category currentCategory = null;
		for (Category category : categoryList) {
			if (category.getId().equals(categoryId)) {
				currentCategory = category;
			}
		}
		return currentCategory;
	}

	// SETTERS

	/**
	 * Set the tool DAO.
	 * 
	 * @param toolDao
	 */
	public void setToolDao(IDao<Tool> toolDao) {
		this.toolDao = toolDao;
	}

	/**
	 * Set the concept DAO. This method allow to make the spring injection.
	 * 
	 * @param conceptDao
	 */
	public void setConceptDao(IDao<Concept> conceptDao) {
		this.conceptDao = conceptDao;
	}

	/**
	 * This method allow to make the spring injection. Set the category DAO
	 * 
	 * @param categoryDao
	 */
	public void setCategoryDao(IDao<Category> categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * This method allow to make the spring injection. Set the vSkill DAO
	 * 
	 * @param vSkillDao
	 */
	public void setvSkillDao(IVSkillDao vSkillDao) {
		this.vSkillDao = vSkillDao;
	}

	/**
	 * This method allow to make the spring injection. Set the
	 * vSkillCollaborator DAO
	 * 
	 * @param vSkillCollabDao
	 */
	public void setvSkillCollabDao(IVSkillCollabDao vSkillCollabDao) {
		this.vSkillCollabDao = vSkillCollabDao;
	}

	/**
	 * This method allow to make the spring injection. Set the skill DAO
	 * 
	 * @param skillDao
	 */
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

}