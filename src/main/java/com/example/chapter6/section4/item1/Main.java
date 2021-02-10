package com.example.chapter6.section4.item1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionCacheFactory;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();

        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        // EVICT_ON_INACTIVE: 60秒の非アクティブ状態の後にセッションを削除します。
        cacheFactory.setEvictionPolicy(60);
        // EVICT_ON_INACTIVEポリシーでのみ有効です。
        cacheFactory.setSaveOnInactiveEvict(true);
        cacheFactory.setFlushOnResponseCommit(true);
        cacheFactory.setInvalidateOnShutdown(false);
        cacheFactory.setRemoveUnloadableSessions(true);
        cacheFactory.setSaveOnCreate(true);

        // ファクトリーをbeanとしてサーバに追加します。SessionHandlerが起動するたびに、
        // beanを参照して新しいDefaultSessionCacheを作成します。
        server.addBean(cacheFactory);
    }

}
