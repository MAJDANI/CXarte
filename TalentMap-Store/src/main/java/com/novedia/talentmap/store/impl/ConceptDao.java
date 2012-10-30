package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.store.IConceptDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ConceptDao extends SqlMapClientDaoSupport implements IConceptDao {
	
	private SqlMapClient sqlMapClient;
	private int concept_id;
	private int id;

	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ConceptDao (SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Builder of a dummy Concept if the database is down
	 * @class ConceptDao.java
	 * @param id
	 * @return
	 */
	private Concept buildDummyConcept(int id){
		
		Concept c = new Concept();
		c.setCategory_id(1);
		c.setId(id);
		c.setName("IOC");
		c.setScore(2.0);
		
		return c;
	}
	
	/**
	 * Get One Concept By Id
	 */
	@Override
	public Concept getById(int id) throws SQLException {
		
		try {
			return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CONCEPT,id);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyConcept(id);
		}
	}
	
	/**
	 * Select all Concepts
	 */
	@Override
	public List<Concept> selectAll() throws Exception {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CONCEPT);
	}
	
	/**
	 * Select all Concepts By the Category_ID
	 */
	@Override
	public List<Concept> selectAllByCategoryId(int categoryId) throws Exception {		
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CONCEPT_BY_CATEGORYID,categoryId);
	}

	@Override
	public int save(Concept concept) throws Exception {
		
		this.sqlMapClient.startTransaction();
		concept_id = (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.CONCEPT_INSERT_REQUEST,concept);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return concept_id;
	}

	@Override
	public Concept checkConcept(String name, int category_id) throws Exception {
		
		Map<String, Object> mapConcept = new HashMap<String, Object>();
		mapConcept.put("name", name);
		mapConcept.put("category_id", category_id);
		return (Concept) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CONCEPT_REQUEST_CHECK,mapConcept);
	}

	@Override
	public int update(Concept concept) throws Exception {
		
		this.sqlMapClient.startTransaction();
		concept_id = (Integer) this.getSqlMapClientTemplate().update(DBRequestsConstants.CONCEPT_UPDATE_REQUEST, concept);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return concept_id;
	}

	@Override
	public int delete(int concept_id) throws Exception {

		this.sqlMapClient.startTransaction();
		int id = (Integer) this.sqlMapClient.delete("concept.deleteConcept", concept_id);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return id;
	}

}
