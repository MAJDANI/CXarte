package com.novedia.talentmap.web.ui.recherche;

import com.vaadin.Application;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class RechercheView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbsoluteLayout mainLayout;
	private HorizontalSplitPanel ecran;
	private Sidebar sidebar;
	private ZoneRecherche zonerecherche;
	
	//private VerticalLayout v;


	public void init() {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// ecran
		ecran = buildEcran();
		mainLayout.addComponent(ecran, "top:20.0px;right:-9.0px;");
		
	
			
		return mainLayout;
	}
	
	private HorizontalSplitPanel buildEcran() {
		// common part: create layout
		ecran = new HorizontalSplitPanel();
		ecran.setImmediate(false);
		ecran.setWidth("600px");
		ecran.setHeight("560px");
		ecran.setMargin(false);
		
		//Sidebar
		ecran.addComponent(sidebar);
		
		// Partie de recherche
		ecran.addComponent(zonerecherche);
		// verticalSplitPanel_1
//		verticalSplitPanel_1 = buildVerticalSplitPanel_1();
//		ecran.addComponent(verticalSplitPanel_1);
//		
		return ecran;
	}
}
