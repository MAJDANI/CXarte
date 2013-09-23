package com.novedia.talentmap.model.entity;

import java.io.Serializable;

/**
 * 
 * @author v.guillemain
 * 
 */
public class Topic implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -5301416165845310249L;

    /**
     * the identifier of the Topic
     */
    private Integer id;

    /**
     * The label of the Topic
     */
    private String label;

    /**
     * Builds an Object Topic
     */
    public Topic() {
    }

    /**
     * @return id : the identifier of the Topic
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : the identifier of the Topic
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return label : the label of the Topic
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label : the label of the Topic
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
