package com.novedia.talentmap.store;

import java.sql.SQLException;
import java.util.List;

import com.novedia.talentmap.model.entity.VSkill;

/**
 * Interface VSkill DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface IVSkillDao {
	
	/**
	 * Select all VSkill By Category_Name and Concept_Name
	 * @class IVSkillDao.java
	 * @param categoryName
	 * @param conceptName
	 * @return
	 * @throws SQLException
	 */
	List<VSkill> getToolByConcept(String categoryName, String conceptName) throws SQLException;
	
	/**
	 * Get One VSkill By Tool_Name
	 * @class IVSkillDao.java
	 * @param toolName
	 * @return
	 * @throws Exception
	 */
	VSkill getSkillByTool(String toolName) throws Exception;
	
	/**
	 * Select all VSkill By Category_Name
	 * @class IVSkillDao.java
	 * @param categoryName
	 * @return
	 * @throws SQLException
	 */
	List<VSkill> getConceptByCategory(String categoryName) throws SQLException;
	
}
