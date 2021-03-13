package ru.sber;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ModernGarageTest {

    @Test
    void addNewCar() {
        ModernGarage obj = new ModernGarage();
        obj.addNewCar(car1, owner1);
        obj.addNewCar(car2, owner1);
        assertThrows(AssertionError.class, () -> obj.addNewCar(car2, owner1));
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

        obj.addNewCar(car2, owner1);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));

        obj.addNewCar(car3, owner2);
        expected.add(owner2);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));

        obj.removeCar(car1.getCarId());
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));

        obj.removeCar(car2.getCarId());
        expected.remove(owner1);
        assertEquals(expected, new HashSet<>(obj.allCarsUniqueOwners()));
    }

    @Test
    void topThreeCarsByMaxVelocity() {
        ModernGarage obj = new ModernGarage();
        TreeSet<Car> expected = new TreeSet<>(
                (obj1, obj2) -> obj2.getMaxVelocity() - obj1.getMaxVelocity());

        obj.addNewCar(car1, owner1);
        expected.add(car1);
        assertEquals(new ArrayList<> (expected), obj.topThreeCarsByMaxVelocity());

        obj.addNewCar(car2, owner1);
        expected.add(car2);
        assertEquals(new ArrayList<> (expected), obj.topThreeCarsByMaxVelocity());

        obj.addNewCar(car3, owner2);
        expected.add(car3);
        assertEquals(new ArrayList<> (expected), obj.topThreeCarsByMaxVelocity());

        obj.addNewCar(car4, owner3);
        expected.add(car4);
        expected.remove(expected.last());
        assertEquals(new ArrayList<> (expected), obj.topThreeCarsByMaxVelocity());
    }

    @Test
    void allCarsOfBrand() {
        ModernGarage obj = new ModernGarage();
        HashSet<Car>expected = new HashSet<>();

        obj.addNewCar(car4, owner3);
        assertEquals(expected, obj.allCarsOfBrand("Lada"));

        obj.addNewCar(car2, owner1);
        expected.add(car2);
        assertEquals(expected, obj.allCarsOfBrand("Lada"));

        obj.addNewCar(car3, owner2);
        expected.add(car3);
        assertEquals(expected, obj.allCarsOfBrand("Lada"));

        obj.removeCar(car2.getCarId());
        expected.remove(car2);
        assertEquals(expected, obj.allCarsOfBrand("Lada"));
    }

    @Test
    void carsWithPowerMoreThan() {
        ModernGarage obj = new ModernGarage();
        HashSet<Car>expected = new HashSet<>();

        obj.addNewCar(car2, owner1);
        assertEquals(expected, new HashSet<>(obj.carsWithPowerMoreThan(500)));

        obj.addNewCar(car4, owner3);
        expected.add(car4);
        assertEquals(expected, new HashSet<>(obj.carsWithPowerMoreThan(500)));

        obj.removeCar(car2.getCarId());
        assertEquals(expected, new HashSet<>(obj.carsWithPowerMoreThan(500)));
    }

    @Test
    void allCarsOfOwner() {
        ModernGarage obj = new ModernGarage();
        HashSet<Car>expected = new HashSet<>();

        obj.addNewCar(car3, owner2);
        assertEquals(expected, new HashSet<>(obj.allCarsOfOwner(owner1)));

        obj.addNewCar(car2, owner1);
        expected.add(car2);
        assertEquals(expected, new HashSet<>(obj.allCarsOfOwner(owner1)));

        obj.addNewCar(car1, owner1);
        expected.add(car1);
        assertEquals(expected, new HashSet<>(obj.allCarsOfOwner(owner1)));

        obj.removeCar(car3.getCarId());
        assertEquals(expected, new HashSet<>(obj.allCarsOfOwner(owner1)));

        obj.removeCar(car2.getCarId());
        expected.remove(car2);
        assertEquals(expected, new HashSet<>(obj.allCarsOfOwner(owner1)));
    }

    @Test
    void meanOwnersAgeOfCarBrand() {
        ModernGarage obj = new ModernGarage();

        obj.addNewCar(car4, owner3);
        assertEquals(0, obj.meanOwnersAgeOfCarBrand("Lada"));

        obj.addNewCar(car2, owner1);
        assertEquals(26, obj.meanOwnersAgeOfCarBrand("Lada"));

        obj.addNewCar(car3, owner2);
        assertEquals(28, obj.meanOwnersAgeOfCarBrand("Lada"));

        obj.removeCar(car2.getCarId());
        assertEquals(31, obj.meanOwnersAgeOfCarBrand("Lada"));
    }

    @Test
    void meanCarNumberForEachOwner() {
        ModernGarage obj = new ModernGarage();

        obj.addNewCar(car1, owner1);
        assertEquals(1, obj.meanCarNumberForEachOwner());

        obj.addNewCar(car2, owner1);
        assertEquals(2, obj.meanCarNumberForEachOwner());

        obj.removeCar(car1.getCarId());
        assertEquals(1, obj.meanCarNumberForEachOwner());

        obj.addNewCar(car1, owner1);
        obj.addNewCar(car3, owner2);
        assertEquals(1, obj.meanCarNumberForEachOwner());
    }

    private final Car car1 = new Car(23243,
            "Toyota",
            "X3",
            240,
            300,
            34738);

    private final Car car2 = new Car(100,
            "Lada",
            "off-road",
            200,
            400,
            34738);

    private final Car car3 = new Car(1292,
            "Lada",
            "alive",
            150,
            200,
            8923243);

    private final Car car4 = new Car(34781,
            "Audi",
            "Forth",
            333,
            800,
            423768);

    private final Owner owner1 = new Owner("Ilya", "Socolov", 26, 34738);
    private final Owner owner2 = new Owner("Dmitry", "Leonov", 31, 8923243);
    private final Owner owner3 = new Owner("Ivan", "Ryasonov", 22, 423768);
}