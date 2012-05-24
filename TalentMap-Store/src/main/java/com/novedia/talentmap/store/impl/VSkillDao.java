package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class VSkillDao implements IVSkillDao {
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Select all Tools By Concept_Name and the Category_Name
	 */
	@Override
	public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws SQLException {
		
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("categoryName", categoryName);
		mapName.put("conceptName", conceptName);
		
		return sqlMapClient.queryForList("vskill.getToolByConcept", mapName);
	}
	
	/**
	 * Get One VSkill By Tool_Name
	 */
	public VSkill getSkillByTool(String toolName) throws Exception{

		return (VSkill) sqlMapClient.queryForObject("vskill.getSkillByTool", toolName);
	}
	
	/**
	 * Get One Concept By Category_Name
	 */
	@Override
	public List<VSkill> getConceptByCategory(String categoryName) throws SQLException {
		
		return sqlMapClient.queryForList("vskill.getConceptByCategory", categoryName);
	}
	

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
