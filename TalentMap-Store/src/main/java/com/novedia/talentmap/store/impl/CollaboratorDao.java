package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.ICollaboratorDao;
/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CollaboratorDao  implements ICollaboratorDao{
	
	private SqlMapClient sqlMapClient;
	
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
	public Collaborator getById(int id) {
		
		try {
			
			return (Collaborator)sqlMapClient.queryForObject("collaborator.getCollaborator", id);
//			return buildDummyCollaborator(id);
			
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildDummyCollaborator(id);
//			
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
		
		return sqlMapClient.queryForList("collaborator.getAllCollaborator");
	}
	
	/**
	 * Update Collaborator's information
	 */
	@Override
	public int update(Collaborator collaborator) throws Exception {
		
		this.sqlMapClient.startTransaction();
		
		int value = this.sqlMapClient.update("collaborator.updateCollaborateur", collaborator);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return value;
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
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
		
		return sqlMapClient.queryForList("collaborator.getAllCollaboratorByLastName", lastName);
		
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
			listCollab = sqlMapClient.queryForList("collaborator.getAllCollaboratorsByListId", listId);
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
