package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.services.ScoreManage;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.impl.SkillDao;

/**
 * Skill Services
 * 
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class SkillService implements ISkillService {

	/**
	 * skill DAO
	 */
	private SkillDao skillDao;

	/**
	 * tool DAO
	 */
	private IDao<Tool> toolDao;

	/**
	 * concept DAO
	 */
	private IDao<Concept> conceptDao;

	/**
	 * category DAO
	 */
	private IDao<Category> categoryDao;

	/**
	 * VSkill DAO
	 */
	private IVSkillDao vSkillDao;

	/**
	 * VSkillCollaborator DAO
	 */
	private IVSkillCollabDao vSkillCollabDao;

	
	@Override
	public Skill getSkillByToolId(int collaboratorID, int toolId) throws DataAccessException {
		return this.skillDao.getOneCollaboratorSkill(collaboratorID, toolId);
	}

	@Override
	public List<VSkillCollab> getAllSkillCollab(int collab_id)throws DataAccessException {
		return this.vSkillCollabDao.getAllSkillCollab(collab_id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<Category, Map> getAllCollaboratorSkill(int collabId)	throws DataAccessException {

		List<Skill> listSkill = new ArrayList<Skill>();

		// A Tool Map (Tool Object, Integer:Score)
		Map<Tool, Integer> mapTool = new HashMap<Tool, Integer>();

		// A Concept Map (Concept, Tool Map which matches with the Concept)
		Map<Concept, Map> mapConcept = new HashMap<Concept, Map>();

		// A Category Map (Category, Concept Map which matches with the
		// Category)
		Map<Category, Map> mapCategory = new HashMap<Category, Map>();

		// We take all Collaborators Skills
		listSkill = skillDao.getAllCollaboratorSkill(collabId);

		// We build the Tool Map
		buildTool(listSkill, mapTool);
		
		List<Integer> listToolScore = new ArrayList<Integer>();
		Concept conceptTMP = null;
		double conceptScore = 0;

		// We build the Concept Map
		conceptTMP = buildConcept(mapTool, mapConcept, listToolScore,conceptTMP);
		
		// We calculate the score of the last concept if it's not null
		if (conceptTMP != null) {
			conceptScore = ScoreManage.ConceptScore(listToolScore, toolDao.getAll().size());

			Map<Tool, Integer> mapTMP = mapConcept.get(conceptTMP);
			mapConcept.remove(conceptTMP);

			conceptTMP.setScore(conceptScore);
			mapConcept.put(conceptTMP, mapTMP);
		}

		// We build the Category Map
		return buildCategory(mapConcept, mapCategory);
	}
	
	/**
	 * build category
	 * This method returns a map.
	 * This map contains an object "Category" and a map of the "Concept" 
	 * It takes two parameters maps "Concept" and "Category"
	 * The mapConcept : contains an object "Concept" and a map of the "Tool"
	 * The mapCategory : contains an object "Concept" and a map of the "Concept"
	 * @param mapConcept
	 * @param mapCategory
	 * @return
	 */

	 @SuppressWarnings("rawtypes")
	Map<Category, Map> buildCategory(Map<Concept, Map> mapConcept, Map<Category, Map> mapCategory) {
		
		if(mapConcept != null){
			for (Map.Entry<Concept, Map> entry : mapConcept.entrySet()){
				
				Category category = getCategoryById(entry.getKey().getCategory().getId());
				//tester si on peut écrire ce qui suit :
				//Category category = entry.getKey().getCategory();

				if (!mapCategory.containsKey(category)) {
					//mapCategory.put(category, new HashMap<Concept, Map>());
					//mapCategory.get(category).put(entry.getKey(), entry.getValue());
					Map<Concept, Map> newConceptMap = new HashMap<Concept, Map>();
					newConceptMap.put(entry.getKey(), entry.getValue());
					mapCategory.put(category, newConceptMap);
				} else {
					//mapCategory.containsKey(category);
					mapCategory.get(category).put(entry.getKey(), entry.getValue());
				}
			}
		}	
		return mapCategory;
	}

	/**
	 * build concept
	 * This method returns an object "Concept".
	 * It takes four parameters maps "mapTool","mapConcept","listToolScore" and "Concept"
	 * The mapTool : contains an object "Concept" and a map of the "Tool"
	 * The mapConcept : contains an object "Concept" and a map of the "Tool"
	 * The listToolScore : is a list of Integer
	 * An object Concept
	 * @param mapTool
	 * @param mapConcept
	 * @param listToolScore
	 * @param conceptTMP
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Concept buildConcept(Map<Tool, Integer> mapTool,Map<Concept, Map> mapConcept, List<Integer> listToolScore,
			Concept conceptTMP) {
		double conceptScore;
		for (Map.Entry<Tool, Integer> entry : mapTool.entrySet()) {
			// TODO : NullPointerException
			Integer concept_id = entry.getKey().getConcept().getConcept_id();
			Concept concept = getConceptById(concept_id);

			if (!mapConcept.isEmpty()) {

				// We calculate the Concept Score
				// TODO : CODE à MODIFIER nécessitant de récupérer
				// programatiquement les outil correspondant à ce concept

				// conceptScore = ScoreManage.ConceptScore(listToolScore,
				// toolDao.selectAllByConceptId(conceptTMP.getId()).size());
				conceptScore = ScoreManage.ConceptScore(listToolScore, toolDao.getAll().size());

				Map<Tool, Integer> mapTMP = mapConcept.get(conceptTMP);
				mapConcept.remove(conceptTMP);

				conceptTMP.setScore(conceptScore);
				mapConcept.put(conceptTMP, mapTMP);

				conceptTMP = concept;
			} else {
				conceptTMP = concept;
			}

			if (!mapConcept.containsKey(concept)) {

				listToolScore.clear();

				mapConcept.put(concept, new HashMap<Tool, Integer>());
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
			} else {

				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
			}
			// We put in the list the Tool Score
			listToolScore.add(entry.getValue());
		}
		return conceptTMP;
	}
	
	/**
	 * build tool
	 * @param listSkill
	 * @param mapTool
	 */
	 void buildTool(List<Skill> listSkill, Map<Tool, Integer> mapTool) {
		for (Skill s : listSkill) {
			Tool tool1 = toolDao.get(s.getTool_id());

			// We put only not null tool element in mapTool
			if (tool1 != null) {
				// We give a score to the tool
				int score = (int) ScoreManage.ToolScore(s.getScore(),s.getUse_frequency(), s.getNo_using_time());
				mapTool.put(tool1, score);
			}
		}
	}

	@Override
	public List<Tool> getAllTools() throws DataAccessException {
		return toolDao.getAll();
	}

	@Override
	public List<VSkill> getToolByConcept(String categoryName, String conceptName)
			throws DataAccessException {
		return vSkillDao.getToolByConcept(categoryName, conceptName);
	}

	@Override
	public VSkill getSkillByTool(String toolName) throws DataAccessException {
		return vSkillDao.getSkillByTool(toolName);
	}

	@Override
	public List<VSkill> getConceptByCategory(String categoryName)
			throws DataAccessException {
		return vSkillDao.getConceptByCategory(categoryName);
	}

	@Override
	public void addSkill(Skill skill) throws DataAccessException {
		this.skillDao.add(skill);

	}

	@Override
	public void saveSkill(Skill skill) throws DataAccessException {
		skillDao.save(skill);
	}

	@Override
	public Tool getToolByName(String name) throws DataAccessException {
		return toolDao.getByName(name);
	}

	// INTERNAL METHODS

	/**
	 * Get a concept by the value of its id
	 * 
	 * @param concept_id
	 */
	Concept getConceptById(Integer concept_id) {
//		return conceptDao.get(concept_id);

		List<Concept> conceptList = conceptDao.getAll();
		Concept currentConcept = null;
		for (Concept concept : conceptList) {
			if (concept.getConcept_id().equals(concept_id)) {
				currentConcept = concept;
			}
		}
		return currentConcept;
	}

	/**
	 * Get a concept by the value of its id
	 * 
	 * @param concept_id
	 */
	Category getCategoryById(Integer category_id) {

		List<Category> categoryList = categoryDao.getAll();
		Category currentCategory = null;
		for (Category category : categoryList) {
			if (category.getId().equals(category_id)) {
				currentCategory = category;
			}
		}
		return currentCategory;
		
	}

	// SETTERS
	
	/**
	 * Set the tool DAO
	 * @param toolDao
	 */
	public void setToolDao(IDao<Tool> toolDao) {
		this.toolDao = toolDao;
	}
	
	/**
	 * Set the concept DAO 
	 * @param conceptDao
	 */
	public void setConceptDao(IDao<Concept> conceptDao) {
		this.conceptDao = conceptDao;
	}
	
	/**
	 * Set the category DAO 
	 * @param categoryDao
	 */
	public void setCategoryDao(IDao<Category> categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 * Set the vSkill DAO 
	 * @param vSkillDao
	 */
	public void setvSkillDao(IVSkillDao vSkillDao) {
		this.vSkillDao = vSkillDao;
	}
	
	/**
	 * Set the vSkillCollaborator DAO 
	 * @param vSkillCollabDao
	 */
	public void setvSkillCollabDao(IVSkillCollabDao vSkillCollabDao) {
		this.vSkillCollabDao = vSkillCollabDao;
	}
	
	/**
	 * Set the skill DAO 
	 * @param skillDao
	 */
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}
}