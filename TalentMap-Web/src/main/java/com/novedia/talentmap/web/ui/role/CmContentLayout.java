package com.novedia.talentmap.web.ui.role;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CmContentLayout extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CmContentLayout(){
			
			init(); 
		}
		
		public void init() {
			Label label =  new Label(" Page consultant manager en cours de construction ");
			label.setVisible(true);
			label.addStyleName("mystyle");
			addComponent(label);	
		}	

}
