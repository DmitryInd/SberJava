package ru.sber;

import java.util.Objects;

public class Owner {
    public Owner(String name, String lastName, int age, int id) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id && age == owner.age && Objects.equals(name, owner.name) && Objects.equals(lastName, owner.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age, id);
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    private final String name;
    private final String lastName;
    private final int age;
    private final int id;
}
