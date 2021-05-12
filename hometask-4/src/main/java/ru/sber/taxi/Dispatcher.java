package ru.sber.taxi;

public interface Dispatcher extends Runnable{
    void notifyAvailable(Taxi taxi);

    void run();
}
