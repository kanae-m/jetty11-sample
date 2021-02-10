package com.example.chapter5.section3.sample1;

import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.HeadersFrame;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame) {
                // これは"new stream"のイベントなので、リクエストであることが保証されています。
                @SuppressWarnings("squid:S1481")
                MetaData.Request request = (MetaData.Request) frame.getMetaData();

                // リクエストコンテンツイベントやリクエストリセットなどのリクエストイベントを処理するためのStream.Listenerを返します。
                return new Stream.Listener.Adapter();
            }
        };
    }

}
