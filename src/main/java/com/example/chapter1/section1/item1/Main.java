package com.example.chapter1.section1.item1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // HttpChannel.ListenerをBeanとしてコネクタに追加します。
        connector.addBean(new TimingHttpChannelListener());

        // 単純なハンドラーを設定して、リクエスト/レスポンスを処理します。
        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) {
                jettyRequest.setHandled(true);
            }
        });

        server.start();
    }

}
