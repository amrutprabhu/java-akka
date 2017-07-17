package com.amrut.solution.actorSelection;

import java.io.File;
import java.net.URL;

import com.amrut.solution.cluster.external.ClusterSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System2 {

    public static void main(String[] args) {

        URL resource = ClusterSystem.class.getClassLoader().getResource("applicationSelection.conf");
        File configFile = new File(resource.getPath());
        Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=2552")
            .withFallback(ConfigFactory.parseFile(configFile));

        ActorSystem system2 = ActorSystem.create("selectionSystem", config);

        String remoteActorPath = "akka.tcp://selectionSystem@127.0.0.1:2551/user/system1actor";

        ActorRef actorCommunicatingWithRemoteActor = system2.actorOf(Props.create(System2Actor.class, remoteActorPath), "system2actor");

        String localActorPath = "/user/system2actor";

        ActorRef actorCommunicatingWithLocalActor = system2.actorOf(Props.create(System2Actor.class, localActorPath), "system2actor2");

    }

}
