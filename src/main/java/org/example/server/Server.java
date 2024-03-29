package org.example.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorldImpl();
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://localhost:9000/HelloWorld");
        factory.setServiceBean(helloWorld);
        factory.create();

        System.out.println("Server Run...");

    }

}
