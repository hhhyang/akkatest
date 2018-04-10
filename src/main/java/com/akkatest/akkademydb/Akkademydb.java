
package com.akkatest.akkademydb;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;

public class Akkademydb extends AbstractActor {

    private static final Logger logger = Logger.getLogger("Akkademydb");

    Map<String, Object> map = new HashMap<>();

    public void preStart() {
        //System.out.println("start: " + getSelf());
        logger.info("start: " + self());
        logger.info("start path: " + self().path());
    }

    @Override
    public void postStop() {
        System.out.println("stop: " + getSelf());
    }



    @Override
    public AbstractActor.Receive createReceive() {

        ReceiveBuilder builder = ReceiveBuilder.create();
        builder.match(SetRequest.class, message -> {
            logger.info("received set request: " + message + " actor: " + self().path());
            logger.info("map size: " + map.size());
            map.put(message.key, message.value);
            sender().tell(new Status.Success(message.key), self());
        }).match(GetRequest.class, message -> {
            logger.info("received get request: " + message + " actor: " + self().path());
            Object value = map.get(message.key);
            Object response = (value != null) ? value : new Status.Failure(new KeyNotFoundException(message.key));
            sender().tell(response, self());
        }).matchAny(message -> {
            sender().tell(new Status.Failure(new ClassNotFoundException()), self());
        });

        return builder.build();

    }


}
