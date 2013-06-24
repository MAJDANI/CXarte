package com.novedia.talentmap.services;

import com.novedia.talentmap.model.entity.Authentication;

/**
 * Interface Change password service
 * 
 * @author b.tiomofofou
 * @version 2.1
 */
public interface IChangePasswordService {

    /**
     * Save user's password
     * 
     * @param authentication
     *            authentication information user
     * @return Integer
     */
    Integer savePassword(Authentication authentication);

}
