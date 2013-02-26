/**
 * 
 */
package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.search.SearchContent;
import com.novedia.talentmap.web.ui.search.SearchTarget;
import com.vaadin.ui.VerticalLayout;

/**
 * @author v.dibi
 *
 */
public class TabReachByName extends VerticalLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchContent searchContentName;

	public TabReachByName(SearchContent searchContentName) {
		super();
		this.searchContentName = searchContentName;
		addComponent(this.searchContentName);
	}

	/**
	 * @return the searchContentName
	 */
	public SearchContent getSearchContentName() {
		return searchContentName;
	}

	/**
	 * @param searchContentName the searchContentName to set
	 */
	public void setSearchContentName(SearchContent searchContentName) {
		this.searchContentName = searchContentName;
	}
	}