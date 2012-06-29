package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Concept;

/**
 * Interface Concept DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface IConceptDao {
	
	/**
	 * Get One Concept By Id
	 * @class IConceptDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Concept getById(int id) throws Exception;
	
	/**
	 * Select all Concepts
	 * @class IConceptDao.java
	 * @return
	 * @throws Exception
	 */
	List<Concept> selectAll() throws Exception;
	
	/**
	 * Select all Concepts By Category_ID
	 * @class IConceptDao.java
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	List<Concept> selectAllByCategoryId(int categoryId) throws Exception;
	
	/**
	 * Save one concept
	 * @class IConceptDao.java
	 * @throws Exception
	 */
	int saveOne(Concept concept) throws Exception;
	
	/**
	 * Check if the concept exists
	 * @class IConceptDao.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Concept checkConcept(String name, int category_id) throws Exception;
}
