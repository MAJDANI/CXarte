package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchByCustomerForm extends VerticalLayout {
	
	/**
	 * TalentMap services
	 */
	
	private IClientService clientService;
	
	/**
	 * Vaadin components
	 */
	
	private ComboBox customerField;
	
	
	 /**
     * Constructeur par défaut
     */
    public SearchByCustomerForm() {
    	super();
    }
    
    /**
     * Build SearchByCustomerForm view
     * 
     * @return
     */
    public SearchByCustomerForm buildSearchByCustomerFormView() {
    	removeAllComponents();
    	buildMain();
    	return this;
    }

	private void buildMain() {
		setMargin(true);
		setSpacing(true);
		buildField();
		buildClientList();
	}

	private void buildField() {
		customerField.setCaption(Constants.CUSTOMER_NAME_LABEL);
		customerField.setImmediate(true);
		addComponent(customerField);
		
	}
	
	private void buildClientList() {
		BeanItemContainer<Client> container = new BeanItemContainer<Client>(Client.class);
		for (Client client : clientService.getAllClients()) {
			container.addItem(client);
		}
		customerField.setContainerDataSource(container);
		customerField.setItemCaptionPropertyId("name");
	}
	
	public ComboBox getCustomerField() {
		return customerField;
	}

	public void setCustomerField(ComboBox customerField) {
		this.customerField = customerField;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

}
