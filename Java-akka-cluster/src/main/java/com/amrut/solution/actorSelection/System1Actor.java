package com.amrut.solution.actorSelection;

import akka.actor.AbstractLoggingActor;

public class System1Actor extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {

        return receiveBuilder()
            .match(String.class, msg -> {
                log().info(msg);
            }).build();

    }

}
