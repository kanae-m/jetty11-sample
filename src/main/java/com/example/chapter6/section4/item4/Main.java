package com.example.chapter6.section4.item4;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.session.DefaultSessionCacheFactory;
import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.NullSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();

        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        // NEVER_EVICT
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        cacheFactory.setFlushOnResponseCommit(true);
        cacheFactory.setInvalidateOnShutdown(false);
        cacheFactory.setRemoveUnloadableSessions(true);
        cacheFactory.setSaveOnCreate(true);

        // ファクトリをサーバにbeanとして追加することで、SessionHandlerが起動するたびに、
        // beanを参照して新しいDefaultSessionCacheを作成されるようになります。
        server.addBean(cacheFactory);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        // DefaultSessionCacheFactoryを介してDefaultSessionCacheを使用するWebアプリを追加します。
        WebAppContext app1 = new WebAppContext();
        app1.setContextPath("/app1");
        contexts.addHandler(app1);

        // 代わりに明示的な NullSessionCache を使用する Web アプリを追加します。
        WebAppContext app2 = new WebAppContext();
        app2.setContextPath("/app2");
        NullSessionCache nullSessionCache = new NullSessionCache(app2.getSessionHandler());
        nullSessionCache.setFlushOnResponseCommit(true);
        nullSessionCache.setRemoveUnloadableSessions(true);
        nullSessionCache.setSaveOnCreate(true);
        // 既存のSessionCacheインスタンスをSessionHandlerに渡す場合は、完全に設定する必要があります。
        // つまり、SessionDataStoreも提供する必要があります。
        nullSessionCache.setSessionDataStore(new NullSessionDataStore());
        app2.getSessionHandler().setSessionCache(nullSessionCache);
    }

}
