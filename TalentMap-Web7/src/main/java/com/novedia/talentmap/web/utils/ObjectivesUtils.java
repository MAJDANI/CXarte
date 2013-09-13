package com.novedia.talentmap.web.utils;

import java.util.Hashtable;

import com.novedia.talentmap.web.ui.colleague.eae.EAEConsultationMode;

public class ObjectivesUtils {
	
	private static Hashtable<String,String> ht = new Hashtable<String,String>();

	private void fillHashModes() {
	    ht.put("goal"+"bilan"+"OPEN_MANAGER", "CONSULTATION");
	    ht.put("goal"+"bilan"+"VALIDATED_MANAGER", "CONSULTATION");
	    ht.put("goal"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("goal"+"bilan"+"OPEN_COLLAB", "CONSULTATION");
	    ht.put("goal"+"bilan"+"VALIDATED_COLLAB", "CONSULTATION");
	    ht.put("goal"+"objectif"+"OPEN_MANAGER", "ABSENT");
	    ht.put("goal"+"objectif"+"VALIDATED_MANAGER", "MODIFICATION");
	    ht.put("goal"+"objectif"+"CLOSED", "CONSULTATION");

	    ht.put("targetDate"+"bilan"+"OPEN_MANAGER", "CONSULTATION");
	    ht.put("targetDate"+"bilan"+"VALIDATED_MANAGER", "CONSULTATION");
	    ht.put("targetDate"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("targetDate"+"bilan"+"OPEN_COLLAB", "CONSULTATION");
	    ht.put("targetDate"+"bilan"+"VALIDATED_COLLAB", "CONSULTATION");
	    ht.put("targetDate"+"objectif"+"OPEN_MANAGER", "ABSENT");
	    ht.put("targetDate"+"objectif"+"VALIDATED_MANAGER", "MODIFICATION");
	    ht.put("targetDate"+"objectif"+"CLOSED", "CONSULTATION");

	    ht.put("indicators"+"bilan"+"OPEN_MANAGER", "CONSULTATION");
	    ht.put("indicators"+"bilan"+"VALIDATED_MANAGER", "CONSULTATION");
	    ht.put("indicators"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("indicators"+"bilan"+"OPEN_COLLAB", "CONSULTATION");
	    ht.put("indicators"+"bilan"+"VALIDATED_COLLAB", "CONSULTATION");
	    ht.put("indicators"+"objectif"+"OPEN_MANAGER", "ABSENT");
	    ht.put("indicators"+"objectif"+"VALIDATED_MANAGER", "MODIFICATION");
	    ht.put("indicators"+"objectif"+"CLOSED", "CONSULTATION");

	    ht.put("means"+"bilan"+"OPEN_MANAGER", "CONSULTATION");
	    ht.put("means"+"bilan"+"VALIDATED_MANAGER", "CONSULTATION");
	    ht.put("means"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("means"+"bilan"+"OPEN_COLLAB", "CONSULTATION");
	    ht.put("means"+"bilan"+"VALIDATED_COLLAB", "CONSULTATION");
	    ht.put("means"+"objectif"+"OPEN_MANAGER", "ABSENT");
	    ht.put("means"+"objectif"+"VALIDATED_MANAGER", "MODIFICATION");
	    ht.put("means"+"objectif"+"CLOSED", "CONSULTATION");

	    ht.put("colleagueScore"+"bilan"+"OPEN_MANAGER", "ABSENT");
	    ht.put("colleagueScore"+"bilan"+"VALIDATED_MANAGER", "CONSULTATION");
	    ht.put("colleagueScore"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("colleagueScore"+"bilan"+"OPEN_COLLAB", "MODIFICATION");
	    ht.put("colleagueScore"+"bilan"+"VALIDATED_COLLAB", "CONSULTATION");
	    ht.put("colleagueScore"+"objectif"+"VALIDATED_MANAGER", "ABSENT");
	    ht.put("colleagueScore"+"objectif"+"CLOSED", "CONSULTATION");
	    
	    ht.put("managerScore"+"bilan"+"OPEN_MANAGER", "ABSENT");
	    ht.put("managerScore"+"bilan"+"VALIDATED_MANAGER", "MODIFICATION");
	    ht.put("managerScore"+"bilan"+"CLOSED", "CONSULTATION");
	    ht.put("managerScore"+"bilan"+"OPEN_COLLAB", "ABSENT");
	    ht.put("managerScore"+"bilan"+"VALIDATED_COLLAB", "ABSENT");
	    ht.put("managerScore"+"objectif"+"VALIDATED_MANAGER", "ABSENT");
	    ht.put("managerScore"+"objectif"+"CLOSED", "CONSULTATION");
	    
	}
	
	public static String getMode(String objet, String onglet, EAEConsultationMode modeConsultation) {
		String mode ="";
		return mode;
	}

}
