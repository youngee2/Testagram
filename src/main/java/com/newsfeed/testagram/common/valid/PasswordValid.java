package com.newsfeed.testagram.common.valid;

public abstract class PasswordValid {
    public static final String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$";

}
