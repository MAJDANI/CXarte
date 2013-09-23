package com.novedia.talentmap.store;

import java.util.List;
import com.novedia.talentmap.model.entity.VSkill;

/**
 * Interface VSkill DAO.
 * 
 * @author j.collet
 */
public interface IVSkillDao {

	/**
	 * Get One VSkill By Tool_Name.
	 * 
	 * @class IVSkillDao.java
	 * @param toolName
	 *            a tool name
	 * @return a VSkill
	 */
	VSkill getSkillByTool(String toolName);

}