package com.example.chapter3.section2.item8;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // HandlerListを作成します。
        HandlerList handlerList = new HandlerList();

        // コンテキストを管理するContextHandlerCollectionを最初に追加します。
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        handlerList.addHandler(contexts);

        // 最後にDefaultHandlerを追加します。
        DefaultHandler defaultHandler = new DefaultHandler();
        handlerList.addHandler(defaultHandler);

        // HandlerList をサーバにリンクします。
        server.setHandler(handlerList);

        server.start();
    }

}
