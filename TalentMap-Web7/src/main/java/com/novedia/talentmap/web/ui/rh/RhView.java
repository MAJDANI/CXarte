package com.novedia.talentmap.web.ui.rh;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RhView extends VerticalLayout{
	
	
	
	
	public RhView(){
		super();
	}
	
	
	public VerticalLayout buildRhContent(){
		
		addComponent(new Label("RHHHHHHHH"));
		return this;
	}

}
