package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.ICollaboratorDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;
/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CollaboratorDao extends SqlMapClientDaoSupport implements ICollaboratorDao{
	
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
	public Collaborator getById(int id) throws SQLException {
		
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
	public List<Collaborator> selectAll() throws Exception {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR_REQUEST);
	}
	
	/**
	 * Update Collaborator's information
	 */
	@Override
	public int update(Collaborator collaborator) throws Exception {
		
		this.sqlMapClient.startTransaction();
		value = this.getSqlMapClientTemplate().update(DBRequestsConstants.UPDATE_COLLABORATOR_REQUEST,collaborator);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return value;
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	 public CollaboratorDao(final SqlMapClient sqlMapClient) {
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
	public List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws Exception {
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
	public List<Collaborator> getAllCollaboratorsByListId(List<Integer> listId) throws Exception {

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
	
	// METHODE QUI FONCTIONNE
//	public List<Collaborator> getAllCollaboratorsByListId2(Integer toolId, List<Integer> listId) throws Exception {
//
//		System.out.println("CDao !!!!! 1 !!!!! entrée");
//		System.out.println("CDao !!!!! 2 !!!!!  : listId="+listId);
//		
//		SkillParameter skillParameter = new SkillParameter();
//		skillParameter.setToolId(toolId);
//		skillParameter.setListCollaborators(listId);
//		
//		System.out.println("CDao !!!!! 3 !!!!! skillParameter.getToolId="+skillParameter.getToolId());
//		System.out.println("CDao !!!!! 3 !!!!! skillParameter.getListCollaborators="+skillParameter.getListCollaborators());
//
//		return sqlMapClient.queryForList("collaborator.getAllCollaboratorsByListId2", skillParameter);
//		
//	}

}
