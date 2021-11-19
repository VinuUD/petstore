package com.example.petstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "PetType")
public class PetType {

    @Schema(required = true, description = "Pet type id")
    @JsonProperty("Pet_ID")
    private int petTypeID;

    @Schema(required = true, description = "Pet type")
    @JsonProperty("Pet_Type")
    private String petType;

    public String getPetType(){ return petType; }

    public void setPetType(String petType) { this.petType = petType; }

    public Integer getPetTypeID() { return petTypeID; }

    public void setPetTypeID(int petTypeID) { this.petTypeID = petTypeID; }

}
