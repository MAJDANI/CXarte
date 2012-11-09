package com.novedia.talentmap.store;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

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
	 * 
	 * @throws DataAccessException
	 */
	// TODO : Avec le rafectoring de TalentMap-Model la signature de la méthode change
	// ATTENTION le type de retour n'est pas 
	List<VSkill> getToolByConcept(String categoryName, String conceptName) throws DataAccessException;
	
	/**
	 * Get One VSkill By Tool_Name
	 * @class IVSkillDao.java
	 * @param toolName
	 * @return
	 * @throws DataAccessException
	 */
	VSkill getSkillByTool(String toolName) throws DataAccessException;
	
	/**
	 * Select all VSkill By Category_Name
	 * @class IVSkillDao.java
	 * @param categoryName
	 * @return
	 * @throws DataAccessException
	 */
	List<VSkill> getConceptByCategory(String categoryName) throws DataAccessException;
	
}
