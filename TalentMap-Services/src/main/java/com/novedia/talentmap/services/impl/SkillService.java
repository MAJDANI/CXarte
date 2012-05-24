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
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.store.ICategoryDao;
import com.novedia.talentmap.store.IConceptDao;
import com.novedia.talentmap.store.ISkillDao;
import com.novedia.talentmap.store.IToolDao;
import com.novedia.talentmap.store.IVSkillDao;

/**
 * Skill Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class SkillService implements ISkillService{
	
	private ISkillDao skillDao;
	private IToolDao toolDao;
	private IConceptDao conceptDao;
	private ICategoryDao categoryDao;
	private IVSkillDao vSkillDao;

	/**
	 * We take all Collaborator Skills By ID
	 */
	@Override
	public Map<Category, Map> getAllCollaboratorSkill(int collab_id) throws Exception {
		
		Map<String, List> skillMap = new HashMap<String, List>();
		
		List<Skill> listSkill = new ArrayList<Skill>();
		
		//A Tool Map (Tool Object, Integer:Score)
		Map<Tool, Integer> mapTool = new HashMap<Tool, Integer>();
		
		//A Concept Map (Concept, Tool Map which matches with the Concept)
		Map<Concept, Map> mapConcept = new HashMap<Concept, Map>();
		
		//A Category Map (Category, Concept Map which matches with the Category)
		Map<Category, Map> mapCategory = new HashMap<Category, Map>();
		
		
		//We take all Collaborators Skills
		listSkill = skillDao.getAllCollaboratorSkill(collab_id);
		
		
		//We build the Tool Map
		for(Skill s : listSkill){
			Tool tool = toolDao.getById(Integer.parseInt(s.getTool_id()));
			mapTool.put(tool, s.getScore());
		}
		
		//We build the Concept Map
		for(Map.Entry<Tool, Integer> entry : mapTool.entrySet()){
			Concept concept = conceptDao.getById(Integer.parseInt(entry.getKey().getConcept_id()));
			
			if(!mapConcept.containsKey(concept)){
				mapConcept.put(concept, new HashMap<Tool, Integer>());
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
			
			}else{
				mapConcept.containsKey(concept);
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
			}
		}
		
		//We build the Category Map
		for(Map.Entry<Concept, Map> entry : mapConcept.entrySet()){
			Category category = categoryDao.getById(Integer.parseInt(entry.getKey().getCategory_id()));
			
			if(!mapCategory.containsKey(category)){
				mapCategory.put(category, new HashMap<Concept, Map>());
				mapCategory.get(category).put(entry.getKey(), entry.getValue());
			
			}else{
				mapCategory.containsKey(category);
				mapCategory.get(category).put(entry.getKey(), entry.getValue());
			}
			
		}
		
		return mapCategory;
	}
	
	/**
	 * Select all Tools
	 */
	public List<Tool> getAllTools() throws Exception{
		
		return toolDao.selectAll();
	}
	
	/**
	 * Get One VSkill By Tool Name
	 */
	public VSkill getSkillByTool(String toolName) throws Exception{
		
		return vSkillDao.getSkillByTool(toolName);
	}
	
	/**
	 * Select all VSkill By Category_Name and Concept_Name
	 */
	public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws Exception{
		
		return vSkillDao.getToolByConcept(categoryName, conceptName);
	}
	
	/**
	 * Select all VSkill By Category_Name
	 */
	public List<VSkill> getConceptByCategory(String categoryName) throws Exception{
		
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
	 * Get One Tool By Name
	 */
	public Tool getToolByName(String name) throws Exception{
		
		return toolDao.getByName(name);
	}
	/**
	 * Set the skillDao value
	 * @param skillDao the skillDao to set
	 */
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
	}

	/**
	 * Set the toolDao value
	 * @param toolDao the toolDao to set
	 */
	public void setToolDao(IToolDao toolDao) {
		this.toolDao = toolDao;
	}

	/**
	 * Set the conceptDao value
	 * @param conceptDao the conceptDao to set
	 */
	public void setConceptDao(IConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}

	/**
	 * Set the categoryDao value
	 * @param categoryDao the categoryDao to set
	 */
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * Set the vconceptDao value
	 * @param vconceptDao the vconceptDao to set
	 */
	public void setVSkillDao(IVSkillDao vSkillDao) {
		this.vSkillDao = vSkillDao;
	}

	@Override
	public Skill getSkillByToolId(int collaboratorId, int toolId) throws Exception {
		
		return this.skillDao.getOneCollaboratorSkill(collaboratorId, toolId);
	}

	
	
}
