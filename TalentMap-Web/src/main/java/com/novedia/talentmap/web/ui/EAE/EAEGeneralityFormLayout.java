package com.novedia.talentmap.web.ui.EAE;

import java.util.Vector;

import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.EAEGeneralityFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class EAEGeneralityFormLayout extends FormLayout implements
	ClickListener, IObservable {

    /**
     * Declare the observer. The observer will be used when an action in
     * AddMissionPanel will require update data in ListPanel
     */
    private IMissionCollaboratorContent obs;

    /**
     */
    private Integer eaeId;
    private IEAEService eaeService;
    private EAEGeneralityDTO eaeGeneralityDTO;

    /**
     * POJO
     */
    private Vector<Object> fieldOrderEAEGenerality;

    /**
     * Vaadin Components
     */
    private Form eaeGeneralityForm;
    private Button save;

    public static final String SAVE_BUTTON_NAME = "Save";

    private Authentication authentication;

    /**
     * Default constructor
     */
    public EAEGeneralityFormLayout() {
	super();
    }

    /**
     * Build the form mission of colleague
     * 
     * @return
     */
    public EAEGeneralityFormLayout build(Integer eaeId) {
	this.eaeId = eaeId;
	removeAllComponents();
	buildMain();
	return this;
    }

    public void buildMain() {

	try {
	    eaeGeneralityForm.removeAllProperties();
	    buildEAEGeneralityLayout();
	    buildEAEGeneralityForm();
	    buildButton();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void buildEAEGeneralityLayout() {
	GridLayout eaeGeneralityFormLayout = new GridLayout();
	eaeGeneralityFormLayout.setMargin(true);
	eaeGeneralityFormLayout.setSpacing(true);
	eaeGeneralityFormLayout.setColumns(1);
	eaeGeneralityFormLayout.setRows(6);
	eaeGeneralityForm.setLayout(eaeGeneralityFormLayout);
    }

    public void buildEAEGeneralityForm() throws Exception {
	CUtils.setOrderForm(this.fieldOrderEAEGenerality,
		ConstantsEnglish.FIELD_ORDER_EAE_GENERALITY);
	this.eaeGeneralityForm
		.setFormFieldFactory(new EAEGeneralityFormFieldFactory());
	this.eaeGeneralityDTO = this.eaeService.getEAEGenerality(eaeId);

	BeanItem<Item> eaeGeneralityBean = new BeanItem(eaeGeneralityDTO);
	this.eaeGeneralityForm.setItemDataSource(eaeGeneralityBean,
		this.fieldOrderEAEGenerality);
	this.eaeGeneralityForm.setImmediate(true);
	this.eaeGeneralityForm.setVisible(true);
//	this.eaeGeneralityForm.setReadOnly(true);
	addComponent(this.eaeGeneralityForm);
    }

    public void buildButton() {

	this.save.setCaption(SAVE_BUTTON_NAME);
	this.save.addListener(this);
	HorizontalLayout hLayout = new HorizontalLayout();
	hLayout.setMargin(true);
	hLayout.setSpacing(true);
	hLayout.addComponent(this.save);
	this.eaeGeneralityForm.setFooter(hLayout);
    }

    @Override
    public void buttonClick(ClickEvent event) {

	Button button = event.getButton();
	if (this.save == button) {

	}

    }

    /**
     */
    private int validatedEaeGeneralityForm() {
	return 0;
    }

    /**
     * Check null values
     * 
     * @param value
     * @return false if the parameter value is null
     */
    private boolean isNotEmpty(Object value) {
	if (value == null || value.toString() == "") {
	    return false;
	} else {
	    return true;
	}
    }

    @Override
    public void addObservateur(Object observateur, Class<?> cl) {
	//
    }

    public void updateObservateur(String currentAction) {
	//
    }

    @Override
    public void updateObservateur() {
	//
    }

    @Override
    public void delObservateur() {
	this.obs = null;
    }

    /**
     * Set the save value
     * 
     * @param save
     *            the save to set
     */
    public void setSave(Button save) {
	this.save = save;
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * @return the eaeService
     */
    public IEAEService getEaeService() {
        return eaeService;
    }

    /**
     * @param eaeService the eaeService to set
     */
    public void setEaeService(IEAEService eaeService) {
        this.eaeService = eaeService;
    }

    /**
     * @return the eaeGeneralityDTO
     */
    public EAEGeneralityDTO getEaeGeneralityDTO() {
        return eaeGeneralityDTO;
    }

    /**
     * @param eaeGeneralityDTO the eaeGeneralityDTO to set
     */
    public void setEaeGeneralityDTO(EAEGeneralityDTO eaeGeneralityDTO) {
        this.eaeGeneralityDTO = eaeGeneralityDTO;
    }

    /**
     * @return the fieldOrderEAEGenerality
     */
    public Vector<Object> getFieldOrderEAEGenerality() {
        return fieldOrderEAEGenerality;
    }

    /**
     * @param fieldOrderEAEGenerality the fieldOrderEAEGenerality to set
     */
    public void setFieldOrderEAEGenerality(Vector<Object> fieldOrderEAEGenerality) {
        this.fieldOrderEAEGenerality = fieldOrderEAEGenerality;
    }

    /**
     * @return the eaeGeneralityForm
     */
    public Form getEaeGeneralityForm() {
        return eaeGeneralityForm;
    }

    /**
     * @param eaeGeneralityForm the eaeGeneralityForm to set
     */
    public void setEaeGeneralityForm(Form eaeGeneralityForm) {
        this.eaeGeneralityForm = eaeGeneralityForm;
    }

}
