package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

public interface ICollaboratorService {

	List<Collaborator> getAllCollaborator() throws Exception;
	
	Collaborator getCollaborator(int id) throws Exception;
	
	void updateCollaborator(Collaborator collaborator) throws Exception;
}
