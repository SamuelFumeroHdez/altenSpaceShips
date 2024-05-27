package com.alten.spaceships.exception;

public class SpaceShipNotFoundException extends RuntimeException{
    public SpaceShipNotFoundException(String message){
        super(message);
    }
}
