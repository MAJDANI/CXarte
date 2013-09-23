package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
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
	 * 
	 * @param sqlMapClient
	 *            the sqlMapClient to set
	 */
	public ConceptDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * Get One Concept By Id
	 */
	@Override
	public Concept get(Integer id) throws DataAccessException {
		return (Concept) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_CONCEPT, id);
	}

	/**
	 * Select all Concepts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Concept> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_CONCEPT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(Concept concept) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_CONCEPT, concept);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(Concept concept) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_CONCEPT, concept);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Concept concept) throws DataAccessException {
		return this.getSqlMapClientTemplate().delete(
				DBRequestsConstants.DELETE_CONCEPT, concept);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Concept check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public Concept check(Concept concept) throws DataAccessException {
		return (Concept) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.CHECK_CONCEPT_OBJECT, concept);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Concept getByName(String name) throws DataAccessException {
		return (Concept) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_CONCEPT_BY_NAME, name);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Concept> getAllConceptByCategory(Category category)
			throws DataAccessException {
		return (List<Concept>) this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_CONCEPT_BY_CATEGORY, category);
	}

}