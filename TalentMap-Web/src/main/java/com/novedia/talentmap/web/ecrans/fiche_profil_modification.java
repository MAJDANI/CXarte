package com.novedia.talentmap.web.ecrans;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class fiche_profil_modification extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Panel panel_2;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private Link autres_competence;
	@AutoGenerated
	private Accordion catgorie_1;
	@AutoGenerated
	private Label label_5;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private Label label_4;
	@AutoGenerated
	private Accordion accordion_1;
	@AutoGenerated
	private Label label_7;
	@AutoGenerated
	private Label label_6;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private Label label_1;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public fiche_profil_modification() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1, "top:181.0px;left:38.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("742px");
		panel_1.setHeight("309px");

		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panel_1.setContent(verticalLayout_1);

		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		verticalLayout_1.addComponent(horizontalLayout_2);

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		verticalLayout_1.addComponent(horizontalLayout_1);

		return verticalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(true);

		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Même chose que fiche profil création (pour les données administratves)");
		horizontalLayout_2.addComponent(label_1);

		return horizontalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);

		// panel_2
		panel_2 = buildPanel_2();
		horizontalLayout_1.addComponent(panel_2);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("738px");
		panel_2.setHeight("404px");

		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_2.setContent(verticalLayout_2);

		return panel_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(true);

		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Compétences (voir cahier d'Anna)");
		verticalLayout_2.addComponent(label_2);

		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		verticalLayout_2.addComponent(horizontalLayout_3);

		return verticalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("719px");
		horizontalLayout_3.setHeight("302px");
		horizontalLayout_3.setMargin(false);
		horizontalLayout_3.setSpacing(true);

		// catgorie_1
		catgorie_1 = buildCatgorie_1();
		horizontalLayout_3.addComponent(catgorie_1);

		// autres_competence
		autres_competence = new Link();
		autres_competence.setCaption("Autres compétences");
		autres_competence.setImmediate(false);
		autres_competence.setWidth("-1px");
		autres_competence.setHeight("-1px");
		horizontalLayout_3.addComponent(autres_competence);

		return horizontalLayout_3;
	}

	@AutoGenerated
	private Accordion buildCatgorie_1() {
		// common part: create layout
		catgorie_1 = new Accordion();
		catgorie_1.setImmediate(true);
		catgorie_1.setWidth("302px");
		catgorie_1.setHeight("-1px");

		// accordion_1
		accordion_1 = buildAccordion_1();
		catgorie_1.addTab(accordion_1, "Tab", null);

		// label_4
		label_4 = new Label();
		label_4.setImmediate(false);
		label_4.setWidth("-1px");
		label_4.setHeight("-1px");
		label_4.setValue("JAVA");
		catgorie_1.addTab(label_4, "Tab", null);

		// label_3
		label_3 = new Label();
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Label");
		catgorie_1.addTab(label_3, "Tab", null);

		// label_5
		label_5 = new Label();
		label_5.setImmediate(false);
		label_5.setWidth("-1px");
		label_5.setHeight("-1px");
		label_5.setValue("Label");
		catgorie_1.addTab(label_5, "Tab", null);

		return catgorie_1;
	}

	@AutoGenerated
	private Accordion buildAccordion_1() {
		// common part: create layout
		accordion_1 = new Accordion();
		accordion_1.setImmediate(true);
		accordion_1.setWidth("100.0%");
		accordion_1.setHeight("100.0%");

		// label_6
		label_6 = new Label();
		label_6.setImmediate(false);
		label_6.setWidth("-1px");
		label_6.setHeight("-1px");
		label_6.setValue("Label");
		accordion_1.addTab(label_6, "Tab", null);

		// label_7
		label_7 = new Label();
		label_7.setImmediate(false);
		label_7.setWidth("-1px");
		label_7.setHeight("-1px");
		label_7.setValue("Label");
		accordion_1.addTab(label_7, "Tab", null);

		return accordion_1;
	}

}
