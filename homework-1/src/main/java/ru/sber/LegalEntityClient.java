package ru.sber;

import java.math.BigInteger;

public class LegalEntityClient implements Client {
    public String name; //наименование
    public BigInteger inn; //инн
    public boolean isSanctioned; //признак принадлежности к санкционным клиентам
}