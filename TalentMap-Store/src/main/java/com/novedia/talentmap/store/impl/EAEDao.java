package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.dto.EAEColleagueResumeForCMDTO;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.dto.EAESynthesisDTO;
import com.novedia.talentmap.model.dto.NewEAEDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The EAE DAO
 * 
 * @author v.guillemain
 * 
 */
public class EAEDao extends SqlMapClientDaoSupport implements IDao<EAE> {

	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(EAEDao.class);

	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public EAEDao(final SqlMapClient sqlMapClient) {
		if (logger.isDebugEnabled()) {
			logger.debug("Create EAEDao");
		}
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EAE get(Integer id) throws DataAccessException {
		return (EAE) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_EAE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final EAE eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_EAE, eae);
	}

	/**
	 * Adds the NewEAEDTO.
	 * 
	 * @class IEAEService.java
	 * @param eae
	 *            the NewEAEDTO to add
	 * @return an integer, result of the request
	 */
	public int addNewEAEDTO(NewEAEDTO eae) {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_NEW_EAE_DTO, eae);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(final EAE eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_EAE, eae);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final EAE eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(
				DBRequestsConstants.DELETE_EAE, eae);
	}

	/**
	 * Gets, for a given manager specifier by the parameter id, the list of EAE
	 * that are either OPEN or VALIDATED
	 * 
	 * @param id
	 *            : the id of the manager searching for his ongoing EAE
	 * @return List<EAE> : a list of EAE wich state is OPEN or VALIDATED
	 * @throws DataAccessException
	 */
	public List<EAEForSynthesisDTO> getOngoingEAEForCM(Integer id)
			throws DataAccessException {
		return (List<EAEForSynthesisDTO>) this.getSqlMapClientTemplate()
				.queryForList(DBRequestsConstants.GET_ONGOING_EAE_FOR_CM, id);
	}

	/**
	 * Gets, for a given manager specifier by the parameter id, the list of
	 * Colleague that haven't OPEN or VALIDATED EAE. These colleagues have
	 * closed EAE, or may don't have any EAE yet.
	 * 
	 * @param id
	 *            : the id of the manager searching for his colleagues without
	 *            ongoing EAE
	 * @return List<Colleague> : a list of Colleague
	 * @throws DataAccessException
	 */
	@Deprecated
	public List<Colleague> getCollabWithoutOngoingEAEForManager(Integer id)
			throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_WITHOUT_ONGOING_EAE_FOR_CM, id);
	}

	/**
	 * Gets, for a given colleague specified by the parameter idCollab, the list
	 * of all his EAE, with the state for each one.
	 * 
	 * @param id
	 *            : the id of the colleague searching for his colleagues EAE
	 *            history
	 * 
	 * @return List<EAEForSynthesisDTO> : a list of EAEForSynthesisDTO
	 * @throws DataAccessException
	 */
	public List<EAEForSynthesisDTO> getHistoryEAEForCollab(Integer idCollab)
			throws DataAccessException {
		return (List<EAEForSynthesisDTO>) this.getSqlMapClientTemplate()
				.queryForList(DBRequestsConstants.GET_HISTORY_EAE_FOR_COLLAB,
						idCollab);
	}

	/**
	 * Gets general informations corresponding to the given EAE's id
	 * 
	 * @param id
	 *            : the id of the EAE which we want general datas
	 * 
	 * @return EAEGeneralityDTO : an object EAEGeneralityDTO containing general
	 *         informations of the EAE
	 * @throws DataAccessException
	 */
	public EAEGeneralityDTO getEAEGenerality(Integer id)
			throws DataAccessException {
		return (EAEGeneralityDTO) this.getSqlMapClientTemplate()
				.queryForObject(DBRequestsConstants.GET_EAE_GENERALITY, id);
	}

	/**
	 * Gets Results informations corresponding to the given EAE's id
	 * 
	 * @param id
	 *            : the id of the EAE which we want results datas
	 * 
	 * @return EAEResultsDTO : an object EAEResultsDTO containing results
	 *         informations of the EAE
	 * @throws DataAccessException
	 */
	public EAEResultsDTO getEAEResults(Integer id) throws DataAccessException {
		return (EAEResultsDTO) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_EAE_RESULTS, id);
	}

	/**
	 * Gets Synthesis informations corresponding to the given EAE's id
	 * 
	 * @param id
	 *            : the id of the EAE which we want synthesis data
	 * 
	 * @return EAEResultsDTO : an object EAESynthesisDTO containing synthesis
	 *         informations of the EAE
	 * @throws DataAccessException
	 */
	public EAESynthesisDTO getEAESynthesis(Integer id)
			throws DataAccessException {
		return (EAESynthesisDTO) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_EAE_SYNTHESIS, id);
	}

	/**
	 * Gets the id of the EAE Open for the Colleague's id given. If no EAE Open
	 * is found, returns null
	 * 
	 * @param id
	 *            : the id of the colleague which we want to count open EAE
	 * 
	 * @return Integer : the id or null
	 */
	public Integer getOpenEAEIdForColleague(Integer id)
			throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_OPEN_EAE_ID, id);
	}

	/**
	 * Gets the list of informations about the last EAE for each colleague
	 * attached to the Manager. This information will be displayed on the
	 * manager's board for his colleague's EAEs
	 * 
	 * @param id
	 *            : the id of the manager who want to get his colleagues' EAE
	 *            informations
	 * 
	 * @return List<EAEColleagueResumeForCMDTO> : the list of
	 *         EAEColleagueResumeForCMDTO found
	 */
	public List<EAEColleagueResumeForCMDTO> getEAEColleagueResumeForCM(
			Integer id) throws DataAccessException {
		return (List<EAEColleagueResumeForCMDTO>) this
				.getSqlMapClientTemplate().queryForList(
						DBRequestsConstants.GET_EAE_COLL_RESUME_FOR_CM, id);
	}

	/**
	 * Gets the number of empty fields for an eae id. The empty fields we look
	 * for are the fields the colleague has to fill before validating the EAE.
	 * These fields are the SALARY, COLLEAGUES_STRENGTHS, COLLEAGUES_WEAKNESSES, 
	 * and for each objective attached to the eae COL_OBJ_SCORE_ID (the score)
	 * and MOTIVES_OR_RESTRAINTS. If the number returned is 0, the colleague
	 * is allowed to validate his EAE.
	 * 
	 * @param id
	 *            : the id of the EAE we want to get the number of empty fields
	 * 
	 * @return Integer : the number of empty fields found.
	 */
	public Integer getNbEmptyFieldsByEAE(Integer id) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_NB_EMPTY_FIELDS_BY_EAE_ID, id);
	}

	/**
	 * Gets the previous EAE_ID of the EAE concerned.
	 * 
	 * @param id
	 *            : the id of the EAE we want to get the previous EAE_ID
	 * 
	 * @return Integer : the previous EAE_ID.
	 */
	public Integer getPreviousEAEID(Integer id) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_PREVIOUS_EAE_ID, id);
	}

	/**
	 * Gets the SALARY for the EAE concerned.
	 * 
	 * @param id
	 *            : the id of the EAE we want to get the SALARY
	 * 
	 * @return Integer : the SALARY
	 */
	public Integer getSalaryByEAEID(Integer id) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_SALARY_BY_EAE_ID, id);
	}

	/**
	 * Sets the EAE to VALIDATE state.
	 * 
	 * @param id
	 *            : the id of the EAE we want to validate
	 * 
	 */
	public Integer validateEAEById(Integer id) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.VALIDATE_EAE_BY_EAE_ID, id);
	}

	/**
	 * Sets the EAE to CLOSE state.
	 * 
	 * @param id
	 *            : the id of the EAE we want to close
	 * 
	 */
	public Integer closeEAEById(Integer id) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.CLOSE_EAE_BY_EAE_ID, id);
	}

	/**
	 * Saves the salary for the EAE given.
	 * 
	 * @param eae
	 *            : the EAEGeneralityDTO to save
	 * 
	 */
	public int saveEAESalary(EAEGeneralityDTO eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_EAE_SALARY, eae);
	}

	/**
	 * Saves the "Results" data for the EAE given.
	 * 
	 * @param eae
	 *            : the EAEResultsDTO to save
	 * 
	 */
	public int saveEAEResults(EAEResultsDTO eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_EAE_RESULTS, eae);
	}

	/**
	 * Saves the "Synthesis" data for the EAE given.
	 * 
	 * @param eae
	 *            : the EAESynthesisDTO to save
	 * 
	 */
	public int saveEAESynthesis(EAESynthesisDTO eae) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_EAE_SYNTHESIS, eae);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EAE check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EAE getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EAE> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}
}
