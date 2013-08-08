package com.novedia.talentmap.web.ui.admin;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class ColleagueContainer extends BeanItemContainer<Colleague> {
	
	private IColleagueService colleagueService;
	
	
	public ColleagueContainer(){
		super(Colleague.class);
	}

	
	public ColleagueContainer fillContainerByName(String colleagueName){
		removeAllItems();
		List<Colleague> colleagueList =  colleagueService.getAllColleaguesByName(colleagueName);
		if(colleagueList != null && !colleagueList.isEmpty()){
			for (Colleague colleague : colleagueList) {
				addBean(colleague);
			}
		}
		return this;
	}


	public IColleagueService getColleagueService() {
		return colleagueService;
	}


	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}
	
	
	
	
}
