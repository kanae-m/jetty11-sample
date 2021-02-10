package com.example.chapter2.section1.item5;

import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // HTTP設定オブジェクト
        HttpConfiguration httpConfig = new HttpConfiguration();
        // TLSを使用しているため、SecureRequestCustomizerを追加します。
        httpConfig.addCustomizer(new SecureRequestCustomizer());

        // HTTP/1.1のConnectionFactory
        HttpConnectionFactory http11 = new HttpConnectionFactory(httpConfig);

        // HTTP/2のConnectionFactory
        HTTP2CServerConnectionFactory h2 = new HTTP2CServerConnectionFactory(httpConfig);

        // ALPN ConnectionFactory
        ALPNServerConnectionFactory alpn = new ALPNServerConnectionFactory();
        // ネゴシエーションがない場合に使用するデフォルトのプロトコル
        alpn.setDefaultProtocol(http11.getProtocol());

        // キーストア情報を使用してSslContextFactoryを設定します。
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePassword("/path/to/keystore");
        sslContextFactory.setKeyStorePassword("secret");

        // TLS用のConnectionFactory
        SslConnectionFactory tls = new SslConnectionFactory(sslContextFactory, alpn.getProtocol());

        // ServerConnectorインスタンス
        ServerConnector connector = new ServerConnector(server, tls, alpn, http11, h2);
        connector.setPort(8443);

        server.addConnector(connector);
        server.start();
    }

}
