package com.example.petstore;

import com.example.petstore.data.Pets;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	final private Pets petData = Pets.getInstance();

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {

		List<Pet> petList = petData.getPetList();
		return Response.ok(petList).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Pet pet1 = petData.getPetByID(petId);

		if(pet1 != null){
			return Response.ok(pet1).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet with name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the name.") })
	@GET
	@Path("name/{petName}")
	public Response getPetByName(@PathParam("petName") String petName) {


		Pet pet1 = petData.getPetByName(petName);

		if(pet1 != null){
			return Response.ok(pet1).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet with age", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the age.") })
	@GET
	@Path("age/{petAge}")
	public Response getPetByAge(@PathParam("petAge") int petAge) {

		List<Pet> petsWithAge = petData.getPetByAge(petAge);

		if(petsWithAge.size() > 0){
			return Response.ok(petsWithAge).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet( Pet pet ) {
		Pet addedPet = petData.addPet(pet);

		return Response.ok(addedPet).build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePet( Pet pet ) {
		Pet updatedPet = petData.updatePet(pet);

		return Response.ok(pet).build();
	}

	@DELETE
	@Path("delete/{petId}")
	public Response removePet(@PathParam("petId") int petId ) {
		int removedPet = petData.removePet(petId);

		if (removedPet != -1) {
			return Response.ok(removedPet).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}
}
