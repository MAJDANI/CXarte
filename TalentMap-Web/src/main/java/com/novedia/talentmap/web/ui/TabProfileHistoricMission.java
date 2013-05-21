/**
 * 
 */
package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author v.dibi
 * 
 */
public class TabProfileHistoricMission extends VerticalLayout {
	private MissionCollaboratorContent missionCollabContent;

	public TabProfileHistoricMission(
			MissionCollaboratorContent missionCollabContent) {
		super();
		this.missionCollabContent = missionCollabContent;
		buildMain();
	}

	private void buildMain() {
		addComponent(this.missionCollabContent);
	}

	/**
	 * @return the missionCollabContent
	 */
	public MissionCollaboratorContent getMissionCollabContent() {
		return missionCollabContent;
	}

	/**
	 * @param missionCollabContent
	 *            the missionCollabContent to set
	 */
	public void setMissionCollabContent(
			MissionCollaboratorContent missionCollabContent) {
		this.missionCollabContent = missionCollabContent;
	}

}