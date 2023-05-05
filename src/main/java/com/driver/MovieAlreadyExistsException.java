package com.driver;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String name){
        super("Movie " + name + " already exists in database");
    }
}