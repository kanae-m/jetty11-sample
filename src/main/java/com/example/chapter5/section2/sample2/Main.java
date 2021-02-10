package com.example.chapter5.section2.sample2;

import org.eclipse.jetty.http2.api.Session;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;

import java.net.InetSocketAddress;

import static java.lang.System.Logger.Level.INFO;

public class Main {

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("squid:S1854")
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public void onAccept(Session session) {
                InetSocketAddress remoteAddress = session.getRemoteAddress();
                System.getLogger("http2").log(INFO, "Connection from {0}", remoteAddress);
            }
        };
    }

}
