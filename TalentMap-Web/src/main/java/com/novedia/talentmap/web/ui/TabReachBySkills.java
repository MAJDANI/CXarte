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
public class TabReachBySkills extends VerticalLayout{
	private SearchContent searchContentSkil;

	public TabReachBySkills(SearchContent searchContentSkil) {
		super();
		this.searchContentSkil = searchContentSkil;
		this.searchContentSkil.getSearchTarget().switchBySkillsPanel();
		addComponent(this.searchContentSkil);
	}

	/**
	 * @return the searchContentSkil
	 */
	public SearchContent getSearchContentSkil() {
		return searchContentSkil;
	}

	/**
	 * @param searchContentSkil the searchContentSkil to set
	 */
	public void setSearchContentSkil(SearchContent searchContentSkil) {
		this.searchContentSkil = searchContentSkil;
	}
		
}