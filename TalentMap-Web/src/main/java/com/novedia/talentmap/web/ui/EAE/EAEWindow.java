package com.novedia.talentmap.web.ui.EAE;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EAEWindow extends Window {

    private EAEWindowGeneralityView generalityView;
    private EAEForSynthesisDTO eaeForSynthesisDTO;
    private TabSheet tabSheet;
    private Integer eaeId;
    private VerticalLayout vLayout;
    
    /**
     * The main builder
     * 
     * @class ProfileCollabWindow.java
     */
    public void build(EAEForSynthesisDTO eae) {
	this.eaeForSynthesisDTO = eae;
	
	setModal(true);
	center();
	this.setWidth("1135");
	removeAllComponents();
	buildCaption();
	buildEAEWindow();
    }


    /**
     * Builds the caption of the window with the Name of colleague concerned by
     * the EAE and the date of the EAE
     * 
     * @return
     */
    private void buildCaption() {
	String caption = ConstantsEnglish.EAE_WINDOW_TITLE_1_COLLEAGUE
		+ eaeForSynthesisDTO.getLastName() + " "
		+ eaeForSynthesisDTO.getFirstName()
		+ ConstantsEnglish.EAE_WINDOW_TITLE_2_DATE
		+ eaeForSynthesisDTO.getDateEae();
	this.setCaption(caption);
    }

    private void buildEAEWindow() {
//	vLayout = new VerticalLayout();
//	vLayout.addComponent(new Label("EmploymentDate"));
//	vLayout.addComponent(new Label(this.eaeGeneralityDTO
//		.getEmploymentDate()));
//	this.addComponent(vLayout);

	tabSheet = new TabSheet();
	this.eaeId = eaeForSynthesisDTO.getId();
	generalityView = generalityView.build(eaeId);
	generalityView.setVisible(true);
	tabSheet.addTab(generalityView, "Generality");
	
	this.addComponent(tabSheet);
    }

    /**
     * @return the eaeId
     */
    public Integer getEaeId() {
	return eaeId;
    }

    /**
     * @param eaeId
     *            the eaeId to set
     */
    public void setEaeId(Integer eaeId) {
	this.eaeId = eaeId;
    }


    /**
     * @return the eaeForSynthesisDTO
     */
    public EAEForSynthesisDTO getEaeForSynthesisDTO() {
        return eaeForSynthesisDTO;
    }


    /**
     * @param eaeForSynthesisDTO the eaeForSynthesisDTO to set
     */
    public void setEaeForSynthesisDTO(EAEForSynthesisDTO eaeForSynthesisDTO) {
        this.eaeForSynthesisDTO = eaeForSynthesisDTO;
    }


    /**
     * @return the generalityView
     */
    public EAEWindowGeneralityView getGeneralityView() {
        return generalityView;
    }


    /**
     * @param generalityView the generalityView to set
     */
    public void setGeneralityView(EAEWindowGeneralityView generalityView) {
        this.generalityView = generalityView;
    }

}
