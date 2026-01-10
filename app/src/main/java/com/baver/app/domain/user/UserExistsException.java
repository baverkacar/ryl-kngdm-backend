package com.baver.app.domain.user;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String username) {
        super("User with username '" + username + "' already exists.");
    }
}
