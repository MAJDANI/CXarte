package com.novedia.talentmap.store;

import com.novedia.talentmap.model.entity.Collaborator;

public interface ICollaboratorDao {
	
	Collaborator getCollaborator(int id) throws Exception; 
	String Test();
	
}
