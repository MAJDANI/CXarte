package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IToolDao;

public class ToolDao implements IToolDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	
	@Override
	public Tool getById(int id) throws Exception {
		
		return (Tool)sqlMapClient.queryForObject("tool.getTool",id);
	}

	@Override
	public List<Tool> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("tool.getAllTool");
	}

}
