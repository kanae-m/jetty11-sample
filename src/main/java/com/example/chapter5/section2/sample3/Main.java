package com.example.chapter5.section2.sample3;

import org.eclipse.jetty.http2.api.Session;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.SettingsFrame;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public Map<Integer, Integer> onPreface(Session session) {
                // 設定などをカスタマイズします。
                Map<Integer, Integer> settings = new HashMap<>();

                // HTTP/2プッシュが無効になっていることをクライアントに伝えます。
                settings.put(SettingsFrame.ENABLE_PUSH, 0);

                return settings;
            }
        };
    }

}
