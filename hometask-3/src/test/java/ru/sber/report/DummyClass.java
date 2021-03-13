package ru.sber.report;

public class DummyClass {
    public DummyClass(int id, Integer fieldTwo, String path, String topSecret, Double calculation) {
        this.id = id;
        this.fieldTwo = fieldTwo;
        this.path = path;
        this.topSecret = topSecret;
        this.calculation = calculation;
    }

    public int id;
    @FieldName("Full id")
    public Integer fieldTwo;
    public String path;
    @FieldName("Key word")
    private final String topSecret;
    @FieldName("cvc")
    private final Double calculation;
}
