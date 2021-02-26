package ru.sber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void testHashCode() {
        testHashCodeSameOwners("Ilya", "Socolov", 26);
        testHashCodeSameOwners("Dmitry", "Leonov", 31);
    }

    @Test
    void testEquals() {
        testEqualsSameOwners("Ilya", "Socolov", 26);
        testEqualsSameOwners("Dmitry", "Leonov", 31);

        testEqualsDifferentOwners("Ilya", "Socolov", 26);
        testEqualsDifferentOwners("Dmitry", "Leonov", 51);
    }

    @Test
    void testGetters() {
        Owner obj = new Owner("Ilya", "Socolov", 26);

        assertEquals(obj.getName(), "Ilya");
        assertEquals(obj.getLastName(), "Socolov");
        assertEquals(obj.getAge(), 26);
    }

    private void testHashCodeSameOwners(String name, String lastName, int age) {

        Owner obj1 = new Owner(name, lastName, age);
        Owner obj2 = new Owner(name, lastName, age);

        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    private void testEqualsSameOwners(String name, String lastName, int age) {
        Owner obj1 = new Owner(name, lastName, age);
        Owner obj2 = new Owner(name, lastName, age);

        assertEquals(obj1, obj2);
    }

    private void testEqualsDifferentOwners(String name, String lastName, int age) {
        Owner obj1 = new Owner(name, lastName, age);
        Owner obj2 = new Owner(name + "_Junior", lastName + "_Senior", age-20);

        assertNotEquals(obj1, obj2);
    }
}