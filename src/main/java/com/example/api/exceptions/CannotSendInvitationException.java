package com.example.api.exceptions;

public class CannotSendInvitationException extends RuntimeException {
    public CannotSendInvitationException(String message) {
        super(message);
    }
}
