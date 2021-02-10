package com.example.chapter3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.HandlerWrapper;

public class Main {

    public static void main(String[] args) {
        // サーバーインスタンスを作成する
        Server server = new Server();

        HandlerCollection collection = new HandlerCollection();
        // ルートハンドラーをサーバーにリンクします
        server.setHandler(collection);

        HandlerList list = new HandlerList();
        collection.addHandler(list);
        collection.addHandler(new LoggingHandler());

        list.addHandler(new App1Handler());
        HandlerWrapper wrapper = new HandlerWrapper();
        list.addHandler(wrapper);

        wrapper.setHandler(new App2Handler());
    }

}
