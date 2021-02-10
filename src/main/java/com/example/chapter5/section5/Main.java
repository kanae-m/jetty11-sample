package com.example.chapter5.section5;

import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.ErrorCode;
import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.api.server.ServerSessionListener;
import org.eclipse.jetty.http2.frames.HeadersFrame;
import org.eclipse.jetty.http2.frames.ResetFrame;
import org.eclipse.jetty.util.Callback;

public class Main {

    public static void main(String[] args) {
        float maxRequestRate = 10;

        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        ServerSessionListener sessionListener = new ServerSessionListener.Adapter() {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame) {
                float requestRate = calculateRequestRate();

                if (requestRate > maxRequestRate) {
                    stream.reset(new ResetFrame(stream.getId(), ErrorCode.REFUSED_STREAM_ERROR.code), Callback.NOOP);
                    return null;
                } else {
                    // リクエストを受け付けています。
                    MetaData.Request request = (MetaData.Request) frame.getMetaData();
                    // リクエストイベントを処理するための Stream.Listener を返します。
                    return new Stream.Listener.Adapter();
                }
            }
        };
    }

    public static float calculateRequestRate() {
        return 0;
    }

}
