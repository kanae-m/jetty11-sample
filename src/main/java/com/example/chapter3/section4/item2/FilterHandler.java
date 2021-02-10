package com.example.chapter3.section4.item2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import java.io.IOException;

public class FilterHandler extends HandlerWrapper {

    @Override
    public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getRequestURI();
        if (path.startsWith("/old_path/")) {
            // 古いパスを新しいパスに書き換えます。
            HttpURI uri = jettyRequest.getHttpURI();
            String newPath = "/new_path" + path.substring("/old_path/".length());
            HttpURI newURI = HttpURI.build(uri).path(newPath);
            // リクエストオブジェクトを変更します。
            jettyRequest.setHttpURI(newURI);
        }

        // このHandlerはリクエストを処理していないので、jettyRequest.setHandled(true)を呼び出していません。

        // 次のHandlerに転送します。
        super.handle(target, jettyRequest, request, response);
    }

}
