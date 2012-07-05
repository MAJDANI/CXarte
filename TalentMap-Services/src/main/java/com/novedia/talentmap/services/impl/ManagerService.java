package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.store.IManagerDao;

public class ManagerService implements IManagerService {

	private IManagerDao managerDao;

	@Override
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId)
			throws Exception {
		return managerDao.getAllCollaboratorsByManagerId(managerId);
	}



}
