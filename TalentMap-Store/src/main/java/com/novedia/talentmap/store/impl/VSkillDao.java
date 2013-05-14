package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The VSkill Data Access Object.
 * 
 * @author j.collet
 */
public class VSkillDao extends SqlMapClientDaoSupport implements IVSkillDao {
    /**
     * Class builder based on sqlMapClient.
     * 
     * @param sqlMapClient
     *            a sqlMapClient
     */
    public VSkillDao(final SqlMapClient sqlMapClient) {
	setSqlMapClient(sqlMapClient);
    }

    /**
     * @return all VSkill ordered by category and concept
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<VSkill> getAllVSkillOrdered() throws DataAccessException {
	return this.getSqlMapClientTemplate().queryForList(
		DBRequestsConstants.GET_ALL_VSKILL_ORDERED);
    }

    /**
     * @param toolName
     *            a tool name
     * @return VSkill
     * @throws DataAccessException
     */
    @Override
    public VSkill getSkillByTool(final String toolName)
	    throws DataAccessException {
	return (VSkill) this.getSqlMapClientTemplate().queryForObject(
		DBRequestsConstants.GET_SKILL_BY_TOOL, toolName);
    }

}
