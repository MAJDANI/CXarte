package com.novedia.talentmap.web.ui;


import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ListSkill extends Accordion {
	
	public ListSkill(){
		HorizontalLayout hLayout = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();
		
		
		hLayout.addComponent(new Label("Test 1 "));
		hLayout2.addComponent(new Label("Test 2 "));
		
		addTab(hLayout,"TAB1");
		addTab(hLayout2, "TAB2");
	}
}
