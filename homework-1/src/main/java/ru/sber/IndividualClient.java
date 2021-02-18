package ru.sber;

import java.math.BigInteger;

public class IndividualClient implements Client {
    public String fullName; //ФИО
    public BigInteger inn; //инн
    public BigInteger passportInfo; //серия и номер паспорта
    public String country; //гражданство
    public boolean isSanctioned; //признак принадлежности к санкционным клиентам
}
