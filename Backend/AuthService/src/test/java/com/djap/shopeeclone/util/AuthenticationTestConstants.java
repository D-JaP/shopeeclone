package com.djap.shopeeclone.util;

public class AuthenticationTestConstants {
//  Login Contraints testing
    public static final String URL_AUTH_LOGIN = "/api/v1/auth/login";
    public static final String USERNAME = "test1@test.com";
    public static final String PASSWORD = "123456";

//    Register contraints testing
    public static final String URL_AUTH_REGISTER = "/api/v1/auth/registration";
    public static final String USERNAME_REGISTER = "test1@test.com";
    public static final String PASSWORD_REGISTER = "123456";
    public static final String FIRSTNAME_REGISTER = "Jon";
    public static final String LASTNAME_REGISTER = "Snow";

//  Refresh testing
    public static final String URL_REFRESH_TOKEN = "/api/v1/auth/refresh";
    public static final Long REFRESH_ACCOUNT_ID = 3L;
}
