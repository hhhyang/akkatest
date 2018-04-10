
package com.akkatest.akkacluster;


import java.util.logging.Logger;

import akka.actor.AbstractActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.UnreachableMember;

import akka.japi.pf.ReceiveBuilder;

public class ClusterController extends AbstractActor {

    private static final Logger LOG = Logger.getLogger("ClusterController");

    Cluster cluster = Cluster.get(getContext().system());

    @Override
    public void preStart() throws Exception {
        cluster.subscribe(self(), ClusterEvent.initialStateAsEvents(), MemberEvent.class, UnreachableMember.class);
    }

    @Override
    public void postStop() throws Exception {
        cluster.unsubscribe(self());
    }

    @Override
    public Receive createReceive() {
        ReceiveBuilder builder = ReceiveBuilder.create();
        builder.match(MemberEvent.class, message -> {
            LOG.info("MemberEvent event: " + message);
        }).match(UnreachableMember.class, message -> {
            LOG.info("UnreachableMember event: " + message);
        });

        return builder.build();
    }
}
