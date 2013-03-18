package com.novedia.talentmap.web.ui.role;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmContentLayout extends VerticalLayout{
	

	/**
	 * Default constructor
	 */
	public CmContentLayout(){
			
	}
		
	public CmContentLayout init() {
		Label label =  new Label(" Page consultant manager en cours de construction ");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);	
		return this;
	}	

}
