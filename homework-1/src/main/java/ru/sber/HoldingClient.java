package ru.sber;

import java.math.BigInteger;

public class HoldingClient implements Client {
    public String name; //наименование
    public String type; //тип холдинга
    public boolean isSanctioned; //признак принадлежности к санкционным клиентам
}
