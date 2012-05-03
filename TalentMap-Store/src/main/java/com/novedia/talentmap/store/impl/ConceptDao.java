package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.store.IConceptDao;

public class ConceptDao implements IConceptDao {
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	

	@Override
	public Concept getById(int id) throws Exception {
		
		return (Concept)sqlMapClient.queryForObject("concept.getConcept", id);
	}

	@Override
	public List<Concept> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("concept.getAllConcept");
	}

}
