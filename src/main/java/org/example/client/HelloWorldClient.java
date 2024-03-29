package org.example.client;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.http.asyncclient.AsyncHTTPConduit;
import org.apache.cxf.transport.http.asyncclient.AsyncHttpTransportFactory;
import org.example.server.HelloWorld;

public class HelloWorldClient {
    public static HelloWorld create(String address, Boolean async) {
        LogUtils.setLoggerClass(org.apache.cxf.common.logging.Slf4jLogger.class);

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (async) factory.setBus(createBus());
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress(address);
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        HelloWorld client = (HelloWorld) factory.create();

        return client;
    }

    private static Bus createBus() {
        Bus bus = BusFactory.newInstance().createBus();
        AsyncHttpTransportFactory asyncTransport = new AsyncHttpTransportFactory();
        ConduitInitiatorManager cim = bus.getExtension(ConduitInitiatorManager.class);
        cim.registerConduitInitiator("http://cxf.apache.org/transports/http", asyncTransport);
        cim.registerConduitInitiator("http://cxf.apache.org/transports/https", asyncTransport);
        cim.registerConduitInitiator("http://cxf.apache.org/transports/http/configuration", asyncTransport);
        cim.registerConduitInitiator("http://cxf.apache.org/transports/https/configuration", asyncTransport);
        bus.setProperty(AsyncHTTPConduit.USE_ASYNC, Boolean.TRUE);
        return bus;
    }
}