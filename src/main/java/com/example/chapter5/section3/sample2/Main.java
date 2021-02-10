package com.example.chapter5.section3.sample2;

import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.DataFrame;
import org.eclipse.jetty.http2.frames.HeadersFrame;
import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;

import static java.lang.System.Logger.Level.INFO;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame) {
                MetaData.Request request = (MetaData.Request) frame.getMetaData();
                // リクエストイベントを処理するための Stream.Listener を返します。
                return new Stream.Listener.Adapter() {
                    @Override
                    public void onData(Stream stream, DataFrame frame, Callback callback) {
                        // コンテンツバッファを取得します。
                        ByteBuffer buffer = frame.getData();
                        // バッファを消費します。ここでは例としてログを取るだけです。
                        System.getLogger("http2").log(INFO, "Consuming buffer {0}", buffer);
                        // バッファが消費されたことを実装に伝えます。
                        callback.succeeded();
                        // メソッドから返すことによって、それらが利用可能なときに、
                        // このメソッドにより多くのDATAフレームを配信するように実装に暗黙のうちに伝えます。
                    }
                };
            }
        };
    }

}
