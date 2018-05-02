package com.makhnovetc.ifmo.rest.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.ExceptionBean")
public class NullFieldException extends Exception{
    public static NullFieldException DEFAULT_INSTANCE = new NullFieldException("Invalid field. Field is null or empty.");
    public NullFieldException(String message){
        super(message);
    }

}
