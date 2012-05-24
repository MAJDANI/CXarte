package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.store.IConceptDao;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ConceptDao implements IConceptDao {
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	/**
	 * Get One Concept By Id
	 */
	@Override
	public Concept getById(int id) throws Exception {
		
		return (Concept)sqlMapClient.queryForObject("concept.getConcept", id);
	}
	
	/**
	 * Select all Concepts
	 */
	@Override
	public List<Concept> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("concept.getAllConcept");
	}
	
	/**
	 * Select all Concepts By the Category_ID
	 */
	@Override
	public List<Concept> selectAllByCategoryId(int categoryId) throws Exception {
		
		return sqlMapClient.queryForList("concept.getAllConceptByCategoryId", categoryId);
	}

}
