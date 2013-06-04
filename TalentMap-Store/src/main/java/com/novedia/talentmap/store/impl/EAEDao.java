package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.Category;
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
    public List<EAEForSynthesis> getOngoingEAEForCM(Integer id)
	    throws DataAccessException {
	return (List<EAEForSynthesis>) this.getSqlMapClientTemplate()
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
    public List<Colleague> getCollabWithoutOngoingEAEForManager(Integer id)
	    throws DataAccessException {
	return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(
		DBRequestsConstants.GET_WITHOUT_ONGOING_EAE_FOR_CM, id);
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