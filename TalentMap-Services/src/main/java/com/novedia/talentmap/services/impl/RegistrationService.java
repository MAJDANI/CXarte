package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.novedia.talentmap.model.entity.Authentication;
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

	@Override
	public Colleague check(Registration registration) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the colleague corresponding to the mail in parameter. Returns Null
	 * if not found.
	 * 
	 * @param mail
	 *            : the mail to find
	 * @return The colleague found or null.
	 */
	@Override
	public Integer countMail(String mail) {
		return ((ColleagueDao) colleagueDao).countMail(mail);
	}

	@Override
	@Transactional(rollbackFor = { DataAccessException.class })
	public Integer addColleagueFromRegistration(Registration registration)
			throws DataAccessException {
		Integer res = -1;
		((ColleagueDao) colleagueDao)
				.addColleagueFromRegistration(registration);
		res = ((AuthenticationDao) authenticationDao)
				.addUserFromRegistration(registration);
		return res;
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
	 * Counts the number of login "login" existing in table Authentication. It's
	 * used to assure a user won't choose a login already existing.
	 * 
	 * @param login
	 *            : the login to check in table
	 * @return the number of login found in table
	 */
	@Override
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
