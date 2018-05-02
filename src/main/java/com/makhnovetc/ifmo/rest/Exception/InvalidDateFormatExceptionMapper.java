package com.makhnovetc.ifmo.rest.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidDateFormatExceptionMapper implements ExceptionMapper<InvalidDateFormatException>{

    @Override
    public Response toResponse(InvalidDateFormatException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
