package com.example.chapter2.section1.item4;

import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // HTTP設定オブジェクト
        HttpConfiguration httpConfig = new HttpConfiguration();

        // HTTP/1.1のConnectionFactory
        HttpConnectionFactory http11 = new HttpConnectionFactory(httpConfig);

        // クリアテキストHTTP/2用のConnectionFactory
        HTTP2CServerConnectionFactory h2c = new HTTP2CServerConnectionFactory(httpConfig);

        // ServerConnectorインスタンス
        ServerConnector connector = new ServerConnector(server, http11, h2c);
        connector.setPort(8080);

        server.addConnector(connector);
        server.start();
    }

}
