package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The VSkill Data Access Object.
 * @author j.collet
 */
public class VSkillDao extends SqlMapClientDaoSupport implements IVSkillDao{
/**
 * Class builder based on sqlMapClient.
 * @param sqlMapClient a sqlMapClient
 */
public VSkillDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * This method allow to get the tool by concept.
 * @param categoryName
 * @param conceptName
 * @return list of VSkill
 * @throws DataAccessException
 */
@SuppressWarnings("unchecked")
public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws DataAccessException {
	// TODO : A REVOIR un tableau de String devrait suffire
	Map<String, String> mapName = new HashMap<String, String>();
	mapName.put("categoryName", categoryName);
	mapName.put("conceptName", conceptName);
return this.getSqlMapClientTemplate()
.queryForList(DBRequestsConstants.GET_TOOL_BY_CONCEPT, mapName);
}

/**
 * @param toolName a tool name
 * @return VSkill
 * @throws DataAccessException
 */
@Override
public VSkill getSkillByTool(final String toolName) throws DataAccessException {
return (VSkill) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_SKILL_BY_TOOL, toolName);
}

/**
 * @return a list of VSkill
 * @param categoryName a categoryName
 * @throws DataAccessException
 */
@SuppressWarnings("unchecked")
@Override
public List<VSkill> getConceptByCategory(final String categoryName) throws DataAccessException {
return this.getSqlMapClientTemplate()
.queryForList(DBRequestsConstants.GET_CONCEPT_BY_CATEGORY, categoryName);
}
}
