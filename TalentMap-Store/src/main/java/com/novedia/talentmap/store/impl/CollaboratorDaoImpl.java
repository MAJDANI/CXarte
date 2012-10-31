package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IGenericDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;
/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CollaboratorDaoImpl extends SqlMapClientDaoSupport implements IGenericDao<Collaborator>{
	
	private SqlMapClient sqlMapClient;
	private int value ;
	
	/**
	 * Builder of a dummy collaborator if the database is down
	 * @class CollaboratorDao.java
	 * @param id
	 * @return
	 */
	private Collaborator buildDummyCollaborator(int id){
		
		//Dummy data (d). The connection with the database cannot establish.
		Collaborator collabFactice = new Collaborator();
		collabFactice.setId(id);
		collabFactice.setFirst_name("Julien");
		collabFactice.setLast_name("Collet");
		collabFactice.setPhone(000000000);
		collabFactice.setEmail("j.collet@novedia-solutions.com");
		collabFactice.setProfile_id(1);
		collabFactice.setBusiness_engineer("Ingenieur d'affaire");
		collabFactice.setEmployment_date(new Date());
		collabFactice.setManager_id(1);
		collabFactice.setExperience(5);
		
		return collabFactice;
	}
	
	/**
	 * Get One Collaborator By Id
	 */
	@Override
	public Collaborator getById(int id) throws DataAccessException {
		
		try {
			return (Collaborator)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_COLLABORATOR_REQUEST,id);
			
		} catch(NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyCollaborator(id);
		}
		
		
	}
	
	/**
	 * Select all Collaborators
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> selectAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR_REQUEST);
	}
	
	/**
	 * Update Collaborator's information
	 */
	@Override
	public int update(Collaborator collaborator) throws DataAccessException {
		
		try {
			this.sqlMapClient.startTransaction();
			value = this.getSqlMapClientTemplate().update(DBRequestsConstants.UPDATE_COLLABORATOR_REQUEST,collaborator);
			this.sqlMapClient.commitTransaction();
			
			this.sqlMapClient.endTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	 public CollaboratorDaoImpl(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Select all Collaborators by lastName
	 * @param lastName : a lastName
	 * @return all collaborators who has the last_name specified
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws DataAccessException {
		return (List<Collaborator>) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_ALL_COLLABORATOR_BYLASTNAME_REQUEST,lastName);		
	}
	
	/**
	 * Select all Collaborators for a list of collaborator's id
	 * @author v.guillemain
	 * @class ICollaboratorDao.java
	 * @param listId : a list of collaborator's id
	 * @return all collaborators who has an id specified
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> getAllCollaboratorsByListId(List<Integer> listId) throws DataAccessException {

		System.out.println("CDao ::::: 1 :::::  : entrée");
		System.out.println("CDao ::::: 2 :::::  : listId="+listId);
		
		List<Collaborator> listCollab = new ArrayList<Collaborator>(); 
		
		//if listId is empty calling the request will generate exception
		if (!listId.isEmpty()) {
			listCollab = this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR_BYLISTID_REQUEST,listId);
			System.out.println("CDao ::::: 3 :::::  : listCollab="+listCollab);
		}
		return listCollab;		
	}
	@Override
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId)
			throws DataAccessException, SQLException {
		return sqlMapClient.queryForList("manager.getAllCollaboratorsByManagerId", managerId);
	}

	@Override
	public int save(Collaborator object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collaborator checkCategory(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int object_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Concept> selectAllByCategoryId(int categoryId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collaborator checkConcept(String name, int category_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Collaborator object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Collaborator> getByCollabId(int collabId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Collaborator object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collaborator getByType(String type) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborator> selectAllByConceptId(int conceptId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collaborator getByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tool checkTool(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}