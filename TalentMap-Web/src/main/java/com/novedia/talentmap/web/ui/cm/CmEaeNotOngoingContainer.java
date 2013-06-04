package com.novedia.talentmap.web.ui.cm;

import java.util.List;

import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IEAEService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CmEaeNotOngoingContainer extends
	BeanItemContainer<Colleague> {

    private IEAEService eaeService;

    /**
     * Default constructor
     */
    public CmEaeNotOngoingContainer() {
	super(Colleague.class);
    }

    /**
     * Calls the EAEService to retrieve all Colleague that don't have an ongoing
     * EAE. The colleagues depending on the manager which the id is given
     * (collabId)
     * 
     * @param collabId
     *            : the collab id of the manager consulting his Ongoing EAEs
     * @return
     */

    public void fillEAEContainer(int collabId) {
	try {
	    List<Colleague> cmListColleague = this.eaeService
		    .getCollabWithoutOngoingEAEForManager(collabId);
	    removeAllItems();
	    if (cmListColleague != null && !cmListColleague.isEmpty()) {
		for (Colleague eae : cmListColleague) {
		    addBean(eae);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * @return the eaeService
     */
    public IEAEService getEaeService() {
	return eaeService;
    }

    /**
     * @param eaeService
     *            the eaeService to set
     */
    public void setEaeService(IEAEService eaeService) {
	this.eaeService = eaeService;
    }

}
