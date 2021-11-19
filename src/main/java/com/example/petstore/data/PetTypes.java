package com.example.petstore.data;

import com.example.petstore.Pet;
import com.example.petstore.PetType;

import java.util.ArrayList;

public class PetTypes {

    private static ArrayList<PetType> petTypes = new ArrayList<>();

    private static PetTypes PetTypesInstance = new PetTypes();

    PetTypes(){

        PetType petType1 = new PetType();
        petType1.setPetTypeID(1);
        petType1.setPetType("Dog");

        PetType petType2 = new PetType();
        petType2.setPetTypeID(2);
        petType2.setPetType("Cat");

        PetType petType3 = new PetType();
        petType3.setPetTypeID(3);
        petType3.setPetType("Bird");

        PetType petType4 = new PetType();
        petType4.setPetTypeID(4);
        petType4.setPetType("Dragon");

        petTypes.add(petType1);
        petTypes.add(petType2);
        petTypes.add(petType3);
        petTypes.add(petType4);

    }

    public static PetTypes getInstance() { return PetTypesInstance; }

    public static ArrayList<PetType> getPetTypes() { return petTypes; }

    public PetType addPetType( PetType petType ) {
        petTypes.add(petType);
        return petType;
    }

    public PetType updatePetType ( PetType petTypeToUpdate )
    {
        int index = -1;

        for ( PetType petType : petTypes ) {
            if ( petType.getPetTypeID() == petTypeToUpdate.getPetTypeID()) {
                index = petTypes.indexOf(petType);
                break;
            }
        }

        if ( index != -1 ) {
            petTypes.set(index, petTypeToUpdate);

            return petTypeToUpdate;
        }

        return null;
    }

    public static int removePetType ( int petTypeID ) {
        int index = -1;

        for ( PetType petType : petTypes ) {
            if( petType.getPetTypeID().equals(petTypeID)) {
                index = petTypes.indexOf(petType);
                break;
            }
        }

        if ( index != -1 ) {
            petTypes.remove(index);
        }

        return index;
    }
}
