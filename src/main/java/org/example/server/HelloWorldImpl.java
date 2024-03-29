package org.example.server;

import javax.jws.WebService;

@WebService
public class HelloWorldImpl implements HelloWorld {
    public String sayHi(String name) {
        return "Hello " + name + "!";
    }
}
