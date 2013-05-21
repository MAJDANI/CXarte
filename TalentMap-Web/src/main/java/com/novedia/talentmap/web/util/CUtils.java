package com.novedia.talentmap.web.util;

import java.util.Map;
import java.util.Vector;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class CUtils {

    public static Accordion MapSkillToAccordionSkill(
	    CategoryMapDTO categoryMapDto, Object _this) {

	// We build a new accordion Category
	Accordion accCategory = new Accordion();
	final String LABEL_NOTE_CONCEPT = "Level : ";
	final String FAIL_LABEL_NOTE_CONCEPT = "improve your skill";
	RatingStars rateConcept;
	for (Map.Entry<Category, ConceptMapDTO> category : categoryMapDto
		.getMapCategory().entrySet()) {
	    VerticalLayout vLayoutCategory = new VerticalLayout();
	    vLayoutCategory.setMargin(true);

	    // We build a new accordion Concept
	    Accordion accConcept = new Accordion();

	    ConceptMapDTO conceptMapDto = category.getValue();

	    for (Map.Entry<Concept, ToolSkillMap> concept : conceptMapDto
		    .getMapConcept().entrySet()) {
		VerticalLayout vLayoutConcept = new VerticalLayout();
		HorizontalLayout hLayoutConcept = new HorizontalLayout(); // layout
		// de
		// la
		// note
		// du
		// concept
		hLayoutConcept.setSpacing(true);

		vLayoutConcept.setMargin(true);
		rateConcept = new RatingStars();

		// We build a new table Tool
		Table tableTools = new Table();
		tableTools.setSelectable(true);
		tableTools.setNullSelectionAllowed(true);
		tableTools.setStyleName(TalentMapCSS.TABLE_TOOL);

		if (_this != null) {
		    tableTools.addListener((ItemClickListener) _this);
		}

		Map<Tool, Skill> mapTool = concept.getValue().getMapTool();

		int idTable = 1;
		VerticalLayout vLayoutTool = new VerticalLayout();

		tableTools
			.addContainerProperty("Tool Name", String.class, null);
		tableTools.addContainerProperty("Note", Integer.class, null);
		tableTools.addContainerProperty("score", Integer.class, null);
		tableTools.addContainerProperty("use_frequency", Integer.class,
			null);
		tableTools.addContainerProperty("no_using_time", Integer.class,
			null);

		for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
		    tableTools.addItem(new Object[] { eTool.getKey().getName(),
			    eTool.getValue().getAverageScore(),
			    eTool.getValue().getScore(),
			    eTool.getValue().getUse_frequency(),
			    eTool.getValue().getNo_using_time() }, new Integer(
			    idTable));
		    idTable++;
		}

		tableTools
			.setVisibleColumns(new Object[] { "Tool Name", "Note" });

		vLayoutTool.addComponent(tableTools);

		int noteconcept = (int) Math.round(concept.getKey().getScore());
		if (noteconcept != 0) {
		    rateConcept.setMaxValue(noteconcept);
		    rateConcept.setValue(noteconcept);
		    rateConcept.setReadOnly(true);
		    hLayoutConcept.addComponent(new Label(LABEL_NOTE_CONCEPT));
		    hLayoutConcept.addComponent(rateConcept);

		} else {
		    hLayoutConcept.addComponent(new Label(
			    FAIL_LABEL_NOTE_CONCEPT));
		}

		vLayoutConcept.addComponent(hLayoutConcept);
		vLayoutConcept.addComponent(vLayoutTool);

		// Set Concept tabs Style
		accConcept.setStyleName(TalentMapCSS.TABLE_CONCEPT);
		accConcept.addTab(vLayoutConcept, concept.getKey().getName());

		vLayoutCategory.addComponent(accConcept);
	    }

	    // Set Categories tabs Style
	    accCategory.setStyleName(TalentMapCSS.TABLE_CATEGORY);

	    accCategory.addTab(vLayoutCategory, category.getKey().getName())
		    .setCaption(category.getKey().getName());
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
    public static void showMessage(Map<String, Object> mapNotification,
	    Window mainWindow) {
	Message msg = new Message(mapNotification, mainWindow);
	msg.show();
    }

    /**
     * 
     * @class CUtils.java
     * @param messageError
     */
    public static void showMessage(String messageError, Window mainWindow) {
	Message msg = new Message(messageError, mainWindow);
	msg.show();
    }

    /**
     * 
     * @class CUtils.java
     * @param messageError
     * @param typeError
     */
    public static void showMessage(String messageError, int typeError,
	    Window mainWindow) {
	Message msg = new Message(messageError, typeError, mainWindow);
	msg.show();
    }

    /**
     * Method used to encode password
     */
    public static String encodePassword(String password) {
	Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
	String encodedPassword = md5Encoder.encodePassword(password, null);

	return encodedPassword;
    }

}
