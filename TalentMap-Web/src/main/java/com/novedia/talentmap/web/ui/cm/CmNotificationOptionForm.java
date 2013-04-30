package com.novedia.talentmap.web.ui.cm;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CmOption;
import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.CmOptionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;

public class CmNotificationOptionForm extends FormLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8506054835155389774L;
	
	/**
	 * Vaadin components
	 */
	private Form cmOptionForm;
	private GridLayout cmOptionFormLayout;
	
	/**
	 * POJO
	 */
	private Vector<Object> fieldOrderOption;
	
	/**
	 * TalentMap service
	 */
	private INotificationService notificationService;
	
	private Authentication authentication;
	
	/**
	 * Default constructor
	 */
	public CmNotificationOptionForm(){
		super();
	}
	
	/**
	 * Build the cm option Form View 
	 * @return RegistrationForm object
	 */
	public CmNotificationOptionForm buildCmOptionFormView(){
		cmOptionForm.removeAllProperties();
		removeAllComponents();
		buildMain();
		return this;
	}
	
	public void buildMain() {

		try {
			buildCmOptionLayout();
			buildCmOptionForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildCmOptionLayout(){
		this.cmOptionFormLayout.setMargin(true);
		this.cmOptionFormLayout.setSpacing(true);
		this.cmOptionFormLayout.setColumns(2);
		this.cmOptionFormLayout.setRows(8);
	}
	
	public void buildCmOptionForm(){
		
		this.cmOptionForm.setLayout(this.cmOptionFormLayout);
		
		this.fieldOrderOption = new Vector<Object>(ConstantsEnglish.FIELD_ORDER_CM_OPTIONS.length);
		CUtils.setOrderForm(this.fieldOrderOption, ConstantsEnglish.FIELD_ORDER_CM_OPTIONS);
		
		this.cmOptionForm.setFormFieldFactory(new CmOptionFormFieldFactory(this.notificationService));
		Frequency defaultFrequency = null;
		Frequency f = notificationService.getCmFrequencyOption(authentication.getColleagueId());
		if(f == null){
			defaultFrequency = Frequency.builder().id(1).build() ;
		}
		else{
			defaultFrequency = f;
		}
		CmOption defaultCmOption = CmOption.builder().frequency(defaultFrequency).build();
		BeanItem<CmOption> cmOptionBean = new BeanItem<CmOption>(defaultCmOption);
		this.cmOptionForm.setItemDataSource(cmOptionBean, this.fieldOrderOption);
		
		this.cmOptionForm.setImmediate(true);
		
		addComponent(this.cmOptionForm);
	}

	public Form getCmOptionForm() {
		return cmOptionForm;
	}

	public void setCmOptionForm(Form cmOptionForm) {
		this.cmOptionForm = cmOptionForm;
	}

	public GridLayout getCmOptionFormLayout() {
		return cmOptionFormLayout;
	}

	public void setCmOptionFormLayout(GridLayout cmOptionFormLayout) {
		this.cmOptionFormLayout = cmOptionFormLayout;
	}

	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public Vector<Object> getFieldOrderOption() {
		return fieldOrderOption;
	}

	public void setFieldOrderOption(Vector<Object> fieldOrderOption) {
		this.fieldOrderOption = fieldOrderOption;
	}
	
	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}


}
