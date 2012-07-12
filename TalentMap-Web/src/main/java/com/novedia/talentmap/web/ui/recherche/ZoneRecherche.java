package com.novedia.talentmap.web.ui.recherche;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalSplitPanel;

public class ZoneRecherche extends VerticalSplitPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HorizontalLayout rech;
	private Button button_ok;
	private ComboBox liste_noms;
	private Label nom;
	
	
	public ZoneRecherche(){
		this.setImmediate(false);
		this.setDescription("Nom");
		this.setWidth("347px");
		this.setHeight("557px");
		this.setMargin(false);
		this.addComponent(partieRecherche());
	}
	
	private HorizontalLayout partieRecherche(){
	
	// nom
	nom = new Label();
	nom.setImmediate(false);
	nom.setWidth("-1px");
	nom.setHeight("-1px");
	nom.setValue("Nom");
	rech.addComponent(nom);
	rech.setComponentAlignment(nom, new Alignment(48));
	
	// liste_noms
	liste_noms = new ComboBox();
	liste_noms.setImmediate(false);
	liste_noms.setWidth("-1px");
	liste_noms.setHeight("-1px");
	rech.addComponent(liste_noms);
	rech.setComponentAlignment(liste_noms, new Alignment(33));
	
	// button_ok
	button_ok = new Button();
	button_ok.setCaption("OK");
	button_ok.setImmediate(true);
	button_ok.setWidth("-1px");
	button_ok.setHeight("-1px");
	rech.addComponent(button_ok);
	rech.setComponentAlignment(button_ok, new Alignment(33));
	
	return rech;
	
	}
//		
//		// rech
//				rech = buildrech();
//				verticalSplitPanel_1.addComponent(rech);
//				
//				// table_1
//				table_1 = new Table();
//				table_1.setImmediate(false);
//				table_1.setWidth("-1px");
//				table_1.setHeight("-1px");
//				verticalSplitPanel_1.addComponent(table_1);
//				
//				return verticalSplitPanel_1;
//			
	

}
