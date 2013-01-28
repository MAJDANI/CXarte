package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.store.IDao;

/**
 * The client Service implementation.
 * 
 * @author y.rohr
 */
public class ClientService implements IClientService {

	/**
	 * The client DAO.
	 */
	private IDao<Client> clientDao;
	
	@Override
	public List<Client> getAllClients() {
		return clientDao.getAll();
	}

	public IDao<Client> getClientDao() {
		return clientDao;
	}

	public void setClientDao(IDao<Client> clientDao) {
		this.clientDao = clientDao;
	}
	
}