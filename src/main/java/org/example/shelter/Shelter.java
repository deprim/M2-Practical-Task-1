package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return List.copyOf(animals); // for protection against accidental modification (incapsulation)
    }

    public List<T> findBySpecies(String species){
        List<T> animalsOfSpecies = new ArrayList<>();

        for (T animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)){
                animalsOfSpecies.add(animal);
            }
        }

        return animalsOfSpecies;

    }

    public List<T> findAvailableAnimals(){
        List<T> availableAnimals = new ArrayList<>();

        for (T animal : animals) {
            if (animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE)){
                availableAnimals.add(animal);
            }
        }

        return availableAnimals;

    }

    public void markAsAdopted(String id){

        for (T animal : animals) {
            if (animal.getId().toString().equals(id) && animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE)){
                animal.markAsAdopted();
                System.out.println("Animal with ID " + animal.getId().toString() +  " marked as adopted");
                return;
            }
        }
        System.out.println("Animal with ID " + id + " not found or already adopted");

    }
}
