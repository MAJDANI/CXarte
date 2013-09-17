package com.novedia.talentmap.web.ui.search;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.vaadin.ui.Tree;

public class SearchPopInTest {

	private SearchPopIn searchPopIn;
	
	private Tree treeSkills;
	
	private Category categorie;
	
	private Concept concept;
	
	private Tool tool1;
	
	private Tool tool2;
	
	
	@Before
	public void setUp(){
		searchPopIn = new SearchPopIn();
	
		categorie = Category.builder().id(1).name("Java").build();
		concept = Concept.builder().id(1).name("ORM").build();
		tool1 = Tool.builder().id(1).name("Hibernate").build();
		tool2 = Tool.builder().id(2).name("Ibatis").build();		
		
		// Build a multi selectable tree
		treeSkills = new Tree();
		treeSkills.setMultiSelect(true);
		
		treeSkills.addItem(categorie);
		treeSkills.addItem(concept);
		treeSkills.setParent(concept, categorie);
		
		treeSkills.addItem(tool1);
		treeSkills.setParent(tool1, concept);
		treeSkills.setChildrenAllowed(tool1, false);
		
		treeSkills.addItem(tool2);
		treeSkills.setParent(tool2, concept);
		treeSkills.setChildrenAllowed(tool2, false);
	}
	
	
	@Test
	public void buildToolsListIdWiyhAllSelectedToolsOfTree() {
		
		//GIVEN
		treeSkills.select(tool1);
		treeSkills.select(tool2);
		
		// Get the tool id list 
		Collection<?> itemIds = treeSkills.getItemIds();
		Set<Integer> expectedList = new HashSet<Integer>();
		for (Object itemId : itemIds) {
			if(itemId instanceof Tool){
				if(treeSkills.isSelected(itemId)){
					Tool currentTool = (Tool)itemId;
					expectedList.add(currentTool.getId());
				}
			}
		}
		
		// WHEN		
		Set<Integer> result = searchPopIn.buildToolListOfTreeSkills(treeSkills);
		
		// THEN
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(expectedList.size(), result.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void buildToolsListIdWithAllToolsOfSelectedConcept() throws Exception {
		
		//GIVEN
		treeSkills.select(concept);
		treeSkills.select(tool1);
		treeSkills.select(tool2);
		// Get the tool id list 
		Collection<?> itemIds = treeSkills.getItemIds();
		Set<Tool> expectedList = new HashSet<Tool>();
		for (Object itemId : itemIds) {
			if(itemId instanceof Concept){
				if(treeSkills.isSelected(itemId)){
					Collection<Tool> childs =  (Collection<Tool>) treeSkills.getChildren(itemId);
					expectedList.addAll(childs);
				} 
				else if (itemId instanceof Tool) {
					if(treeSkills.isSelected(itemId)){
						expectedList.add((Tool) itemId);
					}
				}
			}
		}
		
		// WHEN		
		Set<Integer> result = searchPopIn.buildToolListOfTreeSkills(treeSkills);
		
		// THEN
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(expectedList.size(), result.size());
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void buildToolsListIdWithAllToolsOfSelectedCategory() throws Exception {
		//GIVEN
		treeSkills.select(categorie);
		treeSkills.select(concept);
		treeSkills.select(tool1);
		treeSkills.select(tool2);
		// Get the tool id list 
		Collection<?> itemIds = treeSkills.getItemIds();
		Set<Tool> expectedList = new HashSet<Tool>();
		for (Object itemId : itemIds) {
			if (itemId instanceof Category) {
				if(treeSkills.isSelected(itemId)){
					Collection<Concept> conceptchildrens =  (Collection<Concept>) treeSkills.getChildren(itemId);
					for (Concept concept : conceptchildrens) {
						Collection<Tool> toolChildrens =  (Collection<Tool>) treeSkills.getChildren(concept);
						expectedList.addAll(toolChildrens);
					}
				}
			}
			else if(itemId instanceof Concept){
				if(treeSkills.isSelected(itemId)){
					Collection<Tool> childs =  (Collection<Tool>) treeSkills.getChildren(itemId);
					expectedList.addAll(childs);
				} 
				
			} else if (itemId instanceof Tool) {
				if(treeSkills.isSelected(itemId)){
					expectedList.add((Tool) itemId);
				}
			}
		}
		
		// WHEN		
		Set<Integer> result = searchPopIn.buildToolListOfTreeSkills(treeSkills);
		
		// THEN
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(expectedList.size(), result.size());


	}
}
