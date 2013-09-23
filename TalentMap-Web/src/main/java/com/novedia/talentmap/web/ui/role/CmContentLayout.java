package com.novedia.talentmap.web.ui.role;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.cm.CmNavigation;
import com.novedia.talentmap.web.ui.search.SearchContent;
import com.novedia.talentmap.web.util.IAdminView;
import com.novedia.talentmap.web.util.ISearchLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmContentLayout extends HorizontalLayout {

    /**
     * Util Observateur
     */
    private IAdminView obs;

    /**
     * All views
     */
    private CmNavigation cmNav;

    private Authentication authentication;

    /**
     * Vaadin Components
     */
    private HorizontalSplitPanel hSplitContent;
    private SearchContent searchContent;

    /**
     * Default constructor
     */
    public CmContentLayout() {
	super();
    }

    /**
     * Build the CmContentLayout view
     * 
     * @return
     */
    public CmContentLayout buildViewCmContentLayout() {
	removeAllComponents();
	cmNav = cmNav.buildCmNavigation();
	searchContent.setAuthentication(getAuthentication());
	searchContent = searchContent.buildSearchContentView();
	mainBuild();
	buildObservators();
	return this;
    }

    public void buildObservators() {

	this.cmNav.addObservateur(new ISearchLayout() {

	    @Override
	    public void switchPanelTarget(int searchTargetPanel) {
		CmContentLayout.this.searchContent
			.switchPanel(searchTargetPanel);
	    }

	}, ISearchLayout.class);

    }

    /**
     * 
     * @class CmView.java
     */
    public void mainBuild() {

	VerticalLayout vLayout = new VerticalLayout();
	vLayout.setHeight("600px");
	vLayout.addComponent(this.cmNav);
	this.hSplitContent.setFirstComponent(vLayout);
	this.hSplitContent.setSecondComponent(this.searchContent);
	this.hSplitContent.setSplitPosition(20);
	hSplitContent.setLocked(true);
	addComponent(this.hSplitContent);
	setSizeFull();
	setExpandRatio(this.hSplitContent, 1);
    }

    /**
     * Get the authentication value
     * 
     * @return the authentication
     */
    public Authentication getAuthentication() {
	return authentication;
    }

    /**
     * Set the authentication value
     * 
     * @param authentication
     *            the authentication to set
     */
    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * Get the hSplitContent
     * 
     * @return the hSplitContent
     */
    public HorizontalSplitPanel gethSplitContent() {
	return hSplitContent;
    }

    /**
     * Set the hSplitContent
     * 
     * @param hSplitContent
     *            the hSplitPanel to set
     */
    public void sethSplitContent(HorizontalSplitPanel hSplitContent) {
	this.hSplitContent = hSplitContent;
    }

    public CmNavigation getCmNav() {
	return cmNav;
    }

    public void setCmNav(CmNavigation cmNav) {
	this.cmNav = cmNav;
    }

    public SearchContent getSearchContent() {
	return searchContent;
    }

    public void setSearchContent(SearchContent searchContent) {
	this.searchContent = searchContent;
    }

    public IAdminView getObs() {
	return obs;
    }

    public void setObs(IAdminView obs) {
	this.obs = obs;
    }

}
