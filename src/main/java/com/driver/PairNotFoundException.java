package com.driver;

public class PairNotFoundException extends RuntimeException {
    public PairNotFoundException(String movieName, String directorName){
        super("Pair not found");
    }
}