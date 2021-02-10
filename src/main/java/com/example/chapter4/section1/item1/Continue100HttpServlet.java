package com.example.chapter4.section1.item1;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpHeaderValue;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

public class Continue100HttpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // メソッドとヘッダを検査します。
        boolean isPost = HttpMethod.POST.is(request.getMethod());
        boolean expects100 = HttpHeaderValue.CONTINUE.is(request.getHeader("Expect"));
        long contentLength = request.getContentLengthLong();

        if (isPost && expects100) {
            if (contentLength > 1024 * 1024) {
                // 大きすぎるアップロードを拒否します。
                response.sendError(HttpStatus.PAYLOAD_TOO_LARGE_413);
            } else {
                // リクエストInputStreamを取得すると、アプリケーションがリクエストの内容を読みたいことを示します。
                // Jettyはこの時点で100 Continueレスポンスを送信し、クライアントはリクエスト内容を送信します。
                @SuppressWarnings("squid:S1854")
                ServletInputStream input = request.getInputStream();
                // リクエスト入力を読み込んで処理します。
            }
        } else {
            // 通常のリクエストを処理します。
        }
    }

}
