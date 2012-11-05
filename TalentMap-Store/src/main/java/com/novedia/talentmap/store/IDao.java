package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * The generic interface DAO.
 * 
 * @author j.marie-sainte
 *
 * @param <E>
 */
public interface IDao<E> {
	
	
	/**
	 * Get element by Id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	E get(E element) throws DataAccessException;
	
	/**
	 * Get all Categories
	 * @return
	 * @throws DataAccessException
	 */
	List<E> getAll() throws DataAccessException;
	
	/**
	 * Save a category
	 * @class ICategoryDao.java
	 * @throws DataAccessException
	 */
	int save(E element) throws DataAccessException;
	
	/**
	 * Inserts element
	 * 
	 * @param mission : the mission to be inserted
	 * @return id of the element inserted
	 * @throws DataAccessException
	 */
	public int add(E element) throws DataAccessException;
		
	/**
	 * Delete element
	 * 
	 * @param element
	 * @return id of the element deleted
	 * @throws DataAccessException
	 */
	int delete(E element) throws DataAccessException;
	
	/**
	 * Check if the element exists 
	 * @param name
	 * @return the existing element or null
	 * @throws Exception
	 */
	E check(String name) throws DataAccessException;
}