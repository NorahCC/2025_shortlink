package com.negoffer.shortlink.admin.dto.req;

import lombok.Data;

/**
 * User registration request parameters
 */
@Data
public class UserRegisterReqDTO {

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Real name
     */
    private String realName;

    /**
     * Phone number
     */
    private String phone;

    /**
     * Email
     */
    private String mail;
}
