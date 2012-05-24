package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IToolDao;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ToolDao implements IToolDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * Get One Tool By Id
	 */
	@Override
	public Tool getById(int id) throws Exception {
		
		return (Tool)sqlMapClient.queryForObject("tool.getToolById",id);
	}
	
	/**
	 * Get One Tool By Name
	 */
	public Tool getByName(String name) throws Exception{
		
		return (Tool) sqlMapClient.queryForObject("tool.getToolByName", name);
	}
	
	/**
	 * Select All Tools 
	 */
	@Override
	public List<Tool> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("tool.getAllTool");
	}
	
	/**
	 * Select All Tools By Concept_ID
	 */
	public List<Tool> selectAllByConceptId(int conceptId) throws Exception {
		
		return sqlMapClient.queryForList("tool.getAllToolByConceptId", conceptId);
	}

}
