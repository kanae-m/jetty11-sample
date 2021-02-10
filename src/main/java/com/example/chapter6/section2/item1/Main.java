package com.example.chapter6.section2.item1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        DefaultSessionIdManager idMgr = new DefaultSessionIdManager(server);
        // 環境を実行可能なJETTY_WORKER_NAMEを設定しない限り、workerNameを設定する必要があります
        idMgr.setWorkerName("server3");
        server.setSessionIdManager(idMgr);
    }

}
