package com.novedia.talentmap.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.services.impl.ClientService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
@RequestMapping(value = "/clients/")
public class ClientController extends TalentMapRestHandlerException {
	
	@Autowired
	private ClientService clientService;

	/**
	 * Get all Client
	 * @return a list of client
	 */
	@RequestMapping
	@ResponseBody
	public List<Client> getAll(){
		List<Client> clients = null;
		clients = clientService.getAllClients();
		return clients;
		
	}
	
	
	
	
}