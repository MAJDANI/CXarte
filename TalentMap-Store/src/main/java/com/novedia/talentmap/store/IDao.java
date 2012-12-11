package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * The generic interface DAO.
 * @author j.marie-sainte
 *
 * @param <E> generic parameter
 */
public interface IDao<E> {

/**
 * Get element by Id.
 * @param id a id
 * @return <E>
 */
E get(Integer id) throws DataAccessException ;

/**
 * Get all Categories.
 * @return a list <E>
 */
List<E> getAll() throws DataAccessException ;

/**
 * Save a category.
 * @class ICategoryDao.java
 * @return id a id
 * @param element of type <E>
 */
int save(E element) throws DataAccessException ;

/**
 * Inserts element.
 * @param element of type <E>
 * @return id of the element inserted
 */
int add(E element) throws DataAccessException ;

/**
 * Delete element.
 * @param element of type <E>
 * @return id of the element deleted
 */
int delete(E element) throws DataAccessException ;

/**
 * Get the element by name.
 * @param name a name
 * @return name
 */
E getByName(String name) throws DataAccessException ;

/**
 * Check if the element exists.
 * @param name a name
 * @return the existing element or null
 */
E check(String name) throws DataAccessException ;
}