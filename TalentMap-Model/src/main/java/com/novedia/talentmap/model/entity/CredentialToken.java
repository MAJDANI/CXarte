package com.novedia.talentmap.model.entity;

import java.io.Serializable;

/**
 * The token that contains login/password Uses by Ibatis
 * 
 * @author e.moumbe
 * 
 */
public class CredentialToken implements Serializable {

    /**
     * serialization identifier
     */
    private static final long serialVersionUID = 1737209031495790149L;

    /**
     * login credential
     */
    private String login;

    /**
     * password credential
     */
    private String password;

    /**
     * @return the login
     */
    public String getLogin() {
	return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
	this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }
}
