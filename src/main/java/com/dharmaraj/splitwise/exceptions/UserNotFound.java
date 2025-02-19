package com.dharmaraj.splitwise.exceptions;

public class UserNotFound extends RuntimeException {
    
    public UserNotFound(Long userId) {
        
        super("User with id '" + userId + "' is not found.");
    }
}
