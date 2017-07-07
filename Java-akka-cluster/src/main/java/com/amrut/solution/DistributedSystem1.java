package com.amrut.solution;

import java.io.File;
import java.net.URL;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Hello world!
 */
public class DistributedSystem1 {

    public static void main(String[] args) {

        URL resource = DistributedSystem1.class.getClassLoader().getResource("applicationDistributed.conf");
        File configFile = new File(resource.getPath());
        Config config =
            ConfigFactory
                .parseString("akka.remote.netty.tcp.port=2551")
                .withFallback(ConfigFactory.parseFile(configFile));

        ActorSystem system1 = ActorSystem.create("DistributedCluster", config);

        ActorRef actorOf = system1.actorOf(Props.create(MyActor.class), "MyfirstActor");

        // actorOf.tell("Hello", ActorRef.noSender());
    }
}
