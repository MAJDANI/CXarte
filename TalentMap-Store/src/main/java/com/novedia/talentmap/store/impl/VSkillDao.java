package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IVSkillDao;

/**
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
	@Autowired
	public VSkillDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws DataAccessException {
		
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("categoryName", categoryName);
		mapName.put("conceptName", conceptName);
		//return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_TOOL_BY_CONCEPT, mapName);
		return this.getSqlMapClientTemplate().queryForList("", mapName);
	}
	
	
	
	public VSkill getSkillByTool(String toolName) throws DataAccessException {
		//return (VSkill) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_SKILL_BY_TOOL,toolName);
		return (VSkill) this.getSqlMapClientTemplate().queryForObject("",toolName);
	}
	
	
	
	public List<VSkill> getConceptByCategory(String categoryName) throws DataAccessException {
		//return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_CONCEPT_BY_CATEGORY, categoryName);
		return this.getSqlMapClientTemplate().queryForList("", categoryName);
	}
}
