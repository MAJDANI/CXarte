package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Client;

/**
 * Client Service Interface.
 * 
 * @author y.rohr TODO : Must create a data referential interface
 */
public interface IClientService {

    /**
     * Get all Clients.
     * 
     * @return List<Client>
     */
    List<Client> getAllClients();

}