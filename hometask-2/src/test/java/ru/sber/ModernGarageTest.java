package ru.sber;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ModernGarageTest {

    @Test
    void addNewCar() {
        ModernGarage obj = new ModernGarage();
        obj.addNewCar(car1, owner1);
        obj.addNewCar(car2, owner1);
        assertThrows(AssertionError.class, () -> obj.addNewCar(car1, owner2));
    }

    @Test
    void removeCar() {
        ModernGarage obj = new ModernGarage();
        obj.addNewCar(car1, owner1);
        assertThrows(AssertionError.class, () -> obj.removeCar(car2.getCarId()));
        assertEquals(car1, obj.removeCar(car1.getCarId()));
    }

    @Test
    void allCarsUniqueOwners() {
        ModernGarage obj = new ModernGarage();
        HashSet<Owner> expected = new HashSet<>();
        obj.addNewCar(car1, owner1);
        expected.add(owner1);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));
        obj.addNewCar(car2, owner2);
        expected.add(owner2);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));
        obj.removeCar(car1.getCarId());
        expected.remove(owner1);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));
    }

    @Test
    void topThreeCarsByMaxVelocity() {
    }

    @Test
    void allCarsOfBrand() {
    }

    @Test
    void carsWithPowerMoreThan() {
    }

    @Test
    void allCarsOfOwner() {
    }

    @Test
    void meanOwnersAgeOfCarBrand() {
    }

    @Test
    void meanCarNumberForEachOwner() {
    }

    private final Car car1 = new Car(23243,
            "Toyota",
            "X3",
            240,
            300,
            34738);

    private final Car car2 = new Car(100,
            "Hyundai",
            "off-road",
            200,
            400,
            8923243);

    private final Car car3 = new Car(1292,
            "Lada",
            "alive",
            150,
            200,
            823243);

    private final Owner owner1 = new Owner("Ilya", "Socolov", 26, 90453);
    private final Owner owner2 = new Owner("Dmitry", "Leonov", 31, 45147);
}