package com.example.chapter2.sample1;

import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // アクセプタースレッドの数
        int acceptors = 1;

        // セレクターの数
        int selectors = 1;

        // ServerConnectorインスタンスを作成します。
        ServerConnector connector = new ServerConnector(server, acceptors, selectors, new HttpConnectionFactory());

        // リッスンするTCPポート
        connector.setPort(8080);
        // バインドするTCPアドレス
        connector.setHost("127.0.0.1");
        // TCP受け入れキューサイズ
        connector.setAcceptQueueSize(128);

        server.addConnector(connector);
        server.start();
    }

}
