package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.store.IVSkillCollabDao;

public class VSkillCollabDao implements IVSkillCollabDao {

	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	/**
	 * Get All Collaborators' Skill by ID
	 */
	@Override
	public List<VSkillCollab> getAllSkillCollab(int collab_id) throws Exception {
		
		return sqlMapClient.queryForList("vskillCollab.getAllSkillCollab", collab_id);
	}

}
