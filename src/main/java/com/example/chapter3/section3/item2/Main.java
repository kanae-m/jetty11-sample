package com.example.chapter3.section3.item2;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // WebAppContextを作成します。
        WebAppContext context = new WebAppContext();
        // パッケージ化されたWebアプリケーションのパス（ファイルまたはディレクトリ）を設定します。
        context.setWar("/path/to/webapp.war");
        // contextPathを設定します。
        context.setContextPath("/app");

        // コンテキストをサーバーにリンクします。
        server.setHandler(context);

        server.start();
    }

}
