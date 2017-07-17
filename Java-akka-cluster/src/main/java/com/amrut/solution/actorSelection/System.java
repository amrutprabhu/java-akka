package com.amrut.solution.actorSelection;

import java.io.File;
import java.net.URL;

import com.amrut.solution.cluster.external.ClusterSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {

    public static void main(String[] args) {

        URL resource = ClusterSystem.class.getClassLoader().getResource("applicationSelection.conf");
        File configFile = new File(resource.getPath());
        Config config = ConfigFactory.parseFile(configFile);
        ActorSystem system1 = ActorSystem.create("selectionSystem", config);

        ActorRef actorOf = system1.actorOf(Props.create(System1Actor.class), "system1actor");
    }

}
