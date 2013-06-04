package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;

/**
 * The EAE Service Interface.
 * 
 * @author v.guillemain
 */

public interface IEAEService {

    /**
     * Get an EAE By Id.
     * 
     * @class IEAEService.java
     * @param id
     *            the id of the EAE searched
     * @return an EAE
     */
    EAE getEAE(Integer id);

    /**
     * Adds the EAE.
     * 
     * @class IEAEService.java
     * @param eae
     *            the EAE to add
     * @return an integer, result of the request
     */
    int addEAE(EAE eae);

    /**
     * Updates the EAE.
     * 
     * @class IEAEService.java
     * @param eae
     *            the EAE to update
     * @return an integer, result of the request
     */
    int saveEAE(EAE eae);

    /**
     * Deletes the EAE.
     * 
     * @class IEAEService.java
     * @param eae
     *            the EAE to delete
     * @return an integer, result of the request
     */
    int deleteEAE(EAE eae);

    /**
     * Gets, for a given manager specifier by the parameter id, the list of EAE
     * that are either OPEN or VALIDATED
     * 
     * @param idManager
     *            : the id of the manager searching for his ongoing EAE
     * @return List<EAE> : a list of EAE which state is OPEN or VALIDATED
     * 
     */
    List<EAEForSynthesis> getOngoingEAEForCM(Integer idManager);
    
    /**
     * Gets, for a given manager specifier by the parameter id, the list of
     * Colleague that haven't OPEN or VALIDATED EAE. These colleagues have
     * closed EAE, or may don't have any EAE yet.
     * 
     * @param id
     *            : the id of the manager searching for his colleagues without
     *            ongoing EAE
     * @return List<Colleague> : a list of Colleague
     * @throws DataAccessException
     */
    List<Colleague> getCollabWithoutOngoingEAEForManager(Integer idManager);
}