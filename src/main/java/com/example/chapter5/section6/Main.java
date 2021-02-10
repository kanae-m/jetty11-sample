package com.example.chapter5.section6;

import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.api.Session;
import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.DataFrame;
import org.eclipse.jetty.http2.frames.HeadersFrame;
import org.eclipse.jetty.http2.frames.PushPromiseFrame;
import org.eclipse.jetty.http2.frames.SettingsFrame;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.resource.Resource;

import java.nio.ByteBuffer;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        // ファビコンのバイト
        ByteBuffer faviconBuffer = BufferUtil.toBuffer(Resource.newResource("/path/to/favicon.ico"), true);

        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            // デフォルトでは、プッシュが有効になっています。
            private boolean pushEnabled = true;

            @Override
            public void onSettings(Session session, SettingsFrame frame) {
                // クライアントがENABLE_PUSHの設定を送信したかどうかを確認します。
                Map<Integer, Integer> settings = frame.getSettings();
                Integer enablePush = settings.get(SettingsFrame.ENABLE_PUSH);
                if (enablePush != null) {
                    pushEnabled = enablePush == 1;
                }
            }

            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame) {
                MetaData.Request request = (MetaData.Request) frame.getMetaData();
                if (pushEnabled && request.getURIString().endsWith("/index.html")) {
                    // ファビコンを押す。
                    HttpURI pushedURI = HttpURI.build(request.getURI()).path("/favicon.ico");
                    MetaData.Request pushedRequest = new MetaData.Request("GET", pushedURI, HttpVersion.HTTP_2, HttpFields.EMPTY);
                    PushPromiseFrame promiseFrame = new PushPromiseFrame(stream.getId(), 0, pushedRequest);
                    stream.push(promiseFrame, new Stream.Listener.Adapter()).thenCompose(pushedStream -> {
                        // ファビコン「レスポンス」を送信します。
                        MetaData.Response pushedResponse = new MetaData.Response(HttpVersion.HTTP_2, HttpStatus.OK_200, HttpFields.EMPTY);
                        return pushedStream.headers(new HeadersFrame(pushedStream.getId(), pushedResponse, null, false))
                                .thenCompose(pushed -> pushed.data(new DataFrame(pushed.getId(), faviconBuffer, true)));
                    });
                }
                // リクエストイベントを処理するための Stream.Listener を返します。
                return new Stream.Listener.Adapter();
            }
        };
    }

}
