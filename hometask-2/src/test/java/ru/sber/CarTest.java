package ru.sber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testHashCode() {
        testHashCodeSameCars(100,
                "Hyundai",
                "off-road",
                200,
                400,
                8923243);
        testHashCodeSameCars(23243,
                "Toyota",
                "X3",
                240,
                300,
                34738);
    }

    @Test
    void testEquals() {
        testEqualsSameCars(100,
                "Hyundai",
                "off-road",
                200,
                400,
                8923243);
        testEqualsSameCars(23243,
                "Toyota",
                "X3",
                240,
                300,
                34738);
        testEqualsDifferentCars(100,
                "Hyundai",
                "off-road",
                200,
                400,
                8923243);
        testEqualsDifferentCars(23243,
                "Toyota",
                "X3",
                240,
                300,
                34738);
    }

    @Test
    void testGetters() {
        Car obj = new Car(23243,
                "Toyota",
                "X3",
                240,
                300,
                34738);

        assertEquals(obj.getCarId(), 23243);
        assertEquals(obj.getBrand(), "Toyota");
        assertEquals(obj.getModelName(), "X3");
        assertEquals(obj.getMaxVelocity(), 240);
        assertEquals(obj.getOwnerId(), 34738);
    }

    private void testHashCodeSameCars(int carId,
                                      String brand,
                                      String modelName,
                                      int maxVelocity,
                                      int power,
                                      int ownerId) {

        Car obj1 = new Car(carId,
                brand,
                modelName,
                maxVelocity,
                power,
                ownerId);

        Car obj2 = new Car(carId,
                brand,
                modelName,
                maxVelocity,
                power,
                ownerId);

        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    private void testEqualsSameCars(int carId,
                                    String brand,
                                    String modelName,
                                    int maxVelocity,
                                    int power,
                                    int ownerId) {

        Car obj1 = new Car(carId,
                brand,
                modelName,
                maxVelocity,
                power,
                ownerId);

        Car obj2 = new Car(carId,
                brand,
                modelName,
                maxVelocity,
                power,
                ownerId);

        assertEquals(obj1, obj2);
    }

    private void testEqualsDifferentCars(int carId,
                                         String brand,
                                         String modelName,
                                         int maxVelocity,
                                         int power,
                                         int ownerId) {

        Car obj1 = new Car(carId,
                brand,
                modelName,
                maxVelocity,
                power,
                ownerId);

        Car obj2 = new Car(carId + 3435,
                brand + "_country",
                modelName + "model",
                maxVelocity - 30,
                power - 34,
                ownerId + 43937439);

        assertNotEquals(obj1, obj2);
    }
}