package com.novedia.talentmap.store.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.ICollaboratorDao;

public class CollaboratorDao implements ICollaboratorDao {
	
	protected SqlMapClient sqlMap;
	
	/**
	 * Set the sqlMap value
	 * @param sqlMap the sqlMap to set
	 */
	public void setSqlMap(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}


	public Collaborator getCollaborator(int id) throws Exception {
		
		return (Collaborator)sqlMap.queryForObject("collaborator.getCollaborator", id);
	}
	
	public String Test(){
		return "test";
	}
	
}
