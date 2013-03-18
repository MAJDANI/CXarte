package com.novedia.talentmap.web.ui.role;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RhContentLayout extends VerticalLayout {
	
	

	/**
	 * Default constructor
	 */
	public RhContentLayout(){
		super();
	}
	
	public RhContentLayout init() {
		Label label =  new Label(" Page resource humaine en cours de construction ");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);	
		return this;
	}	
	
}