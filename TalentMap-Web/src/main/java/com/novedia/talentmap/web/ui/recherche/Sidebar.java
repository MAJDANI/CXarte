package com.novedia.talentmap.web.ui.recherche;

import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public class Sidebar extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout v;
	private Link rech_client;
	private Link rech_comp;
	private Link rech_nom;
	
	public Sidebar(){
		
		this.setImmediate(false);
		this.setWidth("180px");
		this.setHeight("120px");
		this.setMargin(false);
		buildSidebar();
	}
	
	private VerticalLayout buildSidebar(){
		
				// rech_nom
				rech_nom = new Link();
				rech_nom.setCaption("Recherche par nom");
				rech_nom.setImmediate(false);
				rech_nom.setWidth("-1px");
				rech_nom.setHeight("-1px");
				this.addComponent(rech_nom);
				
				// rech_comp
				rech_comp = new Link();
				rech_comp.setCaption("Recherche par compétences");
				rech_comp.setImmediate(false);
				rech_comp.setWidth("-1px");
				rech_comp.setHeight("-1px");
				this.addComponent(rech_comp);
				
				// rech_client
				rech_client = new Link();
				rech_client.setCaption("Recherche par client");
				rech_client.setImmediate(false);
				rech_client.setWidth("-1px");
				rech_client.setHeight("-1px");
				this.addComponent(rech_client);
				
		return this;
		
	}

}
