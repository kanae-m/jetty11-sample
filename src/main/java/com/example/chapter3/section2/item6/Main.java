package com.example.chapter3.section2.item6;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.StatisticsHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);

        StatisticsHandler statsHandler = new StatisticsHandler();

        // StatisticsHandlerをサーバーにリンクします。
        server.setHandler(statsHandler);

        // コンテキストを保持する ContextHandlerCollection を作成します。
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();
        // ContextHandlerCollection を StatisticsHandler にリンクします。
        statsHandler.setHandler(contextCollection);

        server.start();
    }

}
