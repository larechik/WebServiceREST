package com.makhnovetc.ifmo.rest;

import com.makhnovetc.ifmo.rest.Exception.InvalidDateFormatException;
import com.makhnovetc.ifmo.rest.Exception.NullFieldException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(value = {"text/xml", "application/json"})
public class PersonResource {

    @GET
    public List<Person> getPerson(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("middlename") String middlename,
                                  @QueryParam("surname") String surname, @QueryParam("dob") String dob,
                                  @QueryParam("sex") String sex) throws NullFieldException, InvalidDateFormatException {
        if (dob != null || !dob.isEmpty()) {
            if (!checkDate(dob)) {
                throw new InvalidDateFormatException("Error entering dob: wrong format. The correct format is 'YYYY-MM-DD");
            }
        }
        List<Person> person = new PostgreSQLDAO().findPeople(id, name, middlename, surname, dob, sex);
        return person;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String insertPerson(@QueryParam("name") String name, @QueryParam("middlename") String middlename,
                               @QueryParam("surname") String surname, @QueryParam("dob") String dob,
                               @QueryParam("sex") String sex) throws InvalidDateFormatException, NullFieldException {
        checkField(name);
        checkField(middlename);
        checkField(surname);
        checkField(dob);
        checkField(sex);
        if (!checkDate(dob)) {
            throw InvalidDateFormatException.DEFAULT_INSTANCE;
        }
        String id = new PostgreSQLDAO().insertPerson(name, middlename, surname, dob, sex);
        return id;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updatePerson(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("middlename") String middlename,
                               @QueryParam("surname") String surname, @QueryParam("dob") String dob,
                               @QueryParam("sex") String sex) throws InvalidDateFormatException, NullFieldException {

        checkField(id);
        if (dob != null || !dob.isEmpty()) {
            if (!checkDate(dob)) {
                throw InvalidDateFormatException.DEFAULT_INSTANCE;
            }
        }

        String result = new PostgreSQLDAO().updatePerson(id, name, middlename, surname, dob, sex);
        return result;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deletePerson(@QueryParam("id") String id) {
        try {
            checkField(id);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }
        String result = new PostgreSQLDAO().deletPerson(id);
        return result;
    }

    private void checkField(String field) throws NullFieldException {
        if (field == null || field.isEmpty()) {
            throw NullFieldException.DEFAULT_INSTANCE;
        }
    }

    public boolean checkDate(String dateToValidate) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
