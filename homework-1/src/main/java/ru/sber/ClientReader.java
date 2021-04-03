package ru.sber;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class ClientReader {
    public static Client createClient(String jsonPath) throws IOException {
        JsonObject client = getJsonObject(jsonPath);
        String clientType = client.get("clientType").getAsString();
        Gson gson = new Gson();
        return switch (clientType) {
            case "INDIVIDUAL" -> gson.fromJson(client, IndividualClient.class);
            case "LEGAL_ENTITY" -> gson.fromJson(client, LegalEntityClient.class);
            case "HOLDING" -> gson.fromJson(client, HoldingClient.class);
            default -> null;
        };
    }

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
