package ru.sber.log;

public class ConsoleLog implements Log {
    @Override
    public void log(String log) {
        System.out.println(log);
    }
}
