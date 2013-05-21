package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;

/**
 * The Registration Service Interface.
 * 
 * @author y.rohr
 */
public interface IRegistrationService {

	/**
	 * Test if the user to register is already existing
	 * 
	 * @param registration
	 * @return
	 */
	Colleague check(Registration registration);

	/**
	 * Add a colleague from a registration
	 * 
	 * @param registration
	 * @return
	 */
	@Transactional
	Integer addColleagueFromRegistration(Registration registration);

	/**
	 * Get All profiles
	 * 
	 * @return
	 */
	List<Profile> getAllProfile();

	/**
	 * Get all Consultant Manager
	 */
	List<Colleague> getAllConsultantManager();

	Integer countLogin(String login);

	Integer countMail(String mail);

}
