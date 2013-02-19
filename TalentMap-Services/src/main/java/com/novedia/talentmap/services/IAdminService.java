package com.novedia.talentmap.services;
import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;

/**
 * The Administration Service Interface.
 * 
 * @author j.marie-sainte
 *
 */
public interface IAdminService {

/**
* Gets all tools.
* @return a list of tools.
*/
List<Tool> getAllTools();

/**
* Get all concepts.
* @class IAdminService.java
* @return a list  of Concepts
*/
List<Concept> getAllConcepts();

/**
* Get all categories.
* @class IAdminService.java
* @return a list of Categories
* @throws DataAccessException
*/
List<Category> getAllCategories();

/**
* Add one skill.
* @class IAdminService.java
* @param skill a skill
* @return Return an error message callback
*/
Map<String, Object> addSkill(VSkill skill);

/**
 * Get all concepts available for a category
 * @param category
 * @return List of concept
 */
List<Concept> getAllConceptByCategory(Category category);

/**
 * Add one tool
 * @param tool
 * @return
 */
Integer addTool(Tool tool);

/**
 * Add one Category
 * @param category
 * @return
 */
Integer addCategory(Category category);

/**
 * Add one Concept
 * @param concept
 * @return
 */
Integer addConcept(Concept concept);

/**
* Update one skill.
* @class IAdminService.java. 'category'
* @param cat a category
* @param concept a concept
* @param tool a tool
* @return Return a map
*/
Map<String, Object> updateASkill(Category cat, Concept concept, Tool tool);

/**
*Delete one category (and his concepts and tools).
*@class IAdminService.java
*@param categoryId a id
*@return a map
*@throws Exception if error occurs
*/
Map<String, Object> deleteCategory(int categoryId) throws Exception;

/**
 * Delete one concept (and his tools).
 * @class IAdminService.java
 * @param conceptId A id
 * @return Une map avec comme cle l'id, et valeur les données de Admin
 * @throws DataAccessException
 */
Map<String, Object> deleteConcept(int conceptId);

/**
 * Delete one tool.
 * @class IAdminService.java
 * @param toolId a id
 * @return a map a object
 */
Map<String, Object> deleteTool(int toolId);
}
