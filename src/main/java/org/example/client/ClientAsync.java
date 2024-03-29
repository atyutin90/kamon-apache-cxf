package org.example.client;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import kamon.Kamon;
import org.example.server.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientAsync {

    private final static Logger log = LoggerFactory.getLogger(ClientAsync.class);

    public static void main(String[] args) {
        log.info("Start client");
        Config config = ConfigFactory.load();
        Kamon.init(config);

        HelloWorld helloWorld = HelloWorldClient.create("http://localhost:9000/HelloWorld", false);
        helloWorld.sayHi("Mike");
        Kamon.stop();
        log.info("Finish client");
    }
}
