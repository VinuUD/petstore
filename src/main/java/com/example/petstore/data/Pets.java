package com.example.petstore.data;

import com.example.petstore.Pet;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Pets {
    private static List<Pet> petsList = new ArrayList<Pet>();

    private static Pets petInstance = new Pets();

    Pets(){
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setPetAge(3);
        pet1.setPetName("Boola");
        pet1.setPetType("Dog");

        Pet pet2 = new Pet();
        pet2.setPetId(2);
        pet2.setPetAge(4);
        pet2.setPetName("Sudda");
        pet2.setPetType("Cat");

        Pet pet3 = new Pet();
        pet3.setPetId(3);
        pet3.setPetAge(2);
        pet3.setPetName("Peththappu");
        pet3.setPetType("Bird");

        Pet pet4 = new Pet();
        pet4.setPetId(4);
        pet4.setPetAge(5);
        pet4.setPetName("Littu");
        pet4.setPetType("Cat");

        petsList.add(pet1);
        petsList.add(pet2);
        petsList.add(pet3);
        petsList.add(pet4);

    }

    public static  Pets getInstance() {
        return petInstance;
    }

    public static  List<Pet> getPetList(){
        return petsList;
    }

    public static Pet getPetByID(Integer petID) {

        Pet pet1 = null;

        for (Pet pet : petsList) {
            if (pet.getPetId().equals(petID)) {
                pet1 = pet;
                break;
            }
        }

        return pet1;
    }

    public static Pet getPetByName(String petName) {

        Pet pet1 = null;

        for (Pet pet : petsList) {
            if (pet.getPetName().equalsIgnoreCase(petName)) {
                pet1 = pet;
                break;
            }
        }

        return pet1;
    }

    public static List<Pet> getPetByAge(Integer petAge ) {

        Pet pet1 = null;

        Predicate<Pet> byAge = pet -> pet.getPetAge().equals(petAge);

        List<Pet> result = petsList.stream().filter(byAge).collect(Collectors.toList());

        return result;
    }

    public Pet addPet( Pet pet ) {
        petsList.add(pet);
        return pet;
    }

    public Pet updatePet( Pet petToUpdate ){
        int index = -1;

        for ( Pet pet : petsList ) {
            if ( pet.getPetId().equals(petToUpdate.getPetId())) {
                index = petsList.indexOf(pet);
                break;
            }
        }

        if ( index != -1 ) {
            petsList.set(index, petToUpdate);
            return petToUpdate;
        }

        final Pet o = null;
        return o;
    }

    public static int removePet( int petID ) {
        int index =  -1;

        for ( Pet pet : petsList) {
            if ( pet.getPetId().equals(petID)) {
                index = petsList.indexOf(pet);
                break;
            }
        }

        if ( index != -1) {
            petsList.remove(index);
        }

        return index;
    }
}
