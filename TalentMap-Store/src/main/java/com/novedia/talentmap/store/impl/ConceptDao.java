package com.novedia.talentmap.store.impl;

import java.util.List;

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
	
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ConceptDao (final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	
	/**
	 * Get One Concept By Id
	 */
	@Override
	public Concept get(Concept concept) throws DataAccessException {
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CONCEPT,concept.getId());
	}
	
	/**
	 * Select all Concepts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Concept> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CONCEPT);
	}
	
	@Override
	public int add(Concept concept) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_CONCEPT, concept.getId());
	}
	
	@Override
	public int save(Concept concept) throws DataAccessException {		
		return (Integer) this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_CONCEPT, concept.getId());
	}

	@Override
	public int delete(Concept concept) throws DataAccessException {
		return  this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_CONCEPT, concept.getId());
	}

	@Override
	public Concept check(String name) throws DataAccessException {
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_CONCEPT, name);
	}
	
}