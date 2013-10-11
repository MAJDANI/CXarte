package com.novedia.talentmap.web.ui.EAE;

import com.vaadin.ui.VerticalLayout;

public class EAEWindowGeneralityView extends VerticalLayout {
    
    private Integer eaeId;
//    private EAEGeneralityDTO eaeGeneralityDTO;
//    private IEAEService eaeService;
    private EAEGeneralityFormLayout eaeGeneralityFormLayout;

    /**
     * Default constructor
     */
    public EAEWindowGeneralityView() {
	super();
    }

    public EAEWindowGeneralityView build(Integer eaeId) {
        this.eaeId = eaeId;
	removeAllComponents();
	eaeGeneralityFormLayout.build(eaeId);
	eaeGeneralityFormLayout.setVisible(true);
	addComponent(eaeGeneralityFormLayout);
//	retrieveEaeGeneralityDTO();
	return this;
    }
    
    
    /**
     * @return the eaeId
     */
    public Integer getEaeId() {
        return eaeId;
    }

    /**
     * @param eaeId the eaeId to set
     */
    public void setEaeId(Integer eaeId) {
        this.eaeId = eaeId;
    }

    /**
     * @return the eaeGeneralityFormLayout
     */
    public EAEGeneralityFormLayout getEaeGeneralityFormLayout() {
        return eaeGeneralityFormLayout;
    }

    /**
     * @param eaeGeneralityFormLayout the eaeGeneralityFormLayout to set
     */
    public void setEaeGeneralityFormLayout(
    	EAEGeneralityFormLayout eaeGeneralityFormLayout) {
        this.eaeGeneralityFormLayout = eaeGeneralityFormLayout;
    }


}
