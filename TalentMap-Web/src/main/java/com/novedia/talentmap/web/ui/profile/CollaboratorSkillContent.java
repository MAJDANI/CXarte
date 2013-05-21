package com.novedia.talentmap.web.ui.profile;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CollaboratorSkillContent extends VerticalLayout {

	public CollaboratorSkillContent() {

		init();
	}

	public void init() {
		Label label = new Label(" Page en cours de construction");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);

	}

}
