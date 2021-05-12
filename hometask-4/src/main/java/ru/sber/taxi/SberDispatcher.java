package ru.sber.taxi;

import ru.sber.log.Log;

import java.util.Queue;

public class SberDispatcher implements Dispatcher {
    private final Queue<Taxi> freeCars;
    private final Log out;

    public SberDispatcher(Queue<Taxi> freeCars, Log out) {
        this.freeCars = freeCars;
        this.out = out;
    }

    public void addTaxi(Queue<Taxi> freeCars) {
        synchronized (this.freeCars) {
            out.log("Добавил свободные машины");
            this.freeCars.addAll(freeCars);
            this.freeCars.notifyAll();
        }
    }

    @Override
    public void notifyAvailable(Taxi taxi) {
        synchronized (freeCars) {
            out.log("Зафиксировал свободную машину");
            freeCars.add(taxi);
            freeCars.notifyAll();
        }
    }

    @Override
    public void run() {
        while(true) {
            Taxi chosenCar;
            synchronized (freeCars) {
                while(freeCars.isEmpty()) {
                    try {
                        freeCars.wait();
                    } catch (InterruptedException ignore) {}
                }

                out.log("Передал заказ");
                chosenCar = freeCars.remove();
            }
            chosenCar.placeOrder(new Order());
        }
    }
}
