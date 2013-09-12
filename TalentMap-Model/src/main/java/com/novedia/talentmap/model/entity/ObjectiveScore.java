package com.novedia.talentmap.model.entity;

import java.io.Serializable;

/**
 * This entity represents the score of an Objective. An Objective may have 4 scores : 
 * 1- not achieved
 * 2- partly achieved
 * 3- achieved
 * 4- exceeded
 * 
 * @author v.guillemain
 * 
 */
public class ObjectiveScore implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 2757171270554344889L;

    /**
     * the identifier of the ObjectiveScore
     */
    private Integer id;

    /**
     * The label (not achieved, partly achieved, achieved, exceeded) of the ObjectiveScore
     */
    private String label;

    /**
     * Builds an Object ObjectiveScore
     */
    public ObjectiveScore() {
    }

    /**
     * @return id : the identifier of the ObjectiveScore
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : the identifier of the ObjectiveScore
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return label : the label of the ObjectiveScore
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label : the label of the ObjectiveScore
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
