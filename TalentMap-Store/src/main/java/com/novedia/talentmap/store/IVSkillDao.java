package com.novedia.talentmap.store;

import java.util.List;
import com.novedia.talentmap.model.entity.VSkill;

/**
 * Interface VSkill DAO.
 * @author j.collet
 */
public interface IVSkillDao {
/**
 * Select all VSkill By Category_Name and Concept_Name.
 * @class IVSkillDao.java
 * @param categoryName a category name
 * @param conceptName a concept name
 * @return a list of VSkill
 */
// TODO : Avec le rafectoring de TalentMap-Model la signature de la méthode change
// ATTENTION le type de retour n'est pas 
List<VSkill> getToolByConcept(String categoryName, String conceptName);

/**
 * Get One VSkill By Tool_Name.
 * @class IVSkillDao.java
 * @param toolName a tool name
 * @return a VSkill
 */
VSkill getSkillByTool(String toolName);

/**
 * Select all VSkill By Category_Name.
 * @class IVSkillDao.java
 * @param categoryName a {@link Category} name
 * @return a list of VSkill
 */
List<VSkill> getConceptByCategory(String categoryName);
}