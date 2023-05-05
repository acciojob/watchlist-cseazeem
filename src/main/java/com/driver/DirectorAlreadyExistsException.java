package com.driver;

public class DirectorAlreadyExistsException extends RuntimeException {
    public DirectorAlreadyExistsException(String name){
        super("Director " + name + " already exists in database");
    }
}