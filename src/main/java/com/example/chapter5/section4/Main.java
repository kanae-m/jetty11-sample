package com.example.chapter5.section4;

import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.DataFrame;
import org.eclipse.jetty.http2.frames.HeadersFrame;
import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame) {
                // リクエストを読んでから返信を送信します。
                MetaData.Request request = (MetaData.Request) frame.getMetaData();
                if (frame.isEndStream()) {
                    respond(stream, request);
                    return null;
                } else {
                    return new Stream.Listener.Adapter() {
                        @Override
                        public void onData(Stream stream, DataFrame frame, Callback callback) {
                            // リクエスト内容を消費します。
                            callback.succeeded();
                            if (frame.isEndStream()) {
                                respond(stream, request);
                            }
                        }
                    };
                }
            }

            private void respond(Stream stream, MetaData.Request request) {
                // レスポンスのHEADERSフレームを用意します。

                // レスポンスのHTTPステータスとHTTPヘッダ
                MetaData.Response response = new MetaData.Response(HttpVersion.HTTP_2, HttpStatus.OK_200, HttpFields.EMPTY);

                if (HttpMethod.GET.is(request.getMethod())) {
                    // レスポンスの内容です。
                    ByteBuffer resourceBytes = getResourceBytes(request);

                    // レスポンスステータスとヘッダを含むHEADERSフレームと、レスポンス内容バイトを含むDATAフレームを送信します。
                    stream.headers(new HeadersFrame(stream.getId(), response, null, false))
                            .thenCompose(s -> s.data(new DataFrame(s.getId(), resourceBytes, true)));
                } else {
                    // レスポンスステータスとヘッダを含む HEADERS フレームだけを送信します。
                    stream.headers(new HeadersFrame(stream.getId(), response, null, true));
                }
            }
        };
    }

    private static ByteBuffer getResourceBytes(MetaData.Request request) {
        return null;
    }

}
