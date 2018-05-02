package com.makhnovetc.ifmo.rest.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.ExceptionBean")
public class InvalidDateFormatException extends Exception{
    public static InvalidDateFormatException DEFAULT_INSTANCE = new InvalidDateFormatException("Error entering dob: wrong format. The correct format is 'YYYY-MM-DD");
    public InvalidDateFormatException(String message){
        super(message);
    }
}
