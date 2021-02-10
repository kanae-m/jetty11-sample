package com.example.chapter3.section3.item1;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // contextPathでServletContextHandlerを作成します。
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/shop");

        // カート機能を実装したサーブレットをコンテキストに追加します。
        ServletHolder servletHolder = context.addServlet(ShopCartServlet.class, "/cart/*");
        // init-parametersでサーブレットを設定します。
        servletHolder.setInitParameter("maxItems", "128");

        // CSRF攻撃から保護するためにCrossOriginFilterを追加します。
        FilterHolder filterHolder = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        // フィルターを設定します。
        filterHolder.setAsyncSupported(true);

        // コンテキストをサーバーにリンクします。
        server.setHandler(context);

        server.start();
    }

}
