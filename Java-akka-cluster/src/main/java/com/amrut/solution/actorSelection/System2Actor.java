package com.amrut.solution.actorSelection;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorIdentity;
import akka.actor.ActorSelection;
import akka.actor.Identify;

public class System2Actor extends AbstractLoggingActor {

    public System2Actor(String actorSelectionPath) {

        ActorSelection actorSelection = context().actorSelection(actorSelectionPath);
        actorSelection.tell(new Identify("me"), self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ActorIdentity.class, msg -> {
            msg.getActorRef()
                .ifPresent(ref -> {
                    log().info("Got Ref -> {}", ref);
                    ref.tell("Hello - its me ", self());
                });
        }).match(String.class, msg -> {
            log().info(msg);
        }).build();
    }

}
