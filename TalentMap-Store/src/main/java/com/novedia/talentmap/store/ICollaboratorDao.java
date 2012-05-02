package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

public interface ICollaboratorDao {
	
	Collaborator getById(int id) throws Exception;
	
	List<Collaborator> selectAll() throws Exception;
	
	void update(Collaborator collaborator) throws Exception;
	
}
