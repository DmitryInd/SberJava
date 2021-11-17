package ru.sber;

import java.math.BigInteger;

public class LegalEntityClient implements Client {
    private final String name;
    private final BigInteger inn;
    private final boolean isSanctioned;

    public LegalEntityClient(String name, BigInteger inn, boolean isSanctioned) {
        this.name = name;
        this.inn = inn;
        this.isSanctioned = isSanctioned;
    }

    public String getName() {
        return name;
    }

    public BigInteger getInn() {
        return inn;
    }

    public boolean isSanctioned() {
        return isSanctioned;
    }
}
