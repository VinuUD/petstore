package com.example.petstore;

import com.example.petstore.data.PetTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.data.Pets;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.graalvm.collections.Pair;
import org.jose4j.jwe.RsaKeyManagementAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("/v1/pets/types")
@Produces("application/json")
public class PetTypeResource {

    final private Pets petData = Pets.getInstance();
    final private PetTypes petTypesData = PetTypes.getInstance();

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets Classified by Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    public Response getPetTypes() {

        List<PetType> petTypeList = petTypesData.getPetTypes();

        if (petTypeList.size() > 0 ) {
            return Response.ok(petTypeList).build();
        }
        return Response.status(Status.NOT_FOUND).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets Classified by Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    @Path("all")
    public Response getAllPetsByTypes() {

        List<Pet> petList = petData.getPetList();
        List<PetType> petTypeList = petTypesData.getPetTypes();

        Map<String, List<Pet>> petsByTypeMap
                = petList.stream()
                .collect(Collectors.groupingBy(Pet::getPetType));

        if (petTypeList.size() > 0 ) {
            return Response.ok(petsByTypeMap).build();
        }

        return Response.status(Status.NOT_FOUND).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "PetType for name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pets found for the type.") })
    @GET
    @Path("{petType}")
    public Response getPetsByType(@PathParam("petType") String petType) {

        List<Pet> petList = petData.getPetList();

        Predicate<Pet> byType = pet -> pet.getPetType().equalsIgnoreCase(petType);

        List<Pet> result = petList.stream().filter(byType).collect(Collectors.toList());

        if (result.size() > 0 ) {
            return Response.ok(result).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPet( PetType petType) {
        PetType addedPetType = petTypesData.addPetType(petType);

        return Response.ok(addedPetType).build();
    }


//    TODO: Update pet type of pets in DB when pet type is updated.
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePetType( PetType petType ) {
        PetType updatedPet = petTypesData.updatePetType(petType);

        return Response.ok(updatedPet).build();
    }

    @DELETE
    @Path("delete/{petTypeId}")
    public Response removePetType(@PathParam("petTypeId") int petTypeId ) {
        int removedPetType = petTypesData.removePetType(petTypeId);

        if (removedPetType != -1) {
            return Response.ok(removedPetType).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }
}
