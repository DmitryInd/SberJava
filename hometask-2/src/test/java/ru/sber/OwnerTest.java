package ru.sber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void testHashCode() {
        testHashCodeSameOwners("Ilya", "Socolov", 26, 90453);
        testHashCodeSameOwners("Dmitry", "Leonov", 31, 45147);
    }

    @Test
    void testEquals() {
        testEqualsSameOwners("Ilya", "Socolov", 26, 90453);
        testEqualsSameOwners("Dmitry", "Leonov", 31, 45147);

        testEqualsDifferentOwners("Ilya", "Socolov", 26, 90453);
        testEqualsDifferentOwners("Dmitry", "Leonov", 51, 45147);
    }

    @Test
    void testGetters() {
        Owner obj = new Owner("Ilya", "Socolov", 26, 90453);

        assertEquals(obj.getName(), "Ilya");
        assertEquals(obj.getLastName(), "Socolov");
        assertEquals(obj.getAge(), 26);
        assertEquals(obj.getId(), 90453);
    }

    private void testHashCodeSameOwners(String name, String lastName, int age, int id) {

        Owner obj1 = new Owner(name, lastName, age, id);
        Owner obj2 = new Owner(name, lastName, age, id);

        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    private void testEqualsSameOwners(String name, String lastName, int age, int id) {
        Owner obj1 = new Owner(name, lastName, age, id);
        Owner obj2 = new Owner(name, lastName, age, id);

        assertEquals(obj1, obj2);
    }

    private void testEqualsDifferentOwners(String name, String lastName, int age, int id) {
        Owner obj1 = new Owner(name, lastName, age, id);
        Owner obj2 = new Owner(name + "_Junior", lastName + "_Senior", age-20, id);

        assertNotEquals(obj1, obj2);
    }
}