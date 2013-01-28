package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Client;

/**
 * Client Service Interface.
 * 
 * @author y.rohr
 */
public interface IClientService {
	
	/**
	 * Get all Clients.
	 * @return List<Client>
	 */
	List<Client> getAllClients();
	
}