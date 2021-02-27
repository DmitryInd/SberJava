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
        HashSet<Car> cars = brandCars.get(brand);
        return cars == null ? new HashSet<>() : cars;
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
        HashSet<Car> cars = ownerCars.get(owner.getId());
        return cars == null ? new HashSet<>() : cars;
    }

    /**
     * @param brand
     * @return mean value of owner age that has cars with given brand
     */
    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        if (!brandCars.containsKey(brand)) return 0;
        int meanValue = 0;
        HashSet<Owner> consideredOwners = new HashSet<>();
        for(Car car: brandCars.get(brand)) {
            Owner owner = ownersTable.get(car.getOwnerId());
            if (!consideredOwners.contains(owner)) {
                consideredOwners.add(owner);
                meanValue += owner.getAge();
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
        if (!carsTable.containsKey(carId)) {
            throw new AssertionError("There isn't car with this id in garage");
        }
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
        if (carsTable.containsKey(car.getCarId())) {
            throw new AssertionError("Car with this id had been already added to garage.");
        }

        carsTable.put(car.getCarId(), car);
        ownersTable.put(car.getOwnerId(), owner);

        powerCarsList.add(car);
        velocityCarsList.add(car);

        ownerCars.compute(car.getOwnerId(), (k,v) -> addCarToMap(car, v));
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

    private HashMap<Integer, Car> carsTable = new HashMap<>();
    private HashMap<Integer, Owner> ownersTable = new HashMap<>();

    private HashMap<Integer, HashSet<Car>> ownerCars = new HashMap<>();
    private HashMap<String, HashSet<Car>> brandCars = new HashMap<>();

    private TreeSet<Car> powerCarsList = new TreeSet<>(
            (obj1, obj2) -> obj2.getPower() - obj1.getPower());
    private TreeSet<Car> velocityCarsList = new TreeSet<>(
            (obj1, obj2) -> obj2.getMaxVelocity() - obj1.getMaxVelocity());
}
