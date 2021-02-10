package com.example.chapter2.section1.item1;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // HTTP設定オブジェクト
        HttpConfiguration httpConfig = new HttpConfiguration();
        // HTTPサポートを設定する
        httpConfig.setSendServerVersion(false);

        // HTTP/1.1のConnectionFactory
        HttpConnectionFactory http11 = new HttpConnectionFactory(httpConfig);

        // ServerConnectorを作成します
        ServerConnector connector = new ServerConnector(server, http11);
        connector.setPort(8080);

        server.addConnector(connector);
        server.start();
    }

}
