package com.askarakhmetov.java.multithreading.sample3;

import java.util.UUID;

public class Message {

    private UUID id;
    private String payload;

    public Message(UUID id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public UUID getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", payload='" + payload + '\'' +
                '}';
    }
}
