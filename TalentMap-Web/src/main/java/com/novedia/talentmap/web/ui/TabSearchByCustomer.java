/**
 * 
 */
package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.search.SearchContent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author v.dibi
 *
 */
public class TabSearchByCustomer extends VerticalLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchContent searchContent;
	
	public TabSearchByCustomer(SearchContent searchContent) {
		super();		
		this.searchContent = searchContent;
		this.searchContent.getSearchTarget().switchByClientPanel();
		addComponent(this.searchContent);
	}

	/**
	 * @return the searchContent
	 */
	public SearchContent getSearchContent() {
		return searchContent;
	}

	/**
	 * @param searchContent the searchContent to set
	 */
	public void setSearchContent(SearchContent searchContent) {
		this.searchContent = searchContent;
	}
	
	
	
}