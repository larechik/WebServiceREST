package com.makhnovetc.ifmo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(value={"text/xml", "application/json"})
public class PersonResource {

    @GET
    public List<Person> getPerson(@QueryParam("id") String id,@QueryParam("name") String name,@QueryParam("middlename") String middlename,
                                  @QueryParam("surname") String surname,@QueryParam("dob") String dob,
                                  @QueryParam("sex") String sex) {
        List<Person> person = new PostgreSQLDAO().findPeople(id, name, middlename, surname, dob, sex);
        return person;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String insertPerson(@QueryParam("name") String name,@QueryParam("middlename") String middlename,
                                     @QueryParam("surname") String surname,@QueryParam("dob") String dob,
                                     @QueryParam("sex") String sex){
        String id = new PostgreSQLDAO().insertPerson(name,middlename,surname,dob,sex);
        return id;
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updatePerson(@QueryParam("id") String id,@QueryParam("name") String name,@QueryParam("middlename") String middlename,
                               @QueryParam("surname") String surname,@QueryParam("dob") String dob,
                               @QueryParam("sex") String sex){
        String result = new PostgreSQLDAO().updatePerson(id, name, middlename, surname, dob, sex);
        return result;
    }
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deletePerson(@QueryParam("id") String id){
        String result = new PostgreSQLDAO().deletPerson(id);
        return result;
    }
}
