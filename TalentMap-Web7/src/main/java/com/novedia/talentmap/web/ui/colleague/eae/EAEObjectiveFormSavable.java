package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.model.entity.Objective;

public interface EAEObjectiveFormSavable {

	/**
	 * Fait la sauvegarde en base d'un object Objective
	 * @param objective
	 */
	void saveObjective(Objective objective);

	/**
	 * Rafraichi l'accordée des objectifs
	 */
	void refreshAccordion();


}
