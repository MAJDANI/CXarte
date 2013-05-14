package com.novedia.talentmap.web.ui.formFactory;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class EditMissionFormFieldFactory implements FormFieldFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8213744445391942619L;

	private Mission mission;

	public EditMissionFormFieldFactory(Mission mission) {
		this.mission = mission;
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {

		for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_MISSION.length; i++) {

			if (propertyId.equals(ConstantsEnglish.FIELD_ORDER_MISSION[i])) {

				if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_NAME)
						|| propertyId
								.equals(ConstantsEnglish.FIELD_MISSION_PLACE)
						|| propertyId
								.equals(ConstantsEnglish.FIELD_MISSION_CLIENT)) {

					TextField field = new TextField(
							(String) ConstantsEnglish.NAME_FIELD_MISSION[i]
									+ " : ");

					return field;
				}

				if (propertyId
						.equals(ConstantsEnglish.FIELD_MISSION_START_DATE)
						|| propertyId
								.equals(ConstantsEnglish.FIELD_MISSION_END_DATE)) {

					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield
							.setCaption((String) ConstantsEnglish.NAME_FIELD_MISSION[i]
									+ " : ");
					if (propertyId
							.equals(ConstantsEnglish.FIELD_MISSION_START_DATE)) {
						datefield.setStyleName("mission-start-date");
					} else if (propertyId
							.equals(ConstantsEnglish.FIELD_MISSION_END_DATE)) {
						datefield.setStyleName("mission-end-date");
					}

					return datefield;

				}

				if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_NOTES)) {

					TextArea textarea = new TextArea(
							(String) ConstantsEnglish.NAME_FIELD_MISSION[i]
									+ " : ");
					textarea.setNullRepresentation("");
					textarea.setColumns(20);
					textarea.setRows(5);

					return textarea;

				}
				if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_TOOLS)) {

					List<Tool> allTools = this.mission.getTools();

					int j = 0;

					Table table = new Table("Tools :");
					table.addContainerProperty("Tool N°", Integer.class, null);
					table.addContainerProperty("Tool Name", String.class, null);
					table.setSizeFull();
					table.setSelectable(true);
					table.setMultiSelect(true);
					table.setImmediate(true);

					if (allTools != null) {
						for (Tool tool : allTools) {
							table.addItem(
									new Object[] { j + 1, tool.getName() },
									new Integer(j));
							j++;
						}
					}

					return table;

				}

			}

		}

		return null;
	}

}
