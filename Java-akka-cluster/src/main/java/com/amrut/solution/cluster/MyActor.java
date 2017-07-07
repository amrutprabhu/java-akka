package com.amrut.solution.cluster;

import akka.actor.AbstractLoggingActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

public class MyActor extends AbstractLoggingActor {

    private Cluster cluster;

    @Override
    public void preStart() throws Exception {
        super.preStart();

        cluster = Cluster.get(getContext().system());

        cluster.subscribe(getSelf(), //
            ClusterEvent.MemberJoined.class, //
            ClusterEvent.MemberWeaklyUp.class, //
            ClusterEvent.MemberLeft.class, //
            ClusterEvent.MemberUp.class,
            ClusterEvent.ReachableMember.class,
            ClusterEvent.UnreachableMember.class);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }

    @Override
    public Receive createReceive() {

        return receiveBuilder()
            .match(String.class, value -> {

                log().info("value - > {}", value);
            })
            .match(ClusterEvent.MemberJoined.class, memberJoined -> {

                log().info("memberJoined - > {}", memberJoined);
            })
            .match(ClusterEvent.MemberWeaklyUp.class, memberWeaklyUp -> {
                log().info("memberWeaklyUp - > {}", memberWeaklyUp);
            })
            .match(ClusterEvent.MemberLeft.class, memberLeft -> {
                log().info("memberLeft - > {}", memberLeft);
            })
            .match(ClusterEvent.MemberUp.class, memberUp -> {
                log().info("memberUp - > {}", memberUp);
            })
            .match(ClusterEvent.ReachableMember.class, reachableMember -> {
                log().info("reachableMember - > {}", reachableMember);
            })
            .match(ClusterEvent.UnreachableMember.class, unreachableMember -> {
                log().info("unreachableMember - > {}", unreachableMember);
            }).build();
    }

}
