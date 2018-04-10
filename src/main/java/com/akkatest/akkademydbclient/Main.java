
package com.akkatest.akkademydbclient;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {

        JClient jClient = new JClient("127.0.0.1:2553");

        jClient.set("1", "abc");

        jClient.set("2", "efg");

        jClient.set("3", 4L);

        CompletableFuture<String> f = (CompletableFuture) jClient.get("2");

        try {
            String r = f.get();
            logger.info("result: " + r);
        } catch (Exception e) {
            //
        }

    }

}
