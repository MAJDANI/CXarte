package com.novedia.talentmap.store.generiques;

import java.util.List;

import org.springframework.dao.DataAccessException;

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
	int save(E category) throws DataAccessException;
	
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
	int update(E category) throws DataAccessException;
	
	/**
	 * Delete one category
	 * @class ICategoryDao.java
	 * @param category
	 * @return
	 * @throws Exception
	 */
	int delete(int category_id) throws DataAccessException;
}