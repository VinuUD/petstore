package org.acme;

import com.example.petstore.PetType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class PetTypeResourceTest {

    @Test
    @Order(1)
    public void testPetTypeEndpoint() {
        given()
                .when().get("/v1/pets/types")
                .then()
                .statusCode(200)
                .body("petTypeID", hasItem(1))
                .body("petType",hasItem("Dog"));
    }

    @Test
    @Order(2)
    public void testPetTypeAddEndpoint() {
        Integer newPetTypeID = 6;
        String newPetTypeName = "Azure Kun";

        PetType petType = new PetType();
        petType.setPetTypeID(newPetTypeID);
        petType.setPetType(newPetTypeName);

        given().contentType(ContentType.JSON)
                .body(petType)
                .when().post("/v1/pets/types/add")
                .then()
                .statusCode(200);

    }

    @Test
    @Order(3)
    public void testPetTypeUpdateEndpoint() {
        Integer newPetTypeID = 3;
        String newPetTypeName = "Azure Dragon";

        PetType petType = new PetType();
        petType.setPetTypeID(newPetTypeID);
        petType.setPetType(newPetTypeName);

        given().contentType(ContentType.JSON)
                .body(petType)
                .when().put("/v1/pets/types/update")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void testPetTypeDeleteEndpoint() {

        given()
                .pathParam("petTypeId", 2)
                .when().delete("/v1/pets/types/delete/{petTypeId}")
                .then()
                .statusCode(200);

    }

    @Test
    @Order(5)
    public void testInvalidPetTypeDeleteEndpoint() {

        given()
                .pathParam("petTypeId", 2654)
                .when().delete("/v1/pets/types/delete/{petTypeId}")
                .then()
                .statusCode(404);

    }

}