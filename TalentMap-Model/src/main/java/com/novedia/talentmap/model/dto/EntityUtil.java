/**
 * 
 */
package com.novedia.talentmap.model.dto;

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
//    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
//	    "dd/MM/yyyy");
    
    
    public static Date parseDate(String date, String format) {
	Date result = null;
	try {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		result = formatter.parse(date);
		return result;
	} catch (Exception e) {
		return null;
	}
}

    /**
     * Builds an object EAE with all MANDATORY fields
     */
    public static EAE createEAE(Integer id, Integer colleagueId,
	    Integer managerId, Date eaeDate, Integer eaeStateId) {

	Colleague colleague = Colleague.builder().id(colleagueId).build();
	Colleague manager = Colleague.builder().id(managerId).build();
	EAEState eaeState = EAEState.builder().id(eaeStateId).build();
	EAE eae = new EAE();
	eae.setId(id);
	eae.setColleague(colleague);
	eae.setManager(manager);
	eae.setEaeState(eaeState);
	eae.setDateEae(eaeDate);

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
	Date eaeDate = new Date ();
	Integer eaeStateId = 1;

	Colleague colleague = Colleague.builder().id(colleagueId).build();
	Colleague manager = Colleague.builder().id(managerId).build();
	EAEState eaeState = EAEState.builder().id(eaeStateId).build();

	EAE eae = EAE.builder().id(id).colleague(colleague).manager(manager)
		.dateEae(eaeDate).eaeState(eaeState).build();
	return eae;
    }

}
