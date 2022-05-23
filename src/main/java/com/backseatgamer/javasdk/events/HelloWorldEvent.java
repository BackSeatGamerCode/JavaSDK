package com.backseatgamer.javasdk.events;

import com.backseatgamer.javasdk.models.Redemption;

public class HelloWorldEvent extends BaseEvent{
    @Override
    public void execute(Object... args) {
        System.out.println("Hello, World!");
    }
}
