package com.novedia.talentmap.web.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.web.TalentMapApplication;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;


public abstract class CUtils {

	public static final String STRING_DATE_FORMAT = "dd/MM/yyyy";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			STRING_DATE_FORMAT);
	
    /**
     * 
     * @class CUtils.java
     * @param mapNotification
     */
    public static void showMessage(Map<String, Object> mapNotification) {
	Message msg = new Message(mapNotification);
	msg.show();
    }

    /**
     * Construire l'arbre des compétences affiché à l'utilisateur
     * @param treeSkills l'abre à construire
     * @param skillService service permettant de récuperer toutes les compétences
     * @return 
     */
    public static Tree buildTreeSkills(Tree treeSkills,List<VSkill> listVSkill){
		for (VSkill vSkill : listVSkill) {
		    
			Category categorie = Category.builder().id(vSkill.getCategoryId()).name(vSkill.getCategoryName()).build();
		    Concept concept = Concept.builder().id(vSkill.getConceptId()).name(vSkill.getConceptName()).build() ;
		    Tool tool = Tool.builder().id(vSkill.getToolId()).name(vSkill.getToolName()).build();
		    
		    // Ajout d'une nouvelle catégorie à l'arbre
		    if (!treeSkills.containsId(categorie)) {
				treeSkills.addItem(categorie);
				treeSkills.setItemCaption(categorie, categorie.getName());
		    }
		    
		    // Ajout d'un nouveau concept à l'arbre
		    if (concept.getId() != null && !treeSkills.containsId(concept)) {
				treeSkills.addItem(concept);
				treeSkills.setItemCaption(concept, concept.getName());
				treeSkills.setParent(concept, categorie);
		    }
		    
		    // Ajout de l'outil courant
		    if(tool.getId() != null){
		    	treeSkills.addItem(tool);
		    	treeSkills.setItemCaption(tool, tool.getName());
		    	treeSkills.setParent(tool, concept);
		    	treeSkills.setChildrenAllowed(tool, false);
		    }
		}
		return treeSkills;
	}
    
    
    /**
     * Construire l'arbre des compétences d'un collaborateur
     * @param categoryMapDto
     * @param treeSkills
     * @return Tree
     */
    public static Tree buildTreeSkillsColleague(CategoryMapDTO categoryMapDto, Tree treeSkills){
    	for (Map.Entry<Category, ConceptMapDTO> categoryMap : categoryMapDto.getMapCategory().entrySet()) {
    		Category category = categoryMap.getKey(); 
    		treeSkills.addItem(category);
    		treeSkills.setItemCaption(category, category.getName());
    		ConceptMapDTO conceptMapDto = categoryMap.getValue();
    		for (Map.Entry<Concept, ToolSkillMap> conceptMap : conceptMapDto.getMapConcept().entrySet()) {
    			Concept concept = conceptMap.getKey();
    			treeSkills.addItem(concept);
    			treeSkills.setItemCaption(concept, concept.getName());
    			treeSkills.setParent(concept, category);
    			
    			Map<Tool, Skill> mapTool = conceptMap.getValue().getMapTool();
    			for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
    				Tool tool = eTool.getKey();
    				treeSkills.addItem(tool);
        			treeSkills.setItemCaption(tool, tool.getName());
        			treeSkills.setParent(tool, concept);
        			treeSkills.setChildrenAllowed(tool, false);
    			}
    		}
    	}
    	
    	return treeSkills;
    }

    
    /**
     * Construire un accordion contenant les compétences d'un collaborateur
     * @param categoryMapDto
     * @return
     */
    public static Accordion buildAccordionOfSkill(CategoryMapDTO categoryMapDto, Accordion accordionSkill){
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
    	RatingStars rateConcept;
    	
		for (Map.Entry<Category, ConceptMapDTO> category : categoryMapDto.getMapCategory().entrySet()) {
			VerticalLayout vLayoutCategory = new VerticalLayout();
			vLayoutCategory.setMargin(true);
			// We build a new accordion Concept
			Accordion accConcept = new Accordion();
			
			ConceptMapDTO conceptMapDto = category.getValue();
			for (Map.Entry<Concept, ToolSkillMap> concept : conceptMapDto.getMapConcept().entrySet()) {
				VerticalLayout vLayoutConcept = new VerticalLayout();
				HorizontalLayout hLayoutConcept = new HorizontalLayout(); 
				hLayoutConcept.setSpacing(true);
				
				vLayoutConcept.setMargin(true);
				rateConcept = new RatingStars();
				VerticalLayout vLayoutTool = new VerticalLayout();
				
				Table tableTools = new Table();
				Map<Tool, Skill> mapTool = concept.getValue().getMapTool();
				tableTools.addStyleName("table");
				tableTools.addContainerProperty(resourceBundle.getString("tool.name.caption"), String.class, null);
				tableTools.addContainerProperty(resourceBundle.getString("tool.level"), Integer.class, null);
				tableTools.setPageLength(mapTool.size());
				
				for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
					tableTools.addItem(new Object[] { eTool.getKey().getName(), 
							eTool.getValue().getAverageScore()}, eTool.getKey());
				}
				vLayoutTool.addComponent(tableTools);
				
				int noteconcept = (int) Math.round(concept.getKey().getScore());
				if (noteconcept != 0) {
					rateConcept.setMaxValue(noteconcept);
					rateConcept.setReadOnly(true);
					hLayoutConcept.addComponent(new Label(resourceBundle.getString("concept.level.caption") + " : "));
					hLayoutConcept.addComponent(rateConcept);
					
				} else {
					hLayoutConcept.addComponent(new Label(resourceBundle.getString("concept.level.low.msg")));
				}
				
				vLayoutConcept.addComponent(hLayoutConcept);
				vLayoutConcept.addComponent(vLayoutTool);
				
				accConcept.addTab(vLayoutConcept, concept.getKey().getName());
				vLayoutCategory.addComponent(accConcept);
			}
			accordionSkill.addTab(vLayoutCategory, category.getKey().getName())
			.setCaption(category.getKey().getName());
			
		}
		
    	return accordionSkill;
    	
    }
    
    
    
    /**
	 * Methode de décoration d'un bouton.
	 * Ajoute le focus sur un boutton et enlève le focus sur les autres  
	 * @param button
	 */
	public static void decorateButton(Button focusButton, Button... unfocusButtons){
		focusButton.addStyleName("focus");
		for (Button button : unfocusButtons) {
			button.removeStyleName("focus");
		}
	}
	
	/**
	 * Methode de transformation d'un bouton lien. 
	 * @param unfocusButtons
	 */
	public static void decorateButtonAsLink(Button... unfocusButtons){
		for (Button button : unfocusButtons) {
			button.addStyleName(Reindeer.BUTTON_LINK);
		}
	}
	

}
