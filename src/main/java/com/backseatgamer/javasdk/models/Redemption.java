package com.backseatgamer.javasdk.models;

import java.util.Objects;

public final class Redemption {
    private String command;
    private String name;
    private String guest;

    public Redemption(String command, String name, String guest) {
        this.command = command;
        this.name = name;
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Redemption{" +
                "command='" + command + '\'' +
                ", name='" + name + '\'' +
                ", guest='" + guest + '\'' +
                '}';
    }

    public String toMessage() {
        return guest + " has redeemed the reward " + name;
    }

    public String command() {
        return command;
    }

    public String name() {
        return name;
    }

    public String guest() {
        return guest;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Redemption) obj;
        return Objects.equals(this.command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command);
    }

}
