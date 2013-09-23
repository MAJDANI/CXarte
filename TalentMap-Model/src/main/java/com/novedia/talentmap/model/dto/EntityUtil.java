/**
 * 
 */
package com.novedia.talentmap.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.model.entity.EAEState;

/**
 * @author v.guillemain
 * 
 */
public class EntityUtil {

    public static SimpleDateFormat formatter = new SimpleDateFormat(
	    "yyyy-MM-dd");

    public static Date stringToDate(String sDate) throws ParseException {
	return formatter.parse(sDate);
    }

    /**
     * Builds an object EAE with all MANDATORY fields
     */
    public static EAE createEAE(Integer id, Integer colleagueId,
	    Integer managerId, /* Date eaeDate */String date, Integer eaeStateId) {

	Colleague colleague = Colleague.builder().id(colleagueId).build();
	Colleague manager = Colleague.builder().id(managerId).build();
	EAEState eaeState = EAEState.builder().id(eaeStateId).build();
	EAE eae = new EAE();
	eae.setId(id);
	eae.setColleague(colleague);
	eae.setManager(manager);
	eae.setEaeState(eaeState);
	Date laDate = Calendar.getInstance().getTime();
	try {
	    laDate = stringToDate(date);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	eae.setDateEae(laDate);

	return eae;
    }

    /**
     * Builds an object EAE with all MANDATORY fields. Mandatory fields are
     * filled with default values
     */
    public static EAE createAnyEAE() {
	Integer id = 999;
	Integer colleagueId = 999;
	Integer managerId = 999;
	Date eaeDate = new Date();
	Integer eaeStateId = 1;

	Colleague colleague = Colleague.builder().id(colleagueId).build();
	Colleague manager = Colleague.builder().id(managerId).build();
	EAEState eaeState = EAEState.builder().id(eaeStateId).build();

	EAE eae = EAE.builder().id(id).colleague(colleague).manager(manager)
		.dateEae(eaeDate).eaeState(eaeState).build();
	return eae;
    }

    public static EAEForSynthesisDTO createEAEForSynthesis(Integer id,
	    String dateEae, String lastName, String firstName,
	    Integer eaeStateId, String eaeStateLabel) {
	EAEForSynthesisDTO eae = new EAEForSynthesisDTO();
	eae.setId(id);

	Date laDate = Calendar.getInstance().getTime();
	try {
	    laDate = stringToDate(dateEae);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	eae.setDateEae(laDate);
	eae.setLastName(lastName);
	eae.setFirstName(firstName);
	eae.setEaeStateId(eaeStateId);
	eae.setEaeStateLabel(eaeStateLabel);
	return eae;
    }

    public static EAEGeneralityDTO createEAEGenerality(Integer id,
	    String collabLastName, String collabFirstName, String profile,
	    String managerLastName, String managerFirstName, String eaeDate,
	    String employmentDate, String previousEaeDate, Integer eaeStateId) {

	EAEGeneralityDTO eae = new EAEGeneralityDTO();
	eae.setId(id);

	Date laDate = Calendar.getInstance().getTime();
	try {
	    laDate = stringToDate(eaeDate);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	eae.setCollabLastName(collabLastName);
	eae.setCollabFirstName(collabFirstName);
	eae.setProfile(profile);
	eae.setManagerLastName(managerLastName);
	eae.setManagerFirstName(managerFirstName);
	eae.setEaeDate(eaeDate);
	eae.setEmploymentDate(employmentDate);
	eae.setPreviousEaeDate(previousEaeDate);
	eae.setEaeStateId(eaeStateId);
	return eae;
    }

}
