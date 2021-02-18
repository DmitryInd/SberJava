package ru.sber;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class ClientReader {
    public static Client createClient(String jsonPath) throws IOException {
        FileReader jsonReader = new FileReader(jsonPath);
        JsonObject client = JsonParser.parseReader(jsonReader).getAsJsonObject();
        jsonReader.close();
        String clientType = client.get("clientType").getAsString();
        Gson gson = new Gson();
        return switch (clientType) {
            case "INDIVIDUAL" -> gson.fromJson(client, IndividualClient.class);
            case "LEGAL_ENTITY" -> gson.fromJson(client, LegalEntityClient.class);
            case "HOLDING" -> gson.fromJson(client, HoldingClient.class);
            default -> null;
        };

    }
}
