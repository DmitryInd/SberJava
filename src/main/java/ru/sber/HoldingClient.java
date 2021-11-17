package ru.sber;

public class HoldingClient implements Client {
    private final String name;
    private final String type;
    private final boolean isSanctioned;

    public HoldingClient(String name, String type, boolean isSanctioned) {
        this.name = name;
        this.type = type;
        this.isSanctioned = isSanctioned;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isSanctioned() {
        return isSanctioned;
    }
}
