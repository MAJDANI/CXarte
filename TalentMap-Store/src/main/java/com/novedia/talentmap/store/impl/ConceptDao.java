package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ConceptDao extends SqlMapClientDaoSupport implements IDao<Concept> {
	
	
	/** For log management */
	private static Log logger = LogFactory.getLog(ConceptDao.class);
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ConceptDao (final SqlMapClient sqlMapClient){
		if(logger.isDebugEnabled()) {
			logger.debug("Create ConceptDao");
		}
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Get One Concept By Id
	 */
	@Override
	public Concept get(Integer id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Get Concept with id : " + id);
		}
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CONCEPT, id);
	}
	
	/**
	 * Select all Concepts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Concept> getAll() throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Gets all concept");
		}
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CONCEPT);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(Concept concept) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Add concept");
		}
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_CONCEPT, concept.getConcept_id());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(Concept concept) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Save concept");
		}
		return (Integer) this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_CONCEPT, concept.getConcept_id());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Concept concept) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Delete concept");
		}
		return  this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_CONCEPT, concept.getConcept_id());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Concept check(String name) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Check concept");
		}
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_CONCEPT, name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Concept getByName(String name) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Gets concept by name");
		}
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CONCEPT_BY_NAME, name);
	}
	
}