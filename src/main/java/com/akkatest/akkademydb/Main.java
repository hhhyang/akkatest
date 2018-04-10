
package com.akkatest.akkademydb;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("akkademy");
        ActorRef actorRef = system.actorOf(Props.create(Akkademydb.class).withRouter(new RoundRobinPool(2)),
                "akkademydb");
    }
}
