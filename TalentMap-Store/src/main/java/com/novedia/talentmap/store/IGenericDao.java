package com.novedia.talentmap.store;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Tool;

public interface IGenericDao<E> {
	
	
	/**
	 * Get One Category By Id
	 * @class ICategoryDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	E getById(int id) throws DataAccessException;
	
	/**
	 * Select all Categories
	 * @class ICategoryDao.java
	 * @return
	 * @throws Exception
	 */
	List<E> selectAll() throws DataAccessException;
	
	/**
	 * Save One Category
	 * @class ICategoryDao.java
	 * @throws Exception
	 */
	int save(E object) throws DataAccessException;
	/**
	 * Inserts one Mission
	 * @class IMissionDao.java
	 * @param mission : the mission to be inserted
	 * @return int : id of the mission inserted (id generated)
	 * @throws Exception
	 */
	public int insert(E object) throws Exception;
	
	/**
	 * Check if the Category exists 
	 * @class ICategoryDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	E checkCategory(String name) throws DataAccessException;
	
	/**
	 * Update one category
	 * @class ICategoryDao.java
	 * @param category
	 * @return
	 * @throws Exception
	 */
	int update(E object) throws DataAccessException;
	
	/**
	 * Delete one category
	 * @class ICategoryDao.java
	 * @param category
	 * @return
	 * @throws Exception
	 */
	int delete(int object_id) throws DataAccessException;
	/**
	 * Select all Collaborators by lastName
	 * @author v.guillemain
	 * @class ICollaboratorDao.java
	 * @param lastName : a lastName
	 * @return all collaborators who has the lastName specified
	 * @throws Exception
	 */
	List<E> getAllCollaboratorsByLastName(String lastName) throws DataAccessException;

	
	/**
	 * Select all Collaborators for a list of collaborator's id
	 * @author v.guillemain
	 * @class ICollaboratorDao.java
	 * @param listId : a list of collaborator's id
	 * @return all collaborators who has an id specified
	 * @throws Exception
	 */
	List<E> getAllCollaboratorsByListId(List<Integer> listId) throws DataAccessException;
	/**
	 * Select all Concepts By Category_ID
	 * @class IConceptDao.java
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	List<Concept> selectAllByCategoryId(int categoryId) throws DataAccessException;
	/**
	 * Check if the concept exists
	 * @class IConceptDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	E checkConcept(String name, int category_id) throws DataAccessException;
	/**
	 * Select all Collaborators by managerId
	 * @param managerId : the id of the manager
	 * @return all collaborators who depend on the manager managerId
	 * @author v.guillemain
	 */
	List<E> getAllCollaboratorsByManagerId(Integer managerId) throws DataAccessException,SQLException;
	/**
	 * Get all Collaborators's Missions By Collab_ID
	 * @class IMissionDao.java
	 * @param collab_id : the collaborator's id
	 * @return List<Mission>
	 * @throws Exception
	 */
	List<E> getByCollabId(int collabId) throws DataAccessException;
	/**
	 * Adding a mission in database
	 * @deprecated : use insert(Mission mission)
	 * @class IMissionDao.java
	 * @param mission
	 * @return int TODO
	 * @throws Exception
	 */
	int add(E object) throws DataAccessException;
	/**
	 * Get One Profile By Type
	 * @class IProfileDao.java
	 * @param type
	 * @return
	 * @throws Exception
	 */
	E getByType(String type) throws DataAccessException;
	/**
	 * Select all Tools By Concept_Id
	 * @class IToolDao.java
	 * @param conceptId
	 * @return
	 * @throws Exception
	 */
	List<E> selectAllByConceptId(int conceptId) throws Exception;
	/**
	 * Get One Tool By Name
	 * @class IToolDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	E getByName(String name) throws Exception;	
	/**
	 * Check if the tool exists
	 * @class IToolDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Tool checkTool(String name) throws Exception;
}