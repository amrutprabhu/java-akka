package com.amrut.solution.cluster.external;

import java.io.File;
import java.net.URL;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.client.ClusterClient;
import akka.cluster.client.ClusterClientSettings;

public class ExternalClusterSystem {

    public static void main(String[] args) {

        URL resource = ExternalClusterSystem.class.getClassLoader().getResource("applicationClusterClient.conf");
        File configFile = new File(resource.getPath());
        Config systemConfig = ConfigFactory.parseFile(configFile);

        ConfigValue value = systemConfig.getValue("systemName");

        ActorSystem externalSystem = ActorSystem.create(value.unwrapped().toString(), systemConfig);

        // Set<ActorPath> initialContacts =
        // new HashSet<ActorPath>(Arrays.asList(ActorPaths.fromString("akka.tcp://ClusterSystem@127.0.0.1:2551/system/receptionist")));

        ClusterClientSettings settings = ClusterClientSettings.create(externalSystem);
        // .withInitialContacts(initialContacts);
        // initial contacts is taken from the config file

        ActorRef clusterClient = externalSystem.actorOf(ClusterClient.props(settings));

        clusterClient.tell(new ClusterClient.Send("/user/myClusterActor", "Message from External Client"), ActorRef.noSender());

    }

}
