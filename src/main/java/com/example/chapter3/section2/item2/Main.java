package com.example.chapter3.section2.item2;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.Callback;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // コンテキストを保持する
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();
        // ContextHandlerCollectionをサーバーにリンクします。
        server.setHandler(contextCollection);

        // ショップWebアプリケーションのコンテキストを作成します。
        ContextHandler shopContext = new ContextHandler("/shop");
        shopContext.setHandler(new ShopHandler());
        // それをContextHandlerCollectionに追加します。
        contextCollection.addHandler(shopContext);

        server.start();

        // APIWebアプリケーションのコンテキストを作成します。
        ContextHandler apiContext = new ContextHandler("/api");
        apiContext.setHandler(new RESTHandler());
        // Webアプリケーションは、サーバーの起動後に展開できます。
        contextCollection.deployHandler(apiContext, Callback.NOOP);
    }

}
