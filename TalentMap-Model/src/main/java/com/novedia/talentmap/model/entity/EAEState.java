package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import com.novedia.talentmap.model.entity.Frequency.Builder;

/**
 * This entity represents the state of an EAE. An EAE may have 3 states : when
 * it is created by the manager, in anticipation of an early future EAE, the
 * state of the EAE is "Open". In this state "Open", the colleague is invited to
 * fill his part of informations about his activity during the year. When the
 * colleague's input is finished, the colleague validates his inputs : the EAE
 * is in state "validated" : the manager can open the EAE and 1.see the
 * colleague's input 2.fill the manager's part of informations. When the EAE is
 * finished and filled, the manager can close it. In state "close", the
 * colleague may consult the EAE but it's impossible to modify anything. The 3
 * states : open, validated, close.
 * 
 * @author v.guillemain
 * 
 */
public class EAEState implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5872607963205533469L;


    /**
     * the identifier of the EAEState
     */
    private Integer id;

    /**
     * The label (open, validated or close) of the EAEState
     */
    private String label;

    /**
     * Build an immutable EAEState entity.
     * 
     * @param builder
     *            the builder inner class for this entity
     */
    public EAEState(final Builder builder) {
	this.id = builder.id;
	this.label = builder.label;
    }

    /**
     * Builds an Object EAEState
     */
    public EAEState() {
    }

    /**
     * @return id : the identifier of the EAEState
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : the identifier of the EAEState
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return label : the label of the EAEState
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label : the label of the EAEState
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    // -------------------------------------
    // ------------ BUILDER PART -----------
    // -------------------------------------
    /**
     * Static constructor for this class.
     * 
     * @return a builder instance
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * Inner builder class.
     * 
     */
    public static class Builder {

	/**
	 * EAEState identifier
	 */
	private Integer id;

	/**
	 * EAEState name
	 */
	private String label;

	/**
	 * Set id
	 * 
	 * @param id
	 *            EAEState's identifier
	 * @return the builder
	 */
	public Builder id(final Integer id) {
	    this.id = id;
	    return this;
	}

	/**
	 * Set label
	 * 
	 * @param label
	 *            the EAEState's label
	 * @return the builder
	 */
	public Builder label(final String label) {
	    this.label = label;
	    return this;
	}

	/**
	 * Build an immutable instance of EAEState.
	 * 
	 * @return EAEState
	 */
	public EAEState build() {
	    return new EAEState(this);
	}

	/**
	 * Static constructor for this class.
	 * 
	 * @return a builder instance
	 */
	public static Builder builder() {
	    return new Builder();
	}
    }

}
