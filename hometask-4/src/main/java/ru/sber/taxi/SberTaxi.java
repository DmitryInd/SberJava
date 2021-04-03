package ru.sber.taxi;

import ru.sber.log.Log;

public class SberTaxi implements Taxi {
    private final String id;
    private final Dispatcher dispatcher;
    private final Log out;
    private Order myOrder;

    public SberTaxi(String id, Dispatcher dispatcher, Log out) {
        this.id = id;
        this.dispatcher = dispatcher;
        this.out = out;
    }

    @Override
    public synchronized void run() {
        while (true) {
            while (myOrder == null) {
                try {
                    this.wait();
                } catch (InterruptedException ignore) {
                }
            }

            out.log(id + ": выполняю заказ");
            try {
                Thread.sleep((int) (Math.random()* 2500L) + 2500);
            } catch (InterruptedException ignore) {}

            myOrder = null;
            dispatcher.notifyAvailable(this);
        }
    }

    @Override
    public synchronized void placeOrder(Order order) {
        out.log(id + ": взял заказ");
        myOrder = order;
        this.notify();
    }
}
