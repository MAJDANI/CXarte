package com.novedia.talentmap.web.util;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.OrderedLayout;
import com.vaadin.ui.Window;

public class YesNoDialog extends Window implements Button.ClickListener {

	Callback callback;
	Button yes = new Button("Yes", this);
	Button no = new Button("No", this);

	public YesNoDialog(String caption, String question, Callback callback) {
		super(caption);

		setModal(true);

		this.callback = callback;

		if (question != null) {
			addComponent(new Label(question));
		}

		OrderedLayout hl = new OrderedLayout(
				OrderedLayout.ORIENTATION_HORIZONTAL);
		hl.addComponent(yes);
		hl.addComponent(no);
		addComponent(hl);
		((OrderedLayout) getLayout()).setComponentAlignment(hl,
				OrderedLayout.ALIGNMENT_RIGHT, OrderedLayout.ALIGNMENT_BOTTOM);
	}

	public void buttonClick(ClickEvent event) {
		if (getParent() != null) {
			((Window) getParent()).removeWindow(this);
		}
		callback.onDialogResult(event.getSource() == yes);
	}

	public interface Callback {

		public void onDialogResult(boolean resultIsYes);
	}
}
