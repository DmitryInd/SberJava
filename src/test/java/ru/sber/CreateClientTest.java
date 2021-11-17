package ru.sber;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class CreateClientTest {
    @Test
    public void simpleCreateIndividualClient() {
        IndividualClient myClient = new IndividualClient(
                "Alexander Petrov Sergeevich",
                new BigInteger("38934893048"),
                new BigInteger("2939420211"),
                "Russia",
                true
        );

        assertEquals("Alexander Petrov Sergeevich", myClient.getFullName());
        assertEquals(new BigInteger("38934893048"), myClient.getInn());
        assertEquals("Russia", myClient.getCountry());
        assertTrue(myClient.isSanctioned());
        assertEquals(new BigInteger("2939420211"), myClient.getPassportInfo());
    }

    @Test
    public void simpleCreateLegalEntityClient() {
        LegalEntityClient myClient = new LegalEntityClient(
                "ООО Матрешка",
                new BigInteger("13242352"),
                true
        );

        assertEquals("ООО Матрешка", myClient.getName());
        assertEquals(new BigInteger("13242352"), myClient.getInn());
        assertTrue(myClient.isSanctioned());
    }

    @Test
    public void simpleCreateHoldingClient() {
        HoldingClient myClient = new HoldingClient(
                "ООО Mail Group",
                "Contractual",
                false
        );

        assertEquals("ООО Mail Group", myClient.getName());
        assertEquals("Contractual", myClient.getType());
        assertFalse(myClient.isSanctioned());
    }
}
