package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Category;

/**
 * Interface Category DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface ICategoryDao {
	
	/**
	 * Get One Category By Id
	 * @class ICategoryDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Category getById(int id) throws Exception;
	
	/**
	 * Select all Categories
	 * @class ICategoryDao.java
	 * @return
	 * @throws Exception
	 */
	List<Category> selectAll() throws Exception;
	
}
