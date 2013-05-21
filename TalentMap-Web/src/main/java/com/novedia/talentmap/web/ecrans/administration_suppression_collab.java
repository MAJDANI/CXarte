package com.novedia.talentmap.web.ecrans;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class administration_suppression_collab extends CustomComponent {

    @AutoGenerated
    private AbsoluteLayout mainLayout;
    @AutoGenerated
    private VerticalLayout verticalLayout_1;
    @AutoGenerated
    private HorizontalSplitPanel horizontalSplitPanel_2;
    @AutoGenerated
    private AbsoluteLayout absoluteLayout_2;
    @AutoGenerated
    private Panel panel_2;
    @AutoGenerated
    private VerticalLayout verticalLayout_5;
    @AutoGenerated
    private AbsoluteLayout absoluteLayout_3;
    @AutoGenerated
    private Label label_2;
    @AutoGenerated
    private TextField textField_1;
    @AutoGenerated
    private NativeButton nativeButton_1;
    @AutoGenerated
    private TextField textField_2;
    @AutoGenerated
    private Label label_3;
    @AutoGenerated
    private VerticalLayout verticalLayout_3;
    @AutoGenerated
    private Link link_6;
    @AutoGenerated
    private Link link_4;
    @AutoGenerated
    private Link link_5;
    @AutoGenerated
    private Link link_2;
    @AutoGenerated
    private Label label_1;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

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
    public administration_suppression_collab() {
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

	// verticalLayout_1
	verticalLayout_1 = buildVerticalLayout_1();
	mainLayout.addComponent(verticalLayout_1, "top:60.0px;left:60.0px;");

	return mainLayout;
    }

    @AutoGenerated
    private VerticalLayout buildVerticalLayout_1() {
	// common part: create layout
	verticalLayout_1 = new VerticalLayout();
	verticalLayout_1.setImmediate(false);
	verticalLayout_1.setWidth("800px");
	verticalLayout_1.setHeight("400px");
	verticalLayout_1.setMargin(true);
	verticalLayout_1.setSpacing(true);

	// label_1
	label_1 = new Label();
	label_1.setImmediate(false);
	label_1.setWidth("-1px");
	label_1.setHeight("-1px");
	label_1.setValue("TalentMap - Administration");
	verticalLayout_1.addComponent(label_1);

	// horizontalSplitPanel_2
	horizontalSplitPanel_2 = buildHorizontalSplitPanel_2();
	verticalLayout_1.addComponent(horizontalSplitPanel_2);
	verticalLayout_1.setExpandRatio(horizontalSplitPanel_2, 1.0f);

	return verticalLayout_1;
    }

    @AutoGenerated
    private HorizontalSplitPanel buildHorizontalSplitPanel_2() {
	// common part: create layout
	horizontalSplitPanel_2 = new HorizontalSplitPanel();
	horizontalSplitPanel_2.setImmediate(false);
	horizontalSplitPanel_2.setWidth("762px");
	horizontalSplitPanel_2.setHeight("337px");
	horizontalSplitPanel_2.setMargin(true);

	// verticalLayout_3
	verticalLayout_3 = buildVerticalLayout_3();
	horizontalSplitPanel_2.addComponent(verticalLayout_3);

	// absoluteLayout_2
	absoluteLayout_2 = buildAbsoluteLayout_2();
	horizontalSplitPanel_2.addComponent(absoluteLayout_2);

	return horizontalSplitPanel_2;
    }

    @AutoGenerated
    private VerticalLayout buildVerticalLayout_3() {
	// common part: create layout
	verticalLayout_3 = new VerticalLayout();
	verticalLayout_3.setImmediate(false);
	verticalLayout_3.setWidth("202px");
	verticalLayout_3.setHeight("137px");
	verticalLayout_3.setMargin(true);
	verticalLayout_3.setSpacing(true);

	// link_2
	link_2 = new Link();
	link_2.setCaption("Ajouter des compétences");
	link_2.setImmediate(false);
	link_2.setWidth("-1px");
	link_2.setHeight("-1px");
	verticalLayout_3.addComponent(link_2);

	// link_5
	link_5 = new Link();
	link_5.setCaption("Supprimer des compétences");
	link_5.setImmediate(false);
	link_5.setWidth("-1px");
	link_5.setHeight("-1px");
	verticalLayout_3.addComponent(link_5);

	// link_4
	link_4 = new Link();
	link_4.setCaption("Modifier des compétences");
	link_4.setImmediate(false);
	link_4.setWidth("-1px");
	link_4.setHeight("-1px");
	verticalLayout_3.addComponent(link_4);

	// link_6
	link_6 = new Link();
	link_6.setCaption("Delete collaborator");
	link_6.setImmediate(false);
	link_6.setWidth("-1px");
	link_6.setHeight("-1px");
	verticalLayout_3.addComponent(link_6);

	return verticalLayout_3;
    }

    @AutoGenerated
    private AbsoluteLayout buildAbsoluteLayout_2() {
	// common part: create layout
	absoluteLayout_2 = new AbsoluteLayout();
	absoluteLayout_2.setImmediate(false);
	absoluteLayout_2.setWidth("484px");
	absoluteLayout_2.setHeight("337px");
	absoluteLayout_2.setMargin(false);

	// panel_2
	panel_2 = buildPanel_2();
	absoluteLayout_2.addComponent(panel_2, "top:-7.0px;left:0.0px;");

	return absoluteLayout_2;
    }

    @AutoGenerated
    private Panel buildPanel_2() {
	// common part: create layout
	panel_2 = new Panel();
	panel_2.setImmediate(false);
	panel_2.setWidth("504px");
	panel_2.setHeight("364px");

	// verticalLayout_5
	verticalLayout_5 = buildVerticalLayout_5();
	panel_2.setContent(verticalLayout_5);

	return panel_2;
    }

    @AutoGenerated
    private VerticalLayout buildVerticalLayout_5() {
	// common part: create layout
	verticalLayout_5 = new VerticalLayout();
	verticalLayout_5.setImmediate(false);
	verticalLayout_5.setWidth("115.19%");
	verticalLayout_5.setHeight("105.87%");
	verticalLayout_5.setMargin(false);

	// absoluteLayout_3
	absoluteLayout_3 = buildAbsoluteLayout_3();
	verticalLayout_5.addComponent(absoluteLayout_3);

	return verticalLayout_5;
    }

    @AutoGenerated
    private AbsoluteLayout buildAbsoluteLayout_3() {
	// common part: create layout
	absoluteLayout_3 = new AbsoluteLayout();
	absoluteLayout_3.setImmediate(false);
	absoluteLayout_3.setWidth("503px");
	absoluteLayout_3.setHeight("343px");
	absoluteLayout_3.setMargin(false);

	// label_3
	label_3 = new Label();
	label_3.setImmediate(false);
	label_3.setWidth("-1px");
	label_3.setHeight("-1px");
	label_3.setValue("Nom : ");
	absoluteLayout_3.addComponent(label_3, "top:24.0px;left:24.0px;");

	// textField_2
	textField_2 = new TextField();
	textField_2.setImmediate(false);
	textField_2.setWidth("-1px");
	textField_2.setHeight("-1px");
	textField_2.setSecret(false);
	absoluteLayout_3.addComponent(textField_2, "top:23.0px;left:79.0px;");

	// nativeButton_1
	nativeButton_1 = new NativeButton();
	nativeButton_1.setCaption("Supprimer");
	nativeButton_1.setImmediate(true);
	nativeButton_1.setWidth("-1px");
	nativeButton_1.setHeight("-1px");
	absoluteLayout_3.addComponent(nativeButton_1,
		"top:105.0px;left:79.0px;");

	// textField_1
	textField_1 = new TextField();
	textField_1.setImmediate(false);
	textField_1.setWidth("-1px");
	textField_1.setHeight("-1px");
	textField_1.setSecret(false);
	absoluteLayout_3.addComponent(textField_1, "top:67.0px;left:79.0px;");

	// label_2
	label_2 = new Label();
	label_2.setImmediate(false);
	label_2.setWidth("-1px");
	label_2.setHeight("-1px");
	label_2.setValue("Prénom : ");
	absoluteLayout_3.addComponent(label_2, "top:67.0px;left:24.0px;");

	return absoluteLayout_3;
    }

}
