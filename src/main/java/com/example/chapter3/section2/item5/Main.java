package com.example.chapter3.section2.item5;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.rewrite.handler.CompactPathRule;
import org.eclipse.jetty.rewrite.handler.RedirectRegexRule;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);

        RewriteHandler rewriteHandler = new RewriteHandler();
        // URIパスを二重スラッシュで圧縮します。例：/ctx//path/to//resource
        rewriteHandler.addRule(new CompactPathRule());
        // */product/*を*/p/*に書き換えます。
        rewriteHandler.addRule(new RedirectRegexRule("/(.*)/product/(.*)", "/$1/p/$2"));
        // 別のURIに恒久的にリダイレクトします。
        RedirectRegexRule redirectRule = new RedirectRegexRule("/documentation/(.*)", "https://docs.domain.com/$1");
        redirectRule.setStatusCode(HttpStatus.MOVED_PERMANENTLY_301);
        rewriteHandler.addRule(redirectRule);

        // RewriteHandler をサーバーにリンクします。
        server.setHandler(rewriteHandler);

        // コンテキストを保持する ContextHandlerCollection を作成します。
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();
        // ContextHandlerCollectionをRewriteHandlerにリンクします。
        rewriteHandler.setHandler(contextCollection);

        server.start();
    }

}
