package org.acme;

import com.example.petstore.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class PetResourceTest {

	@Test
    @Order(1)
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200)
                .body("petId", hasItem(1))
                .body("petType", hasItem("Dog"))
                .body("petName", hasItem("Boola"))
                .body("petAge", hasItem(3));
    }

    @Test
    @Order(3)
    public void testPetAddEndpoint() {
        Integer newPetAge = 123;
        Integer newPetID = 6;
        String newPetName = "Kaluu";
        String newPetType = "Dragon";

        Pet pet = new Pet();
        pet.setPetId(newPetID);
        pet.setPetAge(newPetAge);
        pet.setPetName(newPetName);
        pet.setPetType(newPetType);

        given().contentType(ContentType.JSON)
                .body(pet)
                .when().post("/v1/pets/add")
                .then()
                .statusCode(200);

    }

    @Test
    @Order(2)
    public  void testPetUpdateEndpoint() {

        Integer newPetID = 2;
        String newPetName = "Kalu Chuti";
        String newPetType = "Cat";
        Integer newPetAge = 12;

        Pet pet = new Pet();
        pet.setPetId(newPetID);
        pet.setPetAge(newPetAge);
        pet.setPetName(newPetName);
        pet.setPetType(newPetType);

        given().contentType(ContentType.JSON)
                .body(pet)
                .when().put("/v1/pets/update")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void testPetDeleteEndpoint() {

        given()
                .pathParam("petId", 2)
                .when().delete("/v1/pets/delete/{petId}")
                .then()
                .statusCode(200);

    }

    @Test
    @Order(5)
    public void testInvalidPetDeleteEndpoint() {

        given()
                .pathParam("petId", 2345)
                .when().delete("/v1/pets/delete/{petId}")
                .then()
                .statusCode(404);

    }

}