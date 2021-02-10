package com.example.chapter3.section2.item7;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.SecuredRedirectHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // クリアテキスト・コネクタのHttpConfigurationを設定します。
        int securePort = 8443;
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecurePort(securePort);

        // クリアテキストのコネクタ
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        connector.setPort(8080);
        server.addConnector(connector);

        // 暗号化コネクタの HttpConfiguration を構成します。
        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        // TLSを使用しているので、SecureRequestCustomizerを追加します。
        httpConfig.addCustomizer(new SecureRequestCustomizer());

        // 暗号化されたコネクタの HttpConnectionFactory
        HttpConnectionFactory http11 = new HttpConnectionFactory(httpsConfig);

        // SslContextFactory に keyStore 情報を設定します。
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath("/path/to/keystore");
        sslContextFactory.setKeyStorePassword("secret");

        // TLS用のConnectionFactory
        SslConnectionFactory tls = new SslConnectionFactory(sslContextFactory, http11.getProtocol());

        // 暗号化されたコネクタ
        ServerConnector secureConnector = new ServerConnector(server, tls, http11);
        secureConnector.setPort(8443);
        server.addConnector(secureConnector);

        SecuredRedirectHandler securedHandler = new SecuredRedirectHandler();

        // SecuredRedirectHandler をサーバーにリンクします。
        server.setHandler(securedHandler);

        // コンテキストを保持する ContextHandlerCollection を作成します。
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();
        // ContextHandlerCollection を StatisticsHandler にリンクします。
        securedHandler.setHandler(contextCollection);

        server.start();
    }

}
