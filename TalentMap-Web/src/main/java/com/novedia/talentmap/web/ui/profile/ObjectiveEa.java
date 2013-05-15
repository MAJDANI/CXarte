package com.novedia.talentmap.web.ui.profile;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ObjectiveEa extends VerticalLayout {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1758249567036725375L;

    public ObjectiveEa() {

	init();
    }

    public void init() {
	Label label = new Label(" Page en cours de construction");
	label.setVisible(true);
	label.addStyleName("mystyle");
	addComponent(label);
    }
}