
package com.akkatest.akkademydbclient;

import java.util.concurrent.CompletionStage;

import com.akkatest.akkademydb.SetRequest;
import com.akkatest.akkademydb.GetRequest;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;

import static scala.compat.java8.FutureConverters.toJava;


public class JClient {

    private final ActorSystem system = ActorSystem.create("localsystem");

    private final ActorSelection remoteDb;

    public JClient(String remoteAddrees) {
        remoteDb = system.actorSelection("akka.tcp://akkademy@" + remoteAddrees + "/user/akkademydb");
    }

    public CompletionStage set(String key, Object value) {

        return toJava(Patterns.ask(remoteDb, new SetRequest(key, value), 2000L));
    }

    public CompletionStage get(String key) {
        return toJava(Patterns.ask(remoteDb, new GetRequest(key), 2000L));
    }

}
