package ru.sber;

import ru.sber.log.ConsoleLog;
import ru.sber.log.Log;
import ru.sber.taxi.SberDispatcher;
import ru.sber.taxi.SberTaxi;
import ru.sber.taxi.Taxi;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class CabCompany {
    public static void main(String[] args) {
        Log log = new ConsoleLog();
        SberDispatcher dispatcher = new SberDispatcher(new ArrayDeque<>(), log);
        Queue<Taxi> freeCars = new ArrayDeque<>(Arrays.asList(
                new SberTaxi("1", dispatcher, log),
                new SberTaxi("2", dispatcher, log),
                new SberTaxi("3", dispatcher, log),
                new SberTaxi("4", dispatcher, log)
        ));
        dispatcher.addTaxi(freeCars);


        for (var taxi: freeCars) {
            launchDaemonThread(new Thread(taxi));
        }
        launchDaemonThread(new Thread(dispatcher));
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ignore) {}
    }

    private static void launchDaemonThread(Thread thread) {
        thread.setDaemon(true);
        thread.start();
    }
}
