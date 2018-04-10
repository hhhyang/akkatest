
package com.akkatest.akkacluster;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class CmdActor extends AbstractActor {

    @Override
    public Receive createReceive() {

        ReceiveBuilder builder = ReceiveBuilder.create();

        builder.match(String.class, msg -> {
            System.out.println(msg);
        });

        return builder.build();
    }
}
