package com.novedia.talentmap.model.entity;

import java.io.Serializable;
import java.util.Date;

public class EAE implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8904800079555349662L;

    
    /**
     * The identifier of the EAE
     */
    private Integer id;
    /**
     * The colleague concerned by the EAE
     */
    private Colleague colleague;
    /**
     * The manager of the colleague when the EAE occurred
     */
    private Colleague manager;
    /**
     * The date of the meeting for the EAE
     */
    private Date dateEae;
    /**
     * The label of the Profile the colleague has at the date of the EAE. As the
     * profile of the colleague may change in future, and the database may
     * change too, we have here the label and not the id
     */
    private String profileLabel;
    /**
     * The identifier of the previous EAE of the same colleague. We have the id
     * instead of the Object EAE in order to avoid heavy loading of many EAE
     * referencing other EAE years after years.
     */
    private Integer previousEaeId;
    /**
     * The Strengths of the colleague during the year
     */
    private String colleaguesStrengths;
    /**
     * The development axis of the colleague
     */
    private String colleaguesWeaknesses;
    /**
     * The current state of the EAE (open, validated, close)
     */
    private EAEState eaeState;
    /**
     * The synthesis of the year given by the colleague.
     */
    private String colleaguesSynthesis;
    /**
     * The synthesis of the year given by the manager.
     */
    private String managersSynthesis;
    /**
     * A free field in order to indicate any other subject.
     */
    private String other;

    /**
     * Builds an object EAE
     */
    public EAE() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the colleague
     */
    public Colleague getColleague() {
        return colleague;
    }

    /**
     * @param colleague the colleague to set
     */
    public void setColleague(Colleague colleague) {
        this.colleague = colleague;
    }

    /**
     * @return the manager
     */
    public Colleague getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(Colleague manager) {
        this.manager = manager;
    }

    /**
     * @return the dateEae
     */
    public Date getDateEae() {
        return dateEae;
    }

    /**
     * @param dateEae the dateEae to set
     */
    public void setDateEae(Date dateEae) {
        this.dateEae = dateEae;
    }

    /**
     * @return the profileLabel
     */
    public String getProfileLabel() {
        return profileLabel;
    }

    /**
     * @param profileLabel the profileLabel to set
     */
    public void setProfileLabel(String profileLabel) {
        this.profileLabel = profileLabel;
    }

    /**
     * @return the previousEaeId
     */
    public Integer getPreviousEaeId() {
        return previousEaeId;
    }

    /**
     * @param previousEaeId the previousEaeId to set
     */
    public void setPreviousEaeId(Integer previousEaeId) {
        this.previousEaeId = previousEaeId;
    }

    /**
     * @return the colleaguesStrengths
     */
    public String getColleaguesStrengths() {
        return colleaguesStrengths;
    }

    /**
     * @param colleaguesStrengths the colleaguesStrengths to set
     */
    public void setColleaguesStrengths(String colleaguesStrengths) {
        this.colleaguesStrengths = colleaguesStrengths;
    }

    /**
     * @return the colleaguesWeaknesses
     */
    public String getColleaguesWeaknesses() {
        return colleaguesWeaknesses;
    }

    /**
     * @param colleaguesWeaknesses the colleaguesWeaknesses to set
     */
    public void setColleaguesWeaknesses(String colleaguesWeaknesses) {
        this.colleaguesWeaknesses = colleaguesWeaknesses;
    }

    /**
     * @return the eaeState
     */
    public EAEState getEaeState() {
        return eaeState;
    }

    /**
     * @param eaeState the eaeState to set
     */
    public void setEaeState(EAEState eaeState) {
        this.eaeState = eaeState;
    }

    /**
     * @return the colleaguesSynthesis
     */
    public String getColleaguesSynthesis() {
        return colleaguesSynthesis;
    }

    /**
     * @param colleaguesSynthesis the colleaguesSynthesis to set
     */
    public void setColleaguesSynthesis(String colleaguesSynthesis) {
        this.colleaguesSynthesis = colleaguesSynthesis;
    }

    /**
     * @return the managersSynthesis
     */
    public String getManagersSynthesis() {
        return managersSynthesis;
    }

    /**
     * @param managersSynthesis the managersSynthesis to set
     */
    public void setManagersSynthesis(String managersSynthesis) {
        this.managersSynthesis = managersSynthesis;
    }

    /**
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other the other to set
     */
    public void setOther(String other) {
        this.other = other;
    }
    
    
}
