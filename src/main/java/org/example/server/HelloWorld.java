package org.example.server;

import org.apache.cxf.annotations.UseAsyncMethod;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWorld {

    String sayHi(@WebParam(name = "text") String text);
}
