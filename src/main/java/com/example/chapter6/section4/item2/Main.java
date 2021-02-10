package com.example.chapter6.section4.item2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.NullSessionCacheFactory;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        NullSessionCacheFactory cacheFactory = new NullSessionCacheFactory();
        cacheFactory.setFlushOnResponseCommit(true);
        cacheFactory.setRemoveUnloadableSessions(true);
        cacheFactory.setSaveOnCreate(true);

        // ファクトリーをサーバにbeanとして追加することで、SessionHandlerが起動するたびに
        // beanを参照し、新しいNullSessionCacheを作成できるようになりました。
        server.addBean(cacheFactory);
    }

}
