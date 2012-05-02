package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.ICollaboratorDao;

public class CollaboratorDao  implements ICollaboratorDao{
	
	private SqlMapClient sqlMapClient;
	
	@Override
	public Collaborator getById(int id) throws Exception {
		
		return (Collaborator)sqlMapClient.queryForObject("collaborator.getCollaborator", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("collaborator.getAllCollaborator");
	}

	@Override
	public void update(Collaborator collaborator) throws Exception {
		
		this.sqlMapClient.update("collaborator.update", collaborator);
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
}
