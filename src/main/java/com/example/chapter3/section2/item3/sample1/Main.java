package com.example.chapter3.section2.item3.sample1;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // ResourceHandlerを作成して設定します
        ResourceHandler handler = new ResourceHandler();
        // 静的リソースが配置されているディレクトリを設定します。
        handler.setBaseResource(Resource.newResource("/path/to/static/resources/"));
        // ディレクトリリストを設定します。
        handler.setDirectoriesListed(false);
        // ウェルカムファイルを設定します。
        handler.setWelcomeFiles(new String[]{"index.html"});
        // 範囲要求を受け入れるかどうかを設定します。
        handler.setAcceptRanges(true);

        // コンテキストをサーバーにリンクします。
        server.setHandler(handler);

        server.start();
    }

}
