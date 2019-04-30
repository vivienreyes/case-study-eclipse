package com.ibm.training.bootcamp.rest.sample01.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;
import com.ibm.training.bootcamp.rest.sample01.service.UserschedServiceImpl;

@Path("/users")

public class UserschedController {
	
	private UserschedServiceImpl userschedService;
	private Long longTripId;

	public UserschedController() {
		this.userschedService = new UserschedServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usersched> getUsers(
			@QueryParam("name") String name, 
			@QueryParam("load") String load,
			@QueryParam("dtstart") String dtstart,
			@QueryParam("dtend") String dtend,
			@QueryParam("status") String status){

		try {
			List<Usersched> users;
			
			if (StringUtils.isBlank(name)) {
				users = userschedService.findAll();
			} else {
				users = userschedService.findByName(name, load, dtstart, dtend, status);
			}
						
			return users;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@SuppressWarnings("unused")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usersched getUsersched(@PathParam("id") String id, String TripId) {

		try {
			Long longId = Long.parseLong(id);
			Long longtripid = Long.parseLong(TripId);
			Usersched user = userschedService.find(longId);
			Usersched user1 = userschedService.find(longtripid);
			return user1;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsersched(Usersched usersched) {

		try {
			userschedService.add(usersched);
			String result = "User saved : " + usersched.getName() + " " + usersched.getLoad() + " " + usersched.getDtstart() + " " + usersched.getDtend() + " " + usersched.getStatus() + " ";
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(Usersched usersched) {

		try {
			userschedService.upsert(usersched);
			String result = "User updated : " + usersched.getName() + " " + usersched.getLoad() + " " + usersched.getDtstart() + " " + usersched.getDtend() + " " + usersched.getStatus() + " ";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") String id) {

		try {
			Long longId = Long.parseLong(id);
			userschedService.delete(longId);
			String result = "User deleted";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@DELETE
	@Path("{tripid}")
	public Response deleteUsersched(@PathParam("tripid") String tripid) {

		try {
			Long longTripId = Long.parseLong(tripid);
			userschedService.delete(longTripId);
			String result = "User deleted";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	public Long getLongTripId() {
		return longTripId;
	}

	public void setLongTripId(Long longTripId) {
		this.longTripId = longTripId;
	}

}
