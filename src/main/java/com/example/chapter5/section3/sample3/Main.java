package com.example.chapter5.section3.sample3;

import org.eclipse.jetty.http2.api.Stream;
import org.eclipse.jetty.http2.frames.DataFrame;
import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) {
        // コンシューマがポーリングして非同期にコンテンツを消費するキュー。
        Queue<Chunk> dataQueue = new ConcurrentLinkedDeque<>();

        @SuppressWarnings({"squid:S1854", "squid:S1481"})
        // 非同期にコンテンツを消費して要求する場合のStream.Listener.onDataDemanded(...)の実装
        Stream.Listener listener = new Stream.Listener.Adapter() {
            @Override
            public void onDataDemanded(Stream stream, DataFrame frame, Callback callback) {
                // コンテンツバッファを取得します。
                ByteBuffer buffer = frame.getData();

                // バッファを保存して非同期に消費し、コールバックをラップします。
                dataQueue.offer(new Chunk(buffer, Callback.from(() -> {
                    // バッファが消費されてしまった場合
                    // A) ネストされたコールバックを成功させる。
                    callback.succeeded();
                    // B) より多くの DATA フレームを要求して下さい。
                    stream.demand(1);
                }, callback::failed)));

                // キューのオーバーフローを避けるために、ここでより多くのコンテンツを要求しないでください。
            }
        };
    }

}
