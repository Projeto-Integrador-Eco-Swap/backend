package com.generation.backend.exception;

public class EmailUserNotFoundException extends Exception {
        public EmailUserNotFoundException(String email) {
            super("User with email " + email + " not found.");
        }
}
