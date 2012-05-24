package com.novedia.talentmap.store.impl;

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
	 * Get One Collaborator By Id
	 */
	@Override
	public Collaborator getById(int id) throws Exception {
		
		return (Collaborator)sqlMapClient.queryForObject("collaborator.getCollaborator", id);
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
	
}
