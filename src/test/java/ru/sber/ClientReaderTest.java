package ru.sber;

import org.junit.Test;

public class ClientReaderTest {
    @Test(expected = IllegalStateException.class)
    public void createClientReaderTest(){
        new ClientReader();
    }
}
