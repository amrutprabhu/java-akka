package com.amrut.solution.external;

import java.io.File;
import java.net.URL;

import com.amrut.solution.MyActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;

public class ClusterSystem {

    public static void main(String[] args) {

        URL resource = ClusterSystem.class.getClassLoader().getResource("applicationReception.conf");
        File configFile = new File(resource.getPath());
        Config config = ConfigFactory.parseFile(configFile);

        ActorSystem clusterSystem = ActorSystem.create("ClusterSystem", config);

        ActorRef actor = clusterSystem.actorOf(Props.create(MyActor.class), "myClusterActor");
        ClusterClientReceptionist.get(clusterSystem).registerService(actor);
    }

}
