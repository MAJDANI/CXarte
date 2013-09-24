package com.novedia.talentmap.web.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.Reindeer;


public abstract class CUtils {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
		    "dd/MM/yyyy");
	
	/**
     * MANAGE ERROR MESSAGE
     */

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
    public static void buildTreeSkills(Tree treeSkills,ISkillService skillService){
		List<VSkill> listVSkill = skillService.getAllVSkillOrdered();
		
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
