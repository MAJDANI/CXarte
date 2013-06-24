package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.VerticalLayout;

public class SearchView extends VerticalLayout {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1779457808036835024L;
    /**
     * Vaadin UI
     */
    private SearchLayout searchLayout;
    private Authentication authentication;

    /**
     * Default constructor
     */
    public SearchView() {
	super();
    }

    public SearchView buildSearchView() {
	removeAllComponents();
	searchLayout.setAuthentication(authentication);
	searchLayout = searchLayout.buildSearchLayout();
	mainBuild();
	return this;
    }

    public void mainBuild() {
	addComponent(this.searchLayout);
    }

    /**
     * Get the searchLayout value
     * 
     * @return the searchLayout
     */
    public SearchLayout getSearchLayout() {
	return searchLayout;
    }

    /**
     * Set the searchLayout value
     * 
     * @param searchLayout
     *            the searchLayout to set
     */
    public void setSearchLayout(SearchLayout searchLayout) {
	this.searchLayout = searchLayout;
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

}
