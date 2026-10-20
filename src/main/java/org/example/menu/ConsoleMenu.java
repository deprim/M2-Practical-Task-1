package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    private final List<MenuOption> menuOptions = List.of(
            new MenuOption(1, "Add animal"),
            new MenuOption(2, "List all animals"),
            new MenuOption(3, "Find animals by species"),
            new MenuOption(4, "List available animals"),
            new MenuOption(5, "Mark animal as adopted"),
            new MenuOption(0, "Exit")
    );

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){

        // separate methods were implemented for each menu option for more readable code

        while(true){
            printMenu();

            if (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    handleAnimalMenuOption();
                break;
                case 2:
                    displayAllAnimalsMenuOption();
                break;
                case 3:
                    findAnimalsBySpeciesMenuOption();
                break;
                case 4:
                    displayAvailableAnimalsMenuOption();
                break;
                case 5:
                    markAnimalAsAdoptedMenuOption();
                break;
                case 0: return;
                default: System.out.println("Invalid menu option.");
            }
        }
    }

    private void printMenu(){
       for (MenuOption option : menuOptions) {
           System.out.println(option.number() + ". " + option.label());
       }
    }

    private void handleAnimalMenuOption(){


        String animalSpecies = readAnimalSpecies();
        String animalName = readAnimalName();
        int animalAge = readAnimalAge();
        Animal animal = createAnimal(animalSpecies, animalName, animalAge);
        shelter.addAnimal(animal);
        System.out.println("Animal added successfully!");

    }

    private String readAnimalSpecies(){
        System.out.println("Enter animal type(Dog, Cat, Bird, Spider): ");

        String animalSpecies = scanner.nextLine().trim();
        
        if(!animalSpecies.equalsIgnoreCase("Dog") &&
                !animalSpecies.equalsIgnoreCase("Cat") &&
                !animalSpecies.equalsIgnoreCase("Bird") &&
                !animalSpecies.equalsIgnoreCase("Spider")){
            System.out.println("Invalid animal type");
            return readAnimalSpecies();
        }
        
        return animalSpecies;

    }

    private String readAnimalName(){
        System.out.println("Enter animal name: ");
        String animalName = scanner.nextLine().trim();
        if (animalName.isEmpty()){
            System.out.println("Invalid name. Name cannot be empty");
            return readAnimalName();
        }
        return animalName;
    }

    private int readAnimalAge(){
        System.out.println("Enter animal age: ");
        if(!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("Invalid age. Age must be a positive number");
            readAnimalAge();
        }

        int animalAge = scanner.nextInt();
        scanner.nextLine();

        if (animalAge < 0){
            System.out.println("Invalid age. Age must be a positive number");
            readAnimalAge();
        }

        return animalAge;
    }

    private Animal createAnimal(String animalSpecies, String animalName, int animalAge){
        return switch (animalSpecies.toLowerCase()) {
            case "dog" -> new Dog(animalName, animalAge);
            case "cat" -> new Cat(animalName, animalAge);
            case "bird" -> new Bird(animalName, animalAge);
            case "spider" -> new Spider(animalName, animalAge);
            default -> throw new IllegalArgumentException("Invalid animal type");
        };

    }

    private void displayAllAnimalsMenuOption(){
        List<Animal> animalList = shelter.getAllAnimals();
        for (Animal animal : animalList) { // for loop for more readable output (line by line)
            System.out.println(animal);
        }
    }

    private void findAnimalsBySpeciesMenuOption(){
        System.out.println("Enter species(Cat, Dog, Bird, Spider): ");
        String species = scanner.nextLine().trim();
        List<Animal> animalsBySpecies = shelter.findBySpecies(species);

        if (!animalsBySpecies.isEmpty()){
            for (Animal animal : animalsBySpecies) {
                System.out.println(animal);
            }
        } else {
            System.out.println("No animals of that species found.");
        }


    }

    private void displayAvailableAnimalsMenuOption(){
        List<Animal> availableAnimals = shelter.findAvailableAnimals();
        for (Animal animal : availableAnimals) {
            System.out.println(animal);
        }
    }

    private void markAnimalAsAdoptedMenuOption(){
        System.out.println("Enter animal ID: ");
        String animalId = scanner.nextLine().trim();
        shelter.markAsAdopted(animalId);
    }



}
