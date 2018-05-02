package com.makhnovetc.ifmo.rest.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NullFieldExceptionMapper implements ExceptionMapper<NullFieldException> {
    @Override
    public Response toResponse(NullFieldException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

    }
}
