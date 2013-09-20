package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The {@link ObjectiveDao} handles query for table Objective.
 * 
 * @author moumbe
 * 
 */
public class ObjectiveDao extends SqlMapClientDaoSupport implements
		IDao<Objective> {
    /**
     * The logger
     */
    private static Log logger = LogFactory.getLog(ObjectiveDao.class);

    /**
     * Class builder based on sqlMapClient
     * 
     * @param sqlMapClient
     */
    public ObjectiveDao(final SqlMapClient sqlMapClient) {
	if (logger.isDebugEnabled()) {
	    logger.debug("Create ObjectiveDao");
	}
	setSqlMapClient(sqlMapClient);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective get(final Integer id) throws DataAccessException {
		return (Objective) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_OBJECTIVE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Objective> getAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(final Objective objective) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_OBJECTIVE, objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final Objective objective) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_OBJECTIVE, objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final Objective objective) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(
				DBRequestsConstants.DELETE_OBJECTIVE, objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective getByName(final String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective check(final String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    /**
     * Gets, for a given EAE id, the list of Objective related
     * 
     * @param idEAE
     *            : the id of the EAE we search Objectives related
     * @return List<Objective> : a list of Objective attached to the EAE
     * 
     */
	public List<Objective> getObjectivesByEAEId(Integer idEAE)
			throws DataAccessException {
		return (List<Objective>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_OBJECTIVES_FOR_EAE, idEAE);
	}

    /**
	 * Gets, for a given EAE id, the list of precedent objectives related, i.e.
	 * the objectives of the precedent EAE. These objectives are displayed
	 * in the part "Results" of an EAE
	 * 
	 * @param idEAE
	 *            : the id of the EAE we search Precedent Objectives related
	 * @return List<Objective> : a list of Precedent Objective attached to the
	 *         EAE
     * 
     */
	public List<Objective> getPrecedentObjectivesByEAEId(Integer idEAE)
			throws DataAccessException {
		return (List<Objective>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_PRECEDENT_OBJECTIVES_FOR_EAE, idEAE);
	}

}