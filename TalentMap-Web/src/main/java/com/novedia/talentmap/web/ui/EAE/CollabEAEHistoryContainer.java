package com.novedia.talentmap.web.ui.EAE;

import java.util.List;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.entity.EAE;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.INotificationService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CollabEAEHistoryContainer extends BeanItemContainer<EAEForSynthesisDTO> {

    private IEAEService eaeService;

    /**
     * Default constructor
     */
    public CollabEAEHistoryContainer() {
	super(EAEForSynthesisDTO.class);
    }

    /**
     * Calls the EAEService to retrieve all EAE history for
     * the colleague's id given (collabId)
     * 
     * @param collabId
     *            : the collab id consultilg his EAE's history
     * @return
     */

    public void fillEAEContainer(int collabId) {
	try {
	    List<EAEForSynthesisDTO> cmListEAE = this.eaeService.getHistoryEAEForCollab(collabId);
	    removeAllItems();
	    if (cmListEAE != null && !cmListEAE.isEmpty()) {
		for (EAEForSynthesisDTO eae : cmListEAE) {
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
