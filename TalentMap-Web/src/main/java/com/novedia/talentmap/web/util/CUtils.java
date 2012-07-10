package com.novedia.talentmap.web.util;

import java.util.Map;
import java.util.Vector;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class CUtils {
	
	
	public static Accordion MapSkillToAccordionSkill(Map<Category, Map> mapSkill, Object _this){
		
		// We organize the skills in tabs and tables: Categories tabs, Concepts
				// tabs and Tools tables
				
				//We build a new accordion Category
				Accordion accCategory = new Accordion();
				
				for (Map.Entry<Category, Map> eCategory : mapSkill.entrySet()) {
					VerticalLayout vLayoutCategory = new VerticalLayout();
					vLayoutCategory.setMargin(true);
					
					//We build a new accordion Concept
					Accordion accConcept = new Accordion();
					
					Map<Concept, Map> mapConcept = eCategory.getValue();

					for (Map.Entry<Concept, Map> eConcept : mapConcept.entrySet()) {
						VerticalLayout vLayoutConcept = new VerticalLayout();
						vLayoutConcept.setMargin(true);
						
						//We build a new table Tool
						Table tableTools = new Table();
						tableTools.setSelectable(true);
						tableTools.setNullSelectionAllowed(true);
						tableTools.setStyleName(TalentMapCSS.TABLE_TOOL);
						
						if(_this != null){ tableTools.addListener((ItemClickListener) _this); }
					
						Map<Tool, Integer> mapTool = eConcept.getValue();

						int idTable = 1;
						VerticalLayout vLayoutTool = new VerticalLayout();
						
						tableTools.addContainerProperty("Nom de l'outil", String.class,
								null);
						tableTools.addContainerProperty("Note", Integer.class, null);

						for (Map.Entry<Tool, Integer> eTool : mapTool.entrySet()) {
							
							tableTools.addItem(new Object[] { eTool.getKey().getName(),
									eTool.getValue() }, new Integer(idTable));
							idTable++;
						}
						
						vLayoutTool.addComponent(tableTools);
						vLayoutConcept.addComponent(vLayoutTool);
					
						// Set Tool tables
						//setTableTools(tableTools);

						// Set Concept tabs Style
						accConcept.setStyleName(TalentMapCSS.TABLE_CONCEPT);

						accConcept.addTab(vLayoutConcept, eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore())
						.setCaption(eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore());
			
						vLayoutCategory.addComponent(accConcept);
					}
					
					// Set Categories tabs Style
					accCategory.setStyleName(TalentMapCSS.TABLE_CATEGORY);

					accCategory.addTab(vLayoutCategory, eCategory.getKey().getName())
					.setCaption(eCategory.getKey().getName());
				}
				
				return accCategory;
	}
	
	/**
	 * 
	 * @class CUtils.java
	 * @param fieldOrder
	 * @param order
	 */
	public static void setOrderForm(Vector<Object> fieldOrder, Object[] order) {
		
		fieldOrder.removeAllElements();
		
		for (Object field : order) {
			fieldOrder.add(field);
		}
	}
	
	
	/**
	 * MANAGE ERROR MESSAGE
	 */
	
	/**
	 * 
	 * @class CUtils.java
	 * @param mapNotification
	 */
	public static void showMessage(Map<String, Object> mapNotification, Window mainWindow){
		Message msg = new Message(mapNotification, mainWindow);
		msg.show();
	}
	
	/**
	 * 
	 * @class CUtils.java
	 * @param messageError
	 */
	public static void showMessage(String messageError, Window mainWindow){
		Message msg = new Message(messageError, mainWindow);
		msg.show();
	}
	
	/**
	 * 
	 * @class CUtils.java
	 * @param messageError
	 * @param typeError
	 */
	public static void showMessage(String messageError, int typeError, Window mainWindow){
		Message msg = new Message(messageError, typeError, mainWindow);
		msg.show();
	}
	
}
