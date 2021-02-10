package com.example.chapter3.section2.item1;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // contextPathを使用してContextHandlerを作成します。
        ContextHandler context = new ContextHandler();
        context.setContextPath("/shop");
        context.setHandler(new ShopHandler());

        // コンテキストをサーバーにリンクします。
        server.setHandler(context);

        server.start();
    }

}
