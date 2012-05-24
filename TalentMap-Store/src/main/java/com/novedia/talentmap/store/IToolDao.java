package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Tool;

/**
 * Interface Tool DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface IToolDao {
	
	/**
	 * Get One Tool By Id
	 * @class IToolDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Tool getById(int id) throws Exception;
	
	/**
	 * Select all Tools
	 * @class IToolDao.java
	 * @return
	 * @throws Exception
	 */
	List<Tool> selectAll() throws Exception;
	
	/**
	 * Select all Tools By Concept_Id
	 * @class IToolDao.java
	 * @param conceptId
	 * @return
	 * @throws Exception
	 */
	List<Tool> selectAllByConceptId(int conceptId) throws Exception;
	
	/**
	 * Get One Tool By Name
	 * @class IToolDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Tool getByName(String name) throws Exception;
}
