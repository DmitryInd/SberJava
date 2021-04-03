package ru.sber;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class OptimalClientReaderTest {
    @Test
    void simpleCreateIndividualClient() {
        IndividualClient myClient;
        try {
            myClient = (IndividualClient) ClientReader.optimalCreateClient(
                    "src/test/resources/IndividualObject.json");
        } catch (IOException e) {
            throw new AssertionFailedError(e.toString());
        }

        assertEquals("Alexander Petrov Sergeevich", myClient.fullName);
        assertEquals(new BigInteger("38934893048"), myClient.inn);
        assertEquals("Russia", myClient.country);
        assertTrue(myClient.isSanctioned);
        assertEquals(new BigInteger("2939420211"), myClient.passportInfo);
    }

    @Test
    void simpleCreateLegalEntityClient() {
        LegalEntityClient myClient;
        try {
            myClient = (LegalEntityClient) ClientReader.optimalCreateClient(
                    "src/test/resources/LegalEntityObject.json");
        } catch (IOException e) {
            throw new AssertionFailedError(e.toString());
        }

        assertEquals("ООО Матрешка", myClient.name);
        assertEquals(new BigInteger("13242352"), myClient.inn);
        assertTrue(myClient.isSanctioned);
    }

    @Test
    void simpleCreateHoldingClient() {
        HoldingClient myClient;
        try {
            myClient = (HoldingClient) ClientReader.optimalCreateClient(
                    "src/test/resources/HoldingObject.json");
        } catch (IOException e) {
            throw new AssertionFailedError(e.toString());
        }

        assertEquals("ООО Mail Group", myClient.name);
        assertEquals("Contractual", myClient.type);
        assertFalse(myClient.isSanctioned);
    }

    @Test
    void createWrongClientType() {
        try {
            ClientReader.optimalCreateClient("src/test/resources/CountryObject.json");
        } catch (IllegalArgumentException ignored) {
            return;
        } catch (IOException e) {
            throw new AssertionFailedError(e.toString());
        }

        throw new AssertionFailedError("Function uses wrong type of client without exceptions");
    }

    @Test
    void createWrongPath() {
        try {
            ClientReader.optimalCreateClient("src/test/resources/WrongPath.json");
        } catch (IOException ignored) {
            return;
        }

        throw new AssertionFailedError("Function uses wrong path without exceptions");
    }
}
