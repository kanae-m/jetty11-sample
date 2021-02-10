package com.example.chapter3.section3.item3;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) {
        // contextPathでServletContextHandlerを作成します。
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/app");

        // 静的コンテンツを提供する DefaultServlet を追加します。
        ServletHolder servletHolder = context.addServlet(DefaultServlet.class, "/");
        // init-parameters で DefaultServlet を設定します。
        servletHolder.setInitParameter("resourceBase", "/path/to/static/resources");
        servletHolder.setAsyncSupported(true);
    }

}
