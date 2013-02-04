package com.novedia.talentmap.web.ui.ea;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author v.dibi
 *
 */
public class EaContentHistorique extends VerticalLayout{

	/**
	 * @author v.dibi
	 */
	private static final long serialVersionUID = -276088438952391116L;
	
	public EaContentHistorique(){
		
		init(); 
	}
	
	public void init() {
		Label label =  new Label(" Page en cours de construction ");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);
	
	}	
}