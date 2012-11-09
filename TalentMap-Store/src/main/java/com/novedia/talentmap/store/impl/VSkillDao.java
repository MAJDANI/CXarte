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
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class VSkillDao extends SqlMapClientDaoSupport implements IVSkillDao{
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public VSkillDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@SuppressWarnings("unchecked")
	public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws DataAccessException {
		// TODO : A REVOIR un tableau de String devrait suffire
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("categoryName", categoryName);
		mapName.put("conceptName", conceptName);
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_TOOL_BY_CONCEPT, mapName);
	}
	
	@Override
	public VSkill getSkillByTool(String toolName) throws DataAccessException {
		return (VSkill) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_SKILL_BY_TOOL,toolName);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VSkill> getConceptByCategory(String categoryName) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_CONCEPT_BY_CATEGORY, categoryName);
	}
}
