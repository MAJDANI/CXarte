package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CmOption;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.impl.NotificationService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmNotificationOption extends VerticalLayout implements
	ClickListener {

    private NotificationService notificationService;
    private Authentication authentication;

    /***
     * Vaadin Components
     */
    private Panel listPanel;
    private Button btnSaveOptions;

    private CmNotificationOptionForm cmNotificationOptionForm;

    private GridLayout cmOptionFormLayout;

    public static final String OPTION_EMAIL_FREQUENCY = "Email frequency";

    /**
     * Default constructor
     */
    public CmNotificationOption() {
	super();
    }

    /**
     * Build the view of cm's notification options
     * 
     * @return
     */
    public CmNotificationOption buildViewNotificationOptionContent() {
	removeAllComponents();
	buildMain();
	return this;
    }

    /**
     * The main builder
     * 
     * @class CmNotificationOption.java
     */
    public void buildMain() {
	setMargin(true);
	setSpacing(true);
	buildButton();
	buildListPanelNotificationOption();
    }

    public void buildButton() {
	this.btnSaveOptions.setCaption(ConstantsEnglish.CM_OPTION_SAVE);
	this.btnSaveOptions.addListener(this);
    }

    public void buildListPanelNotificationOption() {
	listPanel.removeAllComponents();

	// components initialisation
	cmOptionFormLayout = new GridLayout();
	cmNotificationOptionForm.setAuthentication(getAuthentication());
	cmNotificationOptionForm.setCmOptionFormLayout(cmOptionFormLayout);
	cmNotificationOptionForm = cmNotificationOptionForm
		.buildCmOptionFormView();
	listPanel.addComponent(cmNotificationOptionForm);
	listPanel.addComponent(btnSaveOptions);
	addComponent(listPanel);
    }

    @Override
    public void buttonClick(ClickEvent event) {
	Button button = event.getButton();
	// SAVE OPTIONS
	if (button == this.btnSaveOptions) {
	    BeanItem<CmOption> cmOptionItem = (BeanItem<CmOption>) this.cmNotificationOptionForm
		    .getCmOptionForm().getItemDataSource();
	    CmOption cmOption = cmOptionItem.getBean();
	    if (notificationService.getCmFrequencyOption(getAuthentication()
		    .getColleagueId()) != null) {
		notificationService.saveFrequencyOption(cmOption.getFrequency()
			.getId(), getAuthentication().getColleagueId());
	    } else {
		notificationService.addFrequencyOption(cmOption.getFrequency()
			.getId(), getAuthentication().getColleagueId());
	    }
	    getWindow().showNotification("Options saved",
		    Notification.TYPE_WARNING_MESSAGE);
	}

    }

    public NotificationService getNotificationService() {
	return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
	this.notificationService = notificationService;
    }

    public Panel getListPanel() {
	return listPanel;
    }

    public void setListPanel(Panel listPanel) {
	this.listPanel = listPanel;
    }

    public CmNotificationOptionForm getCmNotificationOptionForm() {
	return cmNotificationOptionForm;
    }

    public void setCmNotificationOptionForm(
	    CmNotificationOptionForm cmNotificationOptionForm) {
	this.cmNotificationOptionForm = cmNotificationOptionForm;
    }

    public GridLayout getCmOptionFormLayout() {
	return cmOptionFormLayout;
    }

    public void setCmOptionFormLayout(GridLayout cmOptionFormLayout) {
	this.cmOptionFormLayout = cmOptionFormLayout;
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    public Button getBtnSaveOptions() {
	return btnSaveOptions;
    }

    public void setBtnSaveOptions(Button btnSaveOptions) {
	this.btnSaveOptions = btnSaveOptions;
    }

}
