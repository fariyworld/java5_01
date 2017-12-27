package com.offcn.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.offcn.domain.Student;

public interface PhoneService {

	@GET
	@Path("/get/{phoneNumber}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	Student queryPhoneLocation(@PathParam("phoneNumber") String phoneNumber);
}
