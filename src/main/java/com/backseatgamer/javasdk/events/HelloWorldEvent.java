package com.backseatgamer.javasdk.events;

import com.backseatgamer.javasdk.models.Redemption;

public class HelloWorldEvent extends BaseEvent{
    @Override
    public void execute(Redemption redemption, Object... args) {
        System.out.println(redemption.toMessage());
    }
}
