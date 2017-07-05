package com.amrut.solution.external;

import java.io.File;
import java.net.URL;

import com.amrut.solution.MyActor;
import com.amrut.solution.System1;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;

public class ClusterSystem {

    public static void main(String[] args) {

        URL resource = System1.class.getClassLoader().getResource("applicationReception.conf");
        File f = new File(resource.getPath());
        Config arg1 = ConfigFactory.parseFile(f);
        ActorSystem clusterSystem = ActorSystem.create("ClusterSystem", arg1);

        ActorRef actor = clusterSystem.actorOf(Props.create(MyActor.class), "myClusterActor");
        ClusterClientReceptionist.get(clusterSystem).registerService(actor);

    }

}
