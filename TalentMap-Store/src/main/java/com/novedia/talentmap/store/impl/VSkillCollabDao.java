package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkillCollab;
import com.novedia.talentmap.store.IVSkillCollabDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The skill and collaborator link table DAO.
 * 
 * @author j.marie-sainte
 *
 */
public class VSkillCollabDao extends SqlMapClientDaoSupport implements IVSkillCollabDao {
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public VSkillCollabDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Get All Collaborators' Skill by ID
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VSkillCollab> getAllSkillCollab(int collab_id) throws DataAccessException {
		String t="";
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_SKILL_COLLAB, collab_id);
	}

	/**
	 * Get VSkillCollab by id
	 */
	@Override
	public VSkillCollab get(int id) throws DataAccessException {
		return (VSkillCollab) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_ALL_SKILL_COLLAB, id);
	}

}
