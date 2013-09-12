package com.novedia.talentmap.model.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author v.dibi
 * @author v.guillemain
 */
public class Authentication implements Serializable {

    /**
     * serial number
     */
    private static final long serialVersionUID = 6615977875597428826L;

    /**
     * Credential
     */
    private CredentialToken token;

    /**
     * id of collaborator
     */
    private Integer colleagueId;

    /**
     * Authorization
     */
    @JsonIgnore
    private Authorization authorization;

    /**
     * Build an immutable Authentification entity. the builder inner class for
     * this entity
     */

    // ------------------------------------------
    // ------------ OVERRIDEN METHODS -----------
    // ------------------------------------------
    @Override
    public String toString() {
	StringBuilder strBld = new StringBuilder();
	strBld.append("[colleagueId=").append(getColleagueId()).append(", ");
	if(this.token != null){
	    strBld.append("token.login=").append(getToken().getLogin()).append("]");
	}
	return strBld.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
	HashCodeBuilder hashBuilder = new HashCodeBuilder();
	hashBuilder.append(this.getToken().getLogin());
	return hashBuilder.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}

	if (!(obj instanceof Authentication)) {
	    return false;
	}

	Authentication comparedObj = (Authentication) obj;
	EqualsBuilder ebuilder = new EqualsBuilder();
	ebuilder.append(this.getToken().getLogin(), comparedObj.getToken()
		.getLogin());
	return ebuilder.isEquals();
    }

    /**
     * @return the token
     */
    public CredentialToken getToken() {
	return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(CredentialToken token) {
	this.token = token;
    }

    /**
     * @return the authorization
     */
    public Authorization getAuthorization() {
	return authorization;
    }

    /**
     * @param authorization
     *            the authorization to set
     */
    @JsonIgnore
    public void setAuthorization(Authorization authorization) {
	this.authorization = authorization;
    }

    /**
     * @return the colleagueId
     */
    public Integer getColleagueId() {
	return colleagueId;
    }

    /**
     * @param colleagueId
     *            the colleagueId to set
     */
    public void setColleagueId(Integer colleagueId) {
	this.colleagueId = colleagueId;
    }
}
