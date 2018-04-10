
package com.akkatest.akkacluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.client.ClusterClientReceptionist;
import akka.cluster.client.ClusterReceptionist;
import akka.routing.BalancingPool;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("akkademy");

        Cluster cluster = Cluster.get(system);

        LOG.info(cluster.getSelfRoles().toString());

        ActorRef clusterController = system.actorOf(Props.create(ClusterController.class), "clusterController");

        ActorRef cmdActor = system.actorOf(Props.create(CmdActor.class), "cmdactor");

        // 注册actor
        ClusterClientReceptionist.get(system).registerService(cmdActor);


        LOG.info(clusterController.path().toString());

        //system.actorOf(Props.create(MsgProc.class), "msgtest");

        // ActorRef workerPool = system.actorOf(new BalancingPool(5).props(Props.create()), "workers");


    }

}
