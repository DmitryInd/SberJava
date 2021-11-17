package ru.sber;

import java.math.BigInteger;

public class IndividualClient implements Client {
    private final String fullName;
    private final BigInteger inn;
    private final BigInteger passportInfo;
    private final String country;
    private final boolean isSanctioned;

    public IndividualClient(String fullName, BigInteger inn, BigInteger passportInfo, String country, boolean isSanctioned) {
        this.fullName = fullName;
        this.inn = inn;
        this.passportInfo = passportInfo;
        this.country = country;
        this.isSanctioned = isSanctioned;
    }

    public String getFullName() {
        return fullName;
    }

    public BigInteger getInn() {
        return inn;
    }

    public BigInteger getPassportInfo() {
        return passportInfo;
    }

    public String getCountry() {
        return country;
    }

    public boolean isSanctioned() {
        return isSanctioned;
    }
}
