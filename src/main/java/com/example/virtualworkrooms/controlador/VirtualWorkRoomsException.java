package com.example.virtualworkrooms.controlador;

@SuppressWarnings("serial")
public class VirtualWorkRoomsException extends Exception {

    public VirtualWorkRoomsException(String msg, Throwable causa){
        super(msg, causa);
    }

    public VirtualWorkRoomsException(String msg){
        super(msg);
    }
    
}