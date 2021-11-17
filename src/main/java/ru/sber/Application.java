package ru.sber;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        IndividualClient myClient = (IndividualClient) ClientReader.optimalCreateClient(
                "src/test/resources/IndividualObject.json");
    }
}