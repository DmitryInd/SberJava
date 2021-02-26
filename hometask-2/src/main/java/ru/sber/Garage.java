package ru.sber;

import java.util.Collection;

public interface Garage {

    <Owner> Collection<Owner> allCarsUniqueOwners();

    /**
     * Complexity should be less than O(n)
     */
    <Car> Collection<Car> topThreeCarsByMaxVelocity();

    /**
     * Complexity should be O(1)
     */
    <Car> Collection<Car> allCarsOfBrand(String brand);

    /**
     * Complexity should be less than O(n)
     */
    <Car> Collection<Car> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    <Car, Owner> Collection<Car> allCarsOfOwner(Owner owner);

    /**
     * @return mean value of owner age that has cars with given brand
     */
    int meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    int meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    <Car> Car removeCar(int carId);

    /**
     * Complexity should be less than O(n)
     */
    <Car, Owner> void addNewCar(Car car, Owner owner);
}