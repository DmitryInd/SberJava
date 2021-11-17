package ru.sber;

import java.math.BigInteger;

public class LegalEntityClient implements Client {
    public String name;
    public BigInteger inn;
    public boolean isSanctioned;
}
