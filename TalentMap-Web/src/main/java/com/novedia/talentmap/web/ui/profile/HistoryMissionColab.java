package com.novedia.talentmap.web.ui.profile;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HistoryMissionColab extends VerticalLayout{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2244129817867831057L;

public HistoryMissionColab(){
		
		init(); 
	}
	
	public void init() {
		Label label =  new Label(" Page en cours de construction");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);
	
	}	

}
