package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.EAEColleagueResumeForCMDTO;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.dto.EAESynthesisDTO;
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
    EAE getEAEById(Integer id);

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
    @Deprecated
    List<EAEForSynthesisDTO> getOngoingEAEForCM(Integer idManager);

    /**
     * Gets, for a given manager specified by the parameter idManager, the list
     * of Colleague that haven't OPEN or VALIDATED EAE. These colleagues have
     * closed EAE, or may don't have any EAE yet.
     * 
     * @param id
     *            : the id of the manager searching for his colleagues without
     *            ongoing EAE
     * @return List<Colleague> : a list of Colleague
     * @throws DataAccessException
     */
    @Deprecated
    List<Colleague> getCollabWithoutOngoingEAEForManager(Integer idManager);

    /**
     * Gets, for a given colleague specified by the parameter idCollab, the list
     * of all his EAE, with the state for each one.
     * 
     * @param id
     *            : the id of the colleague searching for his colleagues EAE
     *            history
     * 
     * @return List<EAEForSynthesisDTO> : a list of EAEForSynthesisDTO
     * @throws DataAccessException
     */
    List<EAEForSynthesisDTO> getHistoryEAEForCollab(Integer idCollab);
    
    /**
     * Gets general informations corresponding to the given EAE's id 
     * 
     * @param id
     *            : the id of the EAE which we want general datas
     * 
     * @return EAEGeneralityDTO : an object EAEGeneralityDTO containing general informations of the EAE
     * @throws DataAccessException
     */
    EAEGeneralityDTO getEAEGenerality(Integer idEAE);
    
    /**
     * Gets Results informations corresponding to the given EAE's id
     * 
     * @param id
     *            : the id of the EAE which we want results datas
     * 
     * @return EAEResultsDTO : an object EAEResultsDTO containing results informations of the EAE
     * @throws DataAccessException
     */
	EAEResultsDTO getEAEResults(Integer idEAE);

    /**
     * Gets Synthesis informations corresponding to the given EAE's id
     * 
     * @param id
     *            : the id of the EAE which we want synthesis data
     * 
     * @return EAESynthesisDTO : an object EAESynthesisDTO containing synthesis informations of the EAE
     * @throws DataAccessException
     */
	EAESynthesisDTO getEAESynthesis(Integer idEAE);

	/**
	 * Gets the id of the EAE Open for the Colleague's id given. If no EAE Open
	 * is found, returns null
	 * 
	 * @param id
	 *            : the id of the colleague which we want to count open EAE
	 * 
	 * @return Integer : the id or null
	 */
	Integer getOpenEAEIdForColleague(Integer idCollab);

	/**
	 * Gets the list of informations about the last EAE for each colleague
	 * attached to the Manager. This information will be displayed on the
	 * manager's board for his colleague's EAEs
	 * 
	 * @param idManager
	 *            : the id of the manager who want to get his colleagues' EAE
	 *            informations
	 * 
	 * @return List<EAEColleagueResumeForCMDTO> : the list of
	 *         EAEColleagueResumeForCMDTO found
	 */
	List<EAEColleagueResumeForCMDTO> getEAEColleagueResumeForCM(Integer idManager);
	
	/**
	 * Saves the salary for the EAE given.
	 * 
	 * @param eae
	 *            : the EAEGeneralityDTO to save
	 * 
	 */
	int saveEAESalary(EAEGeneralityDTO eae);
	
	/**
	 * Saves the "Results" data for the EAE given. 
	 * 
	 * @param eae
	 *            : the EAEResultsDTO to save
	 * 
	 */
	int saveEAEResults(EAEResultsDTO eae);

	/**
	 * Saves the "Synthesis" data for the EAE given. 
	 * 
	 * @param eae
	 *            : the EAESynthesisDTO to save
	 * 
	 */
	int saveEAESynthesis(EAESynthesisDTO eae);

}