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

        assertEquals("Alexander Petrov Sergeevich", myClient.getFullName());
        assertEquals(new BigInteger("38934893048"), myClient.getInn());
        assertEquals("Russia", myClient.getCountry());
        assertTrue(myClient.isSanctioned());
        assertEquals(new BigInteger("2939420211"), myClient.getPassportInfo());
    }

    @Test
    public void simpleCreateLegalEntityClient() throws IOException {
        LegalEntityClient myClient = (LegalEntityClient) ClientReader.optimalCreateClient(
                    "src/test/resources/LegalEntityObject.json");

        assertEquals("ООО Матрешка", myClient.getName());
        assertEquals(new BigInteger("13242352"), myClient.getInn());
        assertTrue(myClient.isSanctioned());
    }

    @Test
    public void simpleCreateHoldingClient() throws IOException {
        HoldingClient myClient = (HoldingClient) ClientReader.optimalCreateClient(
                "src/test/resources/HoldingObject.json");

        assertEquals("ООО Mail Group", myClient.getName());
        assertEquals("Contractual", myClient.getType());
        assertFalse(myClient.isSanctioned());
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
