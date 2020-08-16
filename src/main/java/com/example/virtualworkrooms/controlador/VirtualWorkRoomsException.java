package com.example.virtualworkrooms.controlador;

public class VirtualWorkRoomsException extends Exception {

    public VirtualWorkRoomsException(String msg, Throwable causa){
        super(msg, causa);
    }

    public VirtualWorkRoomsException(String msg){
        super(msg);
    }
    
}