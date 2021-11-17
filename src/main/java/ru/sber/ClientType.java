package ru.sber;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public enum ClientType {
    INDIVIDUAL {
        @Override
        public IndividualClient createClient(JsonObject jsonObject) {
            Gson gson = new Gson();
            return gson.fromJson(jsonObject, IndividualClient.class);
        }
    },
    LEGAL_ENTITY {
        @Override
        public LegalEntityClient createClient(JsonObject jsonObject) {
            Gson gson = new Gson();
            return gson.fromJson(jsonObject, LegalEntityClient.class);
        }
    },
    HOLDING {
        @Override
        public HoldingClient createClient(JsonObject jsonObject) {
            Gson gson = new Gson();
            return gson.fromJson(jsonObject, HoldingClient.class);
        }
    };

    abstract public Client createClient(JsonObject jsonObject);
}
