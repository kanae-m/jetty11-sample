package com.example.chapter3.section2.item4.sample2;

import com.example.chapter3.section2.item2.RESTHandler;
import com.example.chapter3.section2.item2.ShopHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();

        // コンテキストを保持するContextHandlerCollectionを作成します。
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();
        // ContextHandlerCollectionをサーバーにリンクします。
        server.setHandler(contextCollection);

        // ショップWebアプリケーションのコンテキストを作成します。
        ContextHandler shopContext = new ContextHandler("/shop");
        shopContext.setHandler(new ShopHandler());

        // ショップのWebアプリケーションだけをgzip化したい。
        GzipHandler shopGzipHandler = new GzipHandler();
        shopGzipHandler.setHandler(shopContext);

        // ContextHandlerCollectionに追加します。
        contextCollection.addHandler(shopGzipHandler);

        // API ウェブアプリケーションのコンテキストを作成します。
        ContextHandler apiContext = new ContextHandler("/api");
        apiContext.setHandler(new RESTHandler());

        // ContextHandlerCollection に追加します。
        contextCollection.addHandler(apiContext);
    }

}
