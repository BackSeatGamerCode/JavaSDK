package com.backseatgamer.javasdk.models;

public record Redemption(String command, String name, String guest) {

    @Override
    public String toString() {
        return "Redemption{" +
                "command='" + command + '\'' +
                ", name='" + name + '\'' +
                ", guest='" + guest + '\'' +
                '}';
    }
}
