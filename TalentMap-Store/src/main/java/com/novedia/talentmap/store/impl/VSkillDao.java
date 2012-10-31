package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.store.IVSkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class VSkillDao extends SqlMapClientDaoSupport implements IVSkillDao {
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Builder of a list of dummy tool
	 * @class VSkillDao.java
	 * @param categoryName
	 * @param conceptName
	 * @return
	 */
	private List<VSkill> buildListDummyToolByConcept(String categoryName, String conceptName){
		
		List<VSkill> lVSkill = new ArrayList<VSkill>();
		
		VSkill vsk1 = new VSkill();
		vsk1.setCategory_name(categoryName);
		vsk1.setConcept_name(conceptName);
		vsk1.setTool_name("Spring");
		
		VSkill vsk2 = new VSkill();
		vsk2.setCategory_name(categoryName);
		vsk2.setConcept_name(conceptName);
		vsk2.setTool_name("outil2");
		
		lVSkill.add(vsk1);
		lVSkill.add(vsk2);
		
		return lVSkill;
	}
	
	/**$
	 * Builder of a list of dummy concept
	 * @class VSkillDao.java
	 * @param categoryName
	 * @return
	 */
	private List<VSkill> buildListDummyConceptByCategory(String categoryName){
		
		List<VSkill> lVSkill = new ArrayList<VSkill>();
		
		VSkill vsk1 = new VSkill();
		vsk1.setCategory_name(categoryName);
		vsk1.setConcept_name("IOC");
		vsk1.setTool_name("Spring");
		
		VSkill vsk2 = new VSkill();
		vsk2.setCategory_name(categoryName);
		vsk2.setConcept_name("Concept2");
		vsk2.setTool_name("outil2");
		
		lVSkill.add(vsk1);
		lVSkill.add(vsk2);
		
		return lVSkill;
	}
	
	private VSkill buildDummySkill(String toolName){
		
		VSkill vSkill = new VSkill();
		vSkill.setCategory_name("JAVA");
		vSkill.setConcept_name("IOC");
		vSkill.setTool_name(toolName);
		
		return vSkill;
	}
	
	/**
	 * Select all Tools By Concept_Name and the Category_Name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VSkill> getToolByConcept(String categoryName, String conceptName) throws SQLException {
		
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("categoryName", categoryName);
		mapName.put("conceptName", conceptName);
		
		try {
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_TOOL_BY_CONCEPT, mapName);
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildListDummyToolByConcept(categoryName, conceptName);
		}
	}
	
	/**
	 * Get One VSkill By Tool_Name
	 */
	public VSkill getSkillByTool(String toolName) throws SQLException {

		try {
			return (VSkill) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_SKILL_BY_TOOL,toolName);			
		} catch(NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummySkill(toolName);
		}
	}
	
	/**
	 * Get One Concept By Category_Name
	 */
	@Override
	public List<VSkill> getConceptByCategory(String categoryName) throws SQLException {
		
		try {
			// GET_CONCEPT_BY_CATEGORY 
			//vskill.getConceptByCategory
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_CONCEPT_BY_CATEGORY , categoryName);
			
		} catch(NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildListDummyConceptByCategory(categoryName);
		}
	}
	

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public VSkillDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

}
