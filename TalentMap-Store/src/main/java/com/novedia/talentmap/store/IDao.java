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
E get(Integer id);

/**
 * Get all Categories.
 * @return a list <E>
 */
List<E> getAll();

/**
 * Save a category.
 * @class ICategoryDao.java
 * @return id a id
 * @param element of type <E>
 */
int save(E element);

/**
 * Inserts element.
 * @param element of type <E>
 * @return id of the element inserted
 */
int add(E element);

/**
 * Delete element.
 * @param element of type <E>
 * @return id of the element deleted
 */
int delete(E element);

/**
 * Get the element by name.
 * @param name a name
 * @return name
 */
E getByName(String name);

/**
 * Check if the element exists.
 * @param name a name
 * @return the existing element or null
 */
E check(String name);
}