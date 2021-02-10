package com.example.chapter6.section2.item3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.server.session.HouseKeeper;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        DefaultSessionIdManager idMgr = new DefaultSessionIdManager(server);
        idMgr.setWorkerName("server7");
        server.setSessionIdManager(idMgr);

        HouseKeeper houseKeeper = new HouseKeeper();
        houseKeeper.setSessionIdManager(idMgr);
        // 削除サイクルの頻度を設定する
        houseKeeper.setIntervalSec(600L);
        idMgr.setSessionHouseKeeper(houseKeeper);
    }

}
