package com.novedia.talentmap.web.ui;


import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ListSkill extends Accordion {
	
	private ISkillService skillService;

	public ListSkill(ISkillService skillService) throws Exception{
		this.skillService = skillService;
		
		Map<Category, Map> mapSkill = skillService.getAllSkill(1);
		
		for(Map.Entry<Category, Map> eCategory : mapSkill.entrySet()){
			VerticalLayout vLayout = new VerticalLayout();
			vLayout.setMargin(true);
			
			Map<Concept, Map> mapConcept = eCategory.getValue();
			
			Accordion accConcept = new Accordion();
			
			for(Map.Entry<Concept, Map> eConcept : mapConcept.entrySet()){
				
				VerticalLayout vLayout2 = new VerticalLayout();
				vLayout2.setMargin(true);
				
				Map<Tool, Integer> mapTool = eConcept.getValue();
				
				int idTable = 1;
				Table tableTool = new Table();
				tableTool.addContainerProperty("Nom de l'outil", String.class,  null);
				tableTool.addContainerProperty("Note", Integer.class, null);
				
				
				for(Map.Entry<Tool, Integer> eTool : mapTool.entrySet()){
					VerticalLayout vLayout3 = new VerticalLayout();
					
					tableTool.addItem(new Object[] {eTool.getKey().getName(),eTool.getValue()}, new Integer(idTable));
					idTable++;
					
					vLayout3.addComponent(tableTool);
					vLayout2.addComponent(vLayout3);
				}
				
				tableTool.setHeight(idTable*21);
				tableTool.setWidth(170);
				
				accConcept.addTab(vLayout2,eConcept.getKey().getName());
				vLayout.addComponent(accConcept);
			}
			
			addTab(vLayout, eCategory.getKey().getName());
		}
		
	}
	
	/**
	 * Set the skillService value
	 * @param skillService the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}
}
