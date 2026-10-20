package org.example.model;

// new animal type
public final class Spider extends Animal {

    public Spider(AnimalId id, String name, int age) {
        super(id, name, age);
    }

    // overloading constructor
    public Spider(String name, int age) {
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies() {
        return "Spider";
    }
}
