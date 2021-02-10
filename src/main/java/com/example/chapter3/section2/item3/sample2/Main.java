package com.example.chapter3.section2.item3.sample2;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.ResourceCollection;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        ResourceHandler handler = new ResourceHandler();

        // 複数のディレクトリの場合はResourceCollectionを使用します。
        @SuppressWarnings("squid:S2095")
        ResourceCollection directories = new ResourceCollection();
        directories.addPath("/path/to/static/resources/");
        directories.addPath("/another/path/to/static/resources/");

        handler.setBaseResource(directories);

        handler.setDirectoriesListed(false);
        handler.setWelcomeFiles(new String[]{"index.html"});
        handler.setAcceptRanges(true);

        server.setHandler(handler);

        server.start();
    }

}
