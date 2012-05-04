package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.store.impl.CategoryDao;
import com.novedia.talentmap.store.impl.ConceptDao;
import com.novedia.talentmap.store.impl.SkillDao;
import com.novedia.talentmap.store.impl.ToolDao;

public class SkillService implements ISkillService{
	
	private SkillDao skillDao;
	private ToolDao toolDao;
	private ConceptDao conceptDao;
	private CategoryDao categoryDao;

	@Override
	public Map<Category, Map> getAllSkill(int collab_id) throws Exception {
		
		Map<String, List> skillMap = new HashMap<String, List>();
		
		List<Skill> listSkill = new ArrayList<Skill>();
		Map<Tool, Integer> mapTool = new HashMap<Tool, Integer>();
		Map<Concept, Map> mapConcept = new HashMap<Concept, Map>();
		Map<Category, Map> mapCategory = new HashMap<Category, Map>();
		
		listSkill = skillDao.getAllCollaboratorSkill(collab_id);
		
		for(Skill s : listSkill){
			Tool tool = toolDao.getById(Integer.parseInt(s.getTool_id()));
			mapTool.put(tool, s.getScore());
		}
		
		for(Map.Entry<Tool, Integer> entry : mapTool.entrySet()){
			Concept concept = conceptDao.getById(Integer.parseInt(entry.getKey().getConcept_id()));
			
			if(!mapConcept.containsKey(concept)){
				mapConcept.put(concept, new HashMap<Tool, Integer>());
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
				System.out.println("concept does not exist");
			
			}else{
				mapConcept.containsKey(concept);
				mapConcept.get(concept).put(entry.getKey(), entry.getValue());
				System.out.println("concept exists");
			}
		}
		
		for(Map.Entry<Concept, Map> entry : mapConcept.entrySet()){
			Category category = categoryDao.getById(Integer.parseInt(entry.getKey().getCategory_id()));
			
			if(!mapCategory.containsKey(category)){
				mapCategory.put(category, new HashMap<Concept, Map>());
				mapCategory.get(category).put(entry.getKey(), entry.getValue());
				System.out.println("category does not exist");
			
			}else{
				mapCategory.containsKey(category);
				mapCategory.get(category).put(entry.getKey(), entry.getValue());
				System.out.println("category exists");
			}
			
			
		}
		
		return mapCategory;
	}
	
	/**
	 * Set the skillDao value
	 * @param skillDao the skillDao to set
	 */
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	/**
	 * Set the toolDao value
	 * @param toolDao the toolDao to set
	 */
	public void setToolDao(ToolDao toolDao) {
		this.toolDao = toolDao;
	}

	/**
	 * Set the conceptDao value
	 * @param conceptDao the conceptDao to set
	 */
	public void setConceptDao(ConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}

	/**
	 * Set the categoryDao value
	 * @param categoryDao the categoryDao to set
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
