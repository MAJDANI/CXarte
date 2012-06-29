package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Concept getById(int id) {
		
		try {
			
			return (Concept)sqlMapClient.queryForObject("concept.getConcept", id);
//			return buildDummyConcept(id);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			
			return buildDummyConcept(id);
			
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
		
		return sqlMapClient.queryForList("concept.getAllConcept");
	}
	
	/**
	 * Select all Concepts By the Category_ID
	 */
	@Override
	public List<Concept> selectAllByCategoryId(int categoryId) throws Exception {
		
		return sqlMapClient.queryForList("concept.getAllConceptByCategoryId", categoryId);
	}

	@Override
	public int saveOne(Concept concept) throws Exception {
		
		this.sqlMapClient.startTransaction();
		
		int concept_id = (Integer) this.sqlMapClient.insert("concept.insertConcept", concept);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return concept_id;
	}

	@Override
	public Concept checkConcept(String name, int category_id) throws Exception {
		
		Map<String, Object> mapConcept = new HashMap<String, Object>();
		mapConcept.put("name", name);
		mapConcept.put("category_id", category_id);
		
		return (Concept) this.sqlMapClient.queryForObject("concept.checkConcept", mapConcept);
	}

}
