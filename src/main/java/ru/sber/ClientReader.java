package ru.sber;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class ClientReader {
    public static Client optimalCreateClient(String jsonPath) throws IOException {
        JsonObject client = getJsonObject(jsonPath);
        String clientType = client.get("clientType").getAsString();
        ClientType myClientType = ClientType.valueOf(clientType);
        return myClientType.createClient(client);
    }

    private static JsonObject getJsonObject(String jsonPath) throws IOException {
        FileReader jsonReader = new FileReader(jsonPath);
        JsonObject client = JsonParser.parseReader(jsonReader).getAsJsonObject();
        jsonReader.close();
        return client;
    }
}
