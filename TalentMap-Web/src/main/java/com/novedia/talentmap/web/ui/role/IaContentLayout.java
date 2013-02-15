package com.novedia.talentmap.web.ui.role;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class IaContentLayout extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IaContentLayout(){
			
			init(); 
		}
		
		public void init() {
			Label label =  new Label(" Page ingénieur d'affaires( commercial) en cours de construction ");
			label.setVisible(true);
			label.addStyleName("mystyle");
			addComponent(label);	
		}	

}
