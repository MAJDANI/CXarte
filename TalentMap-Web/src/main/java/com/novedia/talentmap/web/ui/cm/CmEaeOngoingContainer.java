package com.novedia.talentmap.web.ui.cm;

import java.util.List;

import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.INotificationService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CmEaeOngoingContainer extends BeanItemContainer<EAEForSynthesis> {

    private IEAEService eaeService;

    /**
     * Default constructor
     */
    public CmEaeOngoingContainer() {
	super(EAEForSynthesis.class);
    }

    /**
     * Calls the EAEService to retrieve all EAE not closed in the Data Base for
     * the manager's id given (collabId)
     * 
     * @param collabId
     *            : the collab id of the manager consulting his Ongoing EAEs
     * @return
     */

    public void fillEAEContainer(int collabId) {
	try {
	    List<EAEForSynthesis> cmListEAE = this.eaeService.getOngoingEAEForCM(collabId);
	    removeAllItems();
	    if (cmListEAE != null && !cmListEAE.isEmpty()) {
		for (EAEForSynthesis eae : cmListEAE) {
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
