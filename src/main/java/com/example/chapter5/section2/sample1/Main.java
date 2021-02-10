package com.example.chapter5.section2.sample1;

import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.server.RawHTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        // サーバーインスタンスを作成します。
        Server server = new Server();

        ServerSessionListener sessionListener = new ServerSessionListener.Adapter();

        // RawHTTP2ServerConnectionFactoryでServerConnectorを作成します。
        RawHTTP2ServerConnectionFactory http2 = new RawHTTP2ServerConnectionFactory(sessionListener);

        // RawHTTP2ServerConnectionFactoryを構成します。

        // 同時リクエストの最大数を設定します。
        http2.setMaxConcurrentStreams(128);
        // CONNECT のサポートを有効にします。
        http2.setConnectProtocolEnabled(true);

        // ServerConnectorを作成します。
        ServerConnector connector = new ServerConnector(server, http2);

        // サーバーにコネクタを追加する
        server.addConnector(connector);

        // サーバーを起動して、クライアントからの接続の受け入れを開始します。
        server.start();
    }

}
