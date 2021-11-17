package ru.sber;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String... args) throws IOException {
        IndividualClient myClient = (IndividualClient) ClientReader.optimalCreateClient(
                "IndividualClientCard.json");

        logger.info("Object with card data: " + myClient.toString());
    }
}