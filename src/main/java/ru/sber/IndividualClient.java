package ru.sber;

import java.math.BigInteger;

public class IndividualClient implements Client {
    public String fullName;
    public BigInteger inn;
    public BigInteger passportInfo;
    public String country;
    public boolean isSanctioned;
}
