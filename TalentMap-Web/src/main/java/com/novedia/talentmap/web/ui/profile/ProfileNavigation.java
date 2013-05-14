package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.ui.profile.skill.SkillCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileLayout;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProfileNavigation extends VerticalLayout implements IObservable,
		ItemClickListener {

	/**
	 * Utils Observateur
	 */
	private IProfileLayout obs;

	/**
	 * POJO
	 */
	private Class<?> cl = ProfileCollaboratorContent.class;

	private Integer roleId;

	/**
	 * Navigation tree
	 */
	private Tree tree;

	/**
	 * Default constructor
	 */
	public ProfileNavigation() {
		super();
	}

	/**
	 * The main builder
	 * 
	 * @class MonitoringCollabNavigation.java
	 */
	public VerticalLayout mainBuild() {
		removeAllComponents();
		setMargin(true);
		setSpacing(true);
		playTree();
		return this;
	}

	/**
	 * allowed unfolding the tree
	 */
	public void playTree() {
		String firstElement;
		String firstEl;
		tree = new Tree();
		Object[][] subItems = null;
		// On construit le menu en fonction du Role de la personne connectée
		if ((Authorization.Role.CL.getId().equals(roleId))) {
			subItems = ConstantsForMenuEnglish.subItemProfilNavForCL;
		} else if ((Authorization.Role.CM.getId().equals(roleId))) {
			subItems = ConstantsForMenuEnglish.subItemProfilNavForCM;
		}
		int nbItem = 0;
		if (subItems != null)
			nbItem = subItems.length;
		for (int i = 0; i < nbItem; i++) {
			firstEl = (String) subItems[i][0];
			tree.addItem(firstEl);

			// au moins 1 élément dans le tableau
			if (subItems[i].length == 1) {
				tree.setChildrenAllowed(subItems, false);
			} else {
				// On remplit le Menu
				for (int j = 1; j < subItems[i].length; j++) {
					firstElement = (String) subItems[i][j];
					tree.addItem(firstElement);
					tree.setParent(firstElement, firstEl);
					tree.setChildrenAllowed(firstElement, false);
				}
				tree.expandItemsRecursively(firstEl);
			}
		}
		tree.addListener((ItemClickListener) this);
		this.addComponent(tree);
	}

	/**
	 * This method allowed to do event, when the item selected
	 */

	@Override
	public void itemClick(ItemClickEvent event) {
		if (event.getSource() == tree) {
			// get the item in the root
			Object itemId = event.getItemId();
			if (itemId != null) {
				if (itemId
						.equals(ConstantsForMenuEnglish.VISUALIZE_ADMINISTRATIVE_DATA)) {
					// allowed to forward the view page
					this.cl = ProfileCollaboratorContent.class;
					updateObservateur();
				} else if (itemId
						.equals(ConstantsForMenuEnglish.VISUALIZE_MISSIONS_NAME)) {
					this.cl = MissionCollaboratorContent.class;
					updateObservateur();
				} else if (itemId
						.equals(ConstantsForMenuEnglish.VISUALIZE_SKILLS_NAME)) {
					this.cl = SkillCollaboratorContent.class;
					updateObservateur();
				}

			}
		}
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if (cl == IProfileLayout.class) {
			this.obs = (IProfileLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obs.updateProfileLayout(this.cl);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}