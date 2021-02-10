package com.example.chapter3.section2.item4.sample1;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // GzipHandlerを作成して設定します。
        GzipHandler gzipHandler = new GzipHandler();
        // これより大きいレスポンスコンテンツのみ圧縮してください。
        gzipHandler.setMinGzipSize(1024);
        // これらのURIパスを圧縮しないでください。
        gzipHandler.setExcludedPaths("/uncompressed");
        // POST応答も圧縮します。
        gzipHandler.addIncludedMethods("POST");
        // これらのマイムタイプを圧縮しないでください。
        gzipHandler.addExcludedMimeTypes("font/ttf");

        // ContextHandlerCollection をリンクしてコンテキストを管理します。
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        gzipHandler.setHandler(contexts);

        // GzipHandlerをサーバーにリンクします。
        server.setHandler(gzipHandler);

        server.start();
    }

}
