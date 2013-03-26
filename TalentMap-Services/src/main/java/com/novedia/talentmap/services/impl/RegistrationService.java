package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.AuthenticationDao;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.store.impl.ProfileDao;

/**
 * The registration service.
 * 
 * @author y.rohr
 */
public class RegistrationService implements IRegistrationService {
	
	/**
	 * The colleague DAO.
	 */
	private IDao<Colleague> colleagueDao;
	
	/**
	 * The authentication DAO.
	 */
	private IDao<Authentication> authenticationDao;
	
	/**
	 * The profile DAO.
	 */
	private IDao<Profile> profileDao;
	
	/**
	 * The businessEngineer DAO.
	 */
	private IDao<BusinessEngineer> businessEngineerDao;

	@Override
	public Colleague check(Registration registration) {
		throw new UnsupportedOperationException();
	}

	public Colleague getByMail(String mail) {
		return ((ColleagueDao) colleagueDao).getByMail(mail);
	}
	
	@Override
	public Integer addColleagueFromRegistration(Registration registration) throws DataAccessException{
		return ((ColleagueDao) colleagueDao).addColleagueFromRegistration(registration);
	}

	@Override
	public Integer addUserFromRegistration(Registration registration){
		return ((AuthenticationDao) authenticationDao).addUserFromRegistration(registration);
	}
	
	@Override
	public List<Profile> getAllProfile() throws DataAccessException {
		return ((ProfileDao) profileDao).getAll();
	}
	
	@Override
	public List<Colleague> getAllConsultantManager() {
		return ((ColleagueDao) colleagueDao).getAllConsultantManager();
	}
	/**
	 * Counts the number of login "login" existing in table Authentication.
	 * It's used to assure a user won't choose a login already existing.
	 * @param login : the login to check in table
	 * @return the number of login found in table
	 */
	public Integer countLogin(String login) {
		AuthenticationDao authentificationDao = (AuthenticationDao) this.authenticationDao;
		return authentificationDao.countLogin(login);
	}

	public IDao<Colleague> getColleagueDao() {
		return colleagueDao;
	}

	public void setColleagueDao(IDao<Colleague> colleagueDao) {
		this.colleagueDao = colleagueDao;
	}

	public IDao<Authentication> getAuthenticationDao() {
		return authenticationDao;
	}

	public void setAuthenticationDao(IDao<Authentication> authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	public IDao<Profile> getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(IDao<Profile> profileDao) {
		this.profileDao = profileDao;
	}

}
