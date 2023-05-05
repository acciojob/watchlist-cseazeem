package com.driver;

public class PairAlreadyExistsException extends RuntimeException {
    public PairAlreadyExistsException(String movie, String director){
        super("Pair of Movie " + movie + " and Director " + director + " already exists in database");
    }
}