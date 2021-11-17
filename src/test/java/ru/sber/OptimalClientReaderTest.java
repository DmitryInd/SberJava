package ru.sber;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class OptimalClientReaderTest {
    @Test
    public void simpleCreateIndividualClient() throws IOException {
        IndividualClient myClient = (IndividualClient) ClientReader.optimalCreateClient(
                "src/test/resources/IndividualObject.json");

        assertEquals("Alexander Petrov Sergeevich", myClient.fullName);
        assertEquals(new BigInteger("38934893048"), myClient.inn);
        assertEquals("Russia", myClient.country);
        assertTrue(myClient.isSanctioned);
        assertEquals(new BigInteger("2939420211"), myClient.passportInfo);
    }

    @Test
    public void simpleCreateLegalEntityClient() throws IOException {
        LegalEntityClient myClient = (LegalEntityClient) ClientReader.optimalCreateClient(
                    "src/test/resources/LegalEntityObject.json");

        assertEquals("ООО Матрешка", myClient.name);
        assertEquals(new BigInteger("13242352"), myClient.inn);
        assertTrue(myClient.isSanctioned);
    }

    @Test
    public void simpleCreateHoldingClient() throws IOException {
        HoldingClient myClient = (HoldingClient) ClientReader.optimalCreateClient(
                "src/test/resources/HoldingObject.json");

        assertEquals("ООО Mail Group", myClient.name);
        assertEquals("Contractual", myClient.type);
        assertFalse(myClient.isSanctioned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWrongClientType() throws IOException {
        ClientReader.optimalCreateClient("src/test/resources/CountryObject.json");
    }

    @Test(expected = IOException.class)
    public void createWrongPath() throws IOException {
        ClientReader.optimalCreateClient("src/test/resources/WrongPath.json");
    }
}
