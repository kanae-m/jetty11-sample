package com.example.chapter2.sample2;

import org.eclipse.jetty.http.HttpCompliance;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // ポート8080でServerConnectorインスタンスを作成します。
        ServerConnector connector1 = new ServerConnector(server, 1, 1, new HttpConnectionFactory());
        connector1.setPort(8080);
        server.addConnector(connector1);
        // たとえば、異なるHTTP設定を使用して、ポート9090で別のServerConnectorインスタンスを作成します。
        HttpConfiguration httpConfig2 = new HttpConfiguration();
        httpConfig2.setHttpCompliance(HttpCompliance.LEGACY);
        @SuppressWarnings("squid:S2095")
        ServerConnector connector2 = new ServerConnector(server, 1, 1, new HttpConnectionFactory(httpConfig2));
        connector2.setPort(9090);

        server.start();
    }

}
