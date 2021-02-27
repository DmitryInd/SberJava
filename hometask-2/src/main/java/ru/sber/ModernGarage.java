package ru.sber;

import java.util.*;

public class ModernGarage implements Garage{

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return ownersTable.values();
    }

    /**
     * Complexity should be less than O(n)
     */
    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        ArrayList<Car> fastCars = new ArrayList<>();
        int i = 0;
        for (var car : velocityCarsList) {
            if (i >= 3) break;
            i++;
            fastCars.add(car);
        }

        return fastCars;
    }

    /**
     * Complexity should be O(1)
     *
     * @param brand
     */
    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return brandCars.get(brand);
    }

    /**
     * Complexity should be less than O(n)
     *
     * @param power
     */
    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return powerCarsList.headSet(
                new Car(1, "edge", "lower", 0, power, 1)
        );
    }

    /**
     * Complexity should be O(1)
     *
     * @param owner
     */
    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return ownerCars.get(owner.getId());
    }

    /**
     * @param brand
     * @return mean value of owner age that has cars with given brand
     */
    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int meanValue = 0;
        HashSet<Owner> consideredOwners = new HashSet<>();
        for(var entry: brandCars.values()) {
            for (var car : entry) {
                Owner owner = ownersTable.get(car.getOwnerId());
                if (!consideredOwners.contains(owner)) {
                    consideredOwners.add(owner);
                    meanValue += owner.getAge();
                }
            }
        }

        return meanValue/consideredOwners.size();
    }

    /**
     * @return mean value of cars for all owners
     */
    @Override
    public int meanCarNumberForEachOwner() {
        int meanValue = 0;
        for(var entry: ownerCars.values()) {
            meanValue += entry.size();
        }
        return meanValue/ownerCars.size();
    }

    /**
     * Complexity should be less than O(n)
     *
     * @param carId
     * @return removed car
     */
    @Override
    public Car removeCar(int carId) {
        Car car = carsTable.remove(carId);
        powerCarsList.remove(car);

        ownerCars.compute(car.getOwnerId(), (k, v) -> removeCarFromMap(car, v));
        brandCars.compute(car.getBrand(), (k, v) -> removeCarFromMap(car, v));

        if (!ownerCars.containsKey(car.getOwnerId())) {
            ownersTable.remove(car.getOwnerId());
        }

        return car;
    }

    /**
     * Complexity should be less than O(n)
     *
     * @param car
     * @param owner
     */
    @Override
    public void addNewCar(Car car, Owner owner) {
        carsTable.put(car.getCarId(), car);
        ownersTable.put(car.getOwnerId(), owner);

        powerCarsList.add(car);
        velocityCarsList.add(car);

        ownerCars.compute(owner.getId(), (k,v) -> addCarToMap(car, v));
        brandCars.compute(car.getBrand(), (k,v) -> addCarToMap(car, v));
    }

    private HashSet<Car> addCarToMap(Car car, HashSet<Car> v) {
        if (v == null) {
            v = new HashSet<>();
        }
        v.add(car);
        return v;
    }

    private HashSet<Car> removeCarFromMap(Car car, HashSet<Car> v) {
        if (v == null || v.size() == 1) {
            v = null;
        } else {
            v.remove(car);
        }
        return v;
    }

    private HashMap<Integer, Car> carsTable;
    private HashMap<Integer, Owner> ownersTable;

    private HashMap<Integer, HashSet<Car>> ownerCars;
    private HashMap<String, HashSet<Car>> brandCars;

    private TreeSet<Car> powerCarsList = new TreeSet<>(
            (obj1, obj2) -> obj2.getPower() - obj1.getPower());
    private TreeSet<Car> velocityCarsList = new TreeSet<>(
            (obj1, obj2) -> obj2.getMaxVelocity() - obj1.getMaxVelocity());
}
