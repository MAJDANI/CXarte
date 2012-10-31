package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

public class VSkillCollabDao extends SqlMapClientDaoSupport implements IVSkillCollabDao {

	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void VSkillCollabDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Get All Collaborators' Skill by ID
	 */
	@Override
	public List<VSkillCollab> getAllSkillCollab(int collab_id) throws Exception {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_SKILL_COLLAB, collab_id);
	}

}
