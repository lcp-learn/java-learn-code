package com.lcp.learn.undertow;

import io.undertow.Undertow;
import io.undertow.util.Headers;

public class UndertowMain {

  public static void main(String[] args) {

    Undertow server = Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler(httpServerExchange -> {
          httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
          httpServerExchange.getResponseSender().send("Hello World");
        }).build();

    server.start();
  }
}
