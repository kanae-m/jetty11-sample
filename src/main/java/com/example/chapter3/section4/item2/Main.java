package com.example.chapter3.section4.item2;

import com.example.chapter3.section4.item1.HelloWorldHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // Handlerをリンクします。
        FilterHandler filter = new FilterHandler();
        filter.setHandler(new HelloWorldHandler());
        server.setHandler(filter);

        server.start();
    }

}
