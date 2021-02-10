package com.example.chapter1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class Main {

    public static void main(String[] args) throws Exception {
        // ThreadPoolを作成して設定します。
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("com/example");

        // サーバーインスタンスを作成します。
        Server server = new Server(threadPool);

        // ServerConnectorを作成して、クライアントからの接続を受け入れます。
        Connector connector = new ServerConnector(server);

        // サーバーにコネクタを追加します
        server.addConnector(connector);

        // 単純なハンドラーを設定して、requests/responsesを処理します。
        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) {
                // 他のハンドラーによって処理されないように、リクエストを処理済みとしてマークします。
                jettyRequest.setHandled(true);
            }
        });

        // サーバーを起動して、クライアントからの接続の受け入れを開始します。
        server.start();
    }

}
