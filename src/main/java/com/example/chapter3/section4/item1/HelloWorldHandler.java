package com.example.chapter3.section4.item1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;

public class HelloWorldHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // リクエストをこのHandlerで処理されたものとしてマークします。
        jettyRequest.setHandled(true);

        response.setStatus(200);
        response.setContentType("text/html; charset=UTF-8");

        // Hello world のレスポンスを書きます。
        response.getWriter().print("" +
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <title>Jetty Hello World Handler</title>" +
                "</head>" +
                "<body>" +
                "  <p>Hello World</p>" +
                "</body>" +
                "</html>" +
                "");
    }

}
