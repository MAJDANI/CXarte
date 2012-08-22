package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.services.ScoreManage;
import com.novedia.talentmap.store.ICategoryDao;
import com.novedia.talentmap.store.IConceptDao;
import com.novedia.talentmap.store.ISkillDao;
import com.novedia.talentmap.store.IToolDao;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.IVSkillDao;

/**
 * Skill Services
 * 
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class SkillService implements ISkillService {

	private ISkillDao skillDao;
	private IToolDao toolDao;
	private IConceptDao conceptDao;
	private ICategoryDao categoryDao;
	private IVSkillDao vSkillDao;
	private IVSkillCollabDao vSkillCollabDao;

	/**
	 * We take all Collaborator Skills By ID (new version)
	 */
	@Override
	public List<VSkillCollab> getAllSkillCollab(int collab_id) throws Exception {
		System.out.println("SkillService.getAllSkillCollab() collab_id=" + collab_id);
		
		return this.vSkillCollabDao.getAllSkillCollab(collab_id);
	}
	
	/**
	 * We take all Collaborator Skills By ID (old version)
	 */
	@Override
	public Map<Category, Map> getAllCollaboratorSkill(int collab_id)
			throws Exception {
		System.out.println("SkillService.getAllCollaboratorSkill() collab_id=" + collab_id);

		Map<String, List> skillMap = new HashMap<String, List>();

		List<Skill> listSkill = new ArrayList<Skill>();

		// A Tool Map (Tool Object, Integer:Score)
		Map<Tool, Integer> mapTool = new HashMap<Tool, Integer>();

		// A Concept Map (Concept, Tool Map which matches with the Concept)
		Map<Concept, Map> mapConcept = new HashMap<Concept, Map>();

		// A Category Map (Category, Concept Map which matches with the
		// Category)
		Map<Category, Map> mapCategory = new HashMap<Category, Map>();

		// We take all Collaborators Skills
		listSkill = skillDao.getAllCollaboratorSkill(collab_id);

		System.out.println("on vient de récupérer listSkill=" + listSkill);
		
		System.out.println("--------------------------");
		for (Skill s : listSkill) {
			System.out.println("skill=" + s);
		}
		System.out.println("--------------------------");

		System.out.println("on va construire toolMap en bouclant sur chaque skill");

		System.out.println("_____________________________");
		// We build the Tool Map
		for (Skill s : listSkill) {
			Tool tool = toolDao.getById(s.getTool_id());
			
			System.out.println("tool=" + tool);
			
			//We give a score to the tool
			int score = (int) ScoreManage.ToolScore(s.getScore(),
					s.getUse_frequency(), s.getNo_using_time());
			System.out.println("score=" + score);

			mapTool.put(tool, score);
		}
		System.out.println("_____________________________");
		System.out.println("mapTool=" + mapTool);
		for (Map.Entry<Tool, Integer> entry : mapTool.entrySet()) {
			System.out.println("entry=" + entry);
		}

			
		List<Integer> listToolScore = new ArrayList<Integer>();
		Concept conceptTMP = null;
		double conceptScore = 0;
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		// We build the Concept Map
		for (Map.Entry<Tool, Integer> entry : mapTool.entrySet()) {
			//TODO : NullPointerException
			System.out.println("++++++++++++++++++");
			System.out.println("entry="+entry);
			System.out.println("entry.getKey()="+entry.getKey());//devient NULL
			System.out.println("entry.getKey().getConcept_id()="+entry.getKey().getConcept_id());
			Concept concept = conceptDao.getById(entry
					.getKey().getConcept_id());
			
			
			if(!mapConcept.isEmpty()){
				
				//We calculate the Concept Score
				conceptScore = ScoreManage.ConceptScore(listToolScore, toolDao.selectAllByConceptId(conceptTMP.getId()).size());
				
				Map<Tool, Integer> mapTMP = mapConcept.get(conceptTMP);
				mapConcept.remove(conceptTMP);
				
				conceptTMP.setScore(conceptScore);
				mapConcept.put(conceptTMP, mapTMP);
				
				conceptTMP = concept;
			}else{
				conceptTMP = concept;
			}
			
			if (!mapConcept.containsKey(concept)) {
				
				listToolScore.clear();
				
				mapConcept.put(concept, new HashMap<Tool, Integer>());
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
				
			} else {
				
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
			}
			
			//We put in the list the Tool Score
			listToolScore.add(entry.getValue());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		//We calculate the score of the last concept if it's not null
		if(conceptTMP != null){
			conceptScore = ScoreManage.ConceptScore(listToolScore, toolDao.selectAllByConceptId(conceptTMP.getId()).size());
			
			Map<Tool, Integer> mapTMP = mapConcept.get(conceptTMP);
			mapConcept.remove(conceptTMP);
			
			conceptTMP.setScore(conceptScore);
			mapConcept.put(conceptTMP, mapTMP);
		}

		// We build the Category Map
		for (Map.Entry<Concept, Map> entry : mapConcept.entrySet()) {
			Category category = categoryDao.getById(entry
					.getKey().getCategory_id());

			if (!mapCategory.containsKey(category)) {
				mapCategory.put(category, new HashMap<Concept, Map>());
				mapCategory.get(category).put(entry.getKey(), entry.getValue());

			} else {
				mapCategory.containsKey(category);
				mapCategory.get(category).put(entry.getKey(), entry.getValue());
			}

		}

		return mapCategory;
	}

	/**
	 * Select all Tools
	 */
	public List<Tool> getAllTools() throws Exception {
		System.out.println("SkillService.getAllTools()");

		return toolDao.selectAll();
	}

	/**
	 * Get One VSkill By Tool Name
	 */
	public VSkill getSkillByTool(String toolName) throws Exception {
		System.out.println("SkillService.getSkillByTool() toolName=" + toolName);

		return vSkillDao.getSkillByTool(toolName);
	}

	/**
	 * Select all VSkill By Category_Name and Concept_Name
	 */
	public List<VSkill> getToolByConcept(String categoryName, String conceptName)
			throws Exception {
		System.out.println("SkillService.getToolByConcept() categoryName=" + categoryName + "conceptName=" +conceptName );

		return vSkillDao.getToolByConcept(categoryName, conceptName);
	}

	/**
	 * Select all VSkill By Category_Name
	 */
	public List<VSkill> getConceptByCategory(String categoryName)
			throws Exception {
		System.out.println("SkillService.getConceptByCategory() categoryName=" + categoryName);

		return vSkillDao.getConceptByCategory(categoryName);
	}

	/**
	 * Add One Skill
	 */
	@Override
	public void addOneSkill(Skill skill) throws Exception {

		this.skillDao.addOneSkill(skill);
	}
	
	/**
	 * Update One Skill
	 */
	@Override
	public void updateOneSkill(Skill skill) throws Exception {
		
		this.skillDao.updateOneSkill(skill);
	}
	
	/**
	 * Get One Tool By Name
	 */
	public Tool getToolByName(String name) throws Exception {

		return toolDao.getByName(name);
	}

	/**
	 * Set the skillDao value
	 * 
	 * @param skillDao
	 *            the skillDao to set
	 */
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
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
	 * Set the vconceptDao value
	 * 
	 * @param vconceptDao
	 *            the vconceptDao to set
	 */
	public void setVSkillDao(IVSkillDao vSkillDao) {
		this.vSkillDao = vSkillDao;
	}

	@Override
	public Skill getSkillByToolId(int collaboratorId, int toolId)
			throws Exception {

		return this.skillDao.getOneCollaboratorSkill(collaboratorId, toolId);
	}
	
	/**
	 * Set the vSkillCollabDao value
	 * @param vSkillCollabDao the vSkillCollabDao to set
	 */
	public void setvSkillCollabDao(IVSkillCollabDao vSkillCollabDao) {
		this.vSkillCollabDao = vSkillCollabDao;
	}

}
