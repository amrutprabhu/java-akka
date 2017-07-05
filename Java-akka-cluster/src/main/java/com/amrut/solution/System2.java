package com.amrut.solution;

import java.io.File;
import java.net.URL;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System2 {

    public static void main(String[] args) {
        URL resource = System1.class.getClassLoader().getResource("application.conf");
        File f = new File(resource.getPath());
        Config config =
            ConfigFactory.parseString(
                "akka.remote.netty.tcp.port=2552").withFallback(
                    ConfigFactory.parseFile(f));

        ActorSystem system1 = ActorSystem.create("System1", config);

        ActorRef actorOf = system1.actorOf(Props.create(MyActor.class), "MyfirstActor");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }
}
