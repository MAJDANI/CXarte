package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The Colleague DAO.
 * 
 * @author j.collet
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ColleagueDao extends SqlMapClientDaoSupport implements
		IDao<Colleague> {

	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(ColleagueDao.class);
	
	
	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public ColleagueDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Colleague get(Integer id) throws DataAccessException {
		return (Colleague) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_COLLEAGUE, id);
	}

	@Override
	public List<Colleague> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUES);
	}

	@Override
	public int save(Colleague colleague) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_COLLEAGUE, colleague);
	}

	@Override
	public int add(Colleague colleague) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Copies Colleague, EAEs, Objectives and Missions related to the colleague in parameter
	 * @param colleague the colleague we want to hitorize
	 * @return the number of (EAEs + Missions) found attached to the colleague
	 * @throws DataAccessException
	 */
	public int historize(Colleague colleague) throws DataAccessException {
		//If the colleague has no mission and no EAE we don't historize
		Integer nbEAE = (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.COUNT_COLLEAGUES_EAES, colleague);
		Integer nbMissions = (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.COUNT_COLLEAGUES_MISSIONS, colleague);
		Integer nbEAEAndMissions = nbEAE + nbMissions;
		if((nbEAEAndMissions) > 0) {
			this.getSqlMapClientTemplate().insert(DBRequestsConstants.HISTORIZE_COLLEAGUE, colleague);
			this.getSqlMapClientTemplate().insert(DBRequestsConstants.HISTORIZE_COLLEAGUES_EAES, colleague);
			this.getSqlMapClientTemplate().insert(DBRequestsConstants.HISTORIZE_COLLEAGUES_EAES_OBJECTIVES, colleague);
			this.getSqlMapClientTemplate().insert(DBRequestsConstants.HISTORIZE_COLLEAGUES_MISSIONS,colleague);
		}
		return nbEAEAndMissions;
	}

	
	@Override
	public int delete(Colleague colleague) throws DataAccessException {
		System.out.println("ICI----------------------");
		if (logger.isDebugEnabled()) {
			logger.debug("Delete colleague ID :" + colleague.getId());
		}
		Integer DeletResult = this.getSqlMapClientTemplate().delete(
				DBRequestsConstants.DELETE_colleague, colleague);
		if (DeletResult > 0) {
			this.getSqlMapClientTemplate().update(
					DBRequestsConstants.ERASE_MANAGER_COLLEAGUE, colleague);
		}
		return DeletResult;
	}

	@Override
	public Colleague getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Colleague check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	public Integer countMail(String mail) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.COUNT_COLLEAGUE_BY_MAIL, mail);
	}

	/**
	 * Counts colleagues in DB that have already the mail, with a different
	 * colleagueId. Used to check when a colleague wants to modifiy his email,
	 * if this email is not already use by someone else.
	 * 
	 * @param the
	 *            colleague who wants to change his email
	 * @return The count found.
	 */
	public Integer countMailForColleagueId(Colleague colleague)
			throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.COUNT_COLLEAGUE_BY_MAIL_AND_ID, colleague);
	}

    /**
     * Counts colleagues in DB that have already the mail, with a different
     * colleague's last and first name. Used to check, during the registration, if
     * his email is not already use by someone else.
     * 
     * @param email : the email to check
     * @param colleagueFirstName : first name of the colleague who wants to register
     * @param colleagueLastName : last name of the colleague who wants to register
     * @return The count found.
     */
	public Integer countMailForColleagueNames(String email, String colleagueFirstName,
			String colleagueLastName)
			throws DataAccessException {
		Colleague colleague = new Colleague();
		colleague.setEmail(email);
		colleague.setLastName(colleagueLastName);
		colleague.setFirstName(colleagueFirstName);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.COUNT_COLLEAGUE_BY_MAIL_AND_NAMES, colleague);
	}

	public Integer addColleagueFromRegistration(Registration registration)
			throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_COLLEAGUE_FROM_REGISTRATION,
				registration);
	}

	public List<Colleague> getAllColleaguesByName(String name)
			throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUES_BY_NAME, name);
	}

	public List<Colleague> getAllColleaguesByClient(Client client)
			throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUES_BY_CLIENT, client);
	}

	public List<Colleague> getCmColleaguesByClient(int clientId, int managerId)
			throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("clientId", clientId);
		parameters.put("managerId", managerId);
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_CM_COLLEAGUES_BY_CLIENT, parameters);
	}

	public List<Colleague> getCmColleaguesByName(String name, int managerId)
			throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("name", name);
		parameters.put("managerId", managerId);
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_CM_COLLEAGUES_BY_NAME, parameters);
	}

	public List<Colleague> getAllColleaguesByListToolId(Map ToolIdMap)
			throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUES_BY_LIST_TOOL_ID,
				ToolIdMap);
	}

	public List<Colleague> getAllConsultantManager() throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_CONSULTANT_MANAGER);
	}
	
	public List<Colleague> getAllBusinessEngineers() throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_BUSINESS_ENGINEERS);
	}

	public List<Colleague> getAllColleagueByColleagueIdList(
			List<Integer> listColleagueId) throws DataAccessException {
		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setListCollaborators(listColleagueId);
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUE_BY_COLL_ID_LIST,
				skillParameter);
	}

}