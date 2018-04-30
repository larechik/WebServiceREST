package com.makhnovetc.ifmo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPerson(@QueryParam("id") String id,@QueryParam("name") String name,@QueryParam("middlename") String middlename,
                                  @QueryParam("surname") String surname,@QueryParam("dob") String dob,
                                  @QueryParam("sex") String sex) {
        List<Person> person = new PostgreSQLDAO().findPeople(id, name, middlename, surname, dob, sex);
        return person;
    }
}
