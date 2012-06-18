package com.novedia.talentmap.services.impl;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.impl.CategoryDao;
import com.novedia.talentmap.store.impl.ConceptDao;
import com.novedia.talentmap.store.impl.SkillDao;
import com.novedia.talentmap.store.impl.ToolDao;

public class SkillServiceTest {
	
	private SkillService skillService;
	
	@Before
	public void setUp() throws Exception {
		this.skillService = new SkillService();
		this.skillService.setSkillDao(new SkillDao());
		this.skillService.setCategoryDao(new CategoryDao());
		this.skillService.setConceptDao(new ConceptDao());
		this.skillService.setToolDao(new ToolDao());
	}

	@Test
	public void testGetAllCollaboratorSkill() throws Exception {
		
		assertNotNull(this.skillService.getAllCollaboratorSkill(2));
		
		//showAllSkills();
		
	}
	
	public void showAllSkills() throws Exception{
		
		Map<Category, Map> mapSkill = this.skillService.getAllCollaboratorSkill(2);
		
		for(Map.Entry<Category, Map> eCategory : mapSkill.entrySet()){
			
			System.out.println("Categorie : "+ eCategory.getKey().getName());
			Map<Concept, Map> conceptMap = eCategory.getValue();
			
			for(Map.Entry<Concept, Map> eConcept : conceptMap.entrySet()){
				
				System.out.println("Concept : " + eConcept.getKey().getName() + "| Score : " + eConcept.getKey().getScore());
				Map<Tool, Integer> toolMap = eConcept.getValue();
				
				for(Map.Entry<Tool, Integer> eTool : toolMap.entrySet()){
					
					System.out.println("Tool : " + eTool.getKey().getName() + " | Score : " + eTool.getValue());
				}
			}
		}
	}

}
