package com.sofrecom.sn3.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message){

        super(message);
    }
}
