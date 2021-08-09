package com.lcp.learn.undertow.handlers;

import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * desc:    <br/>
 *
 * @author xyy
 * @since 2019/9/16-15:55
 */
public class SimpleErrorPageHandler implements HttpHandler {

  private final HttpHandler next;

  public SimpleErrorPageHandler(final HttpHandler next) {
    this.next = next;
  }

  @Override
  public void handleRequest(final HttpServerExchange httpServerExchange) throws Exception {

    httpServerExchange.addDefaultResponseListener(httpServerExchange1 -> {

      if (!httpServerExchange1.isResponseChannelAvailable()) {
        return false;
      }
      if (httpServerExchange1.getStatusCode() == 500) {
        final String errorPage = "<html><head><title>Error</title></head><body>Internal Error</body></html>";
        httpServerExchange1.getResponseHeaders().put(Headers.CONTENT_LENGTH, "" + errorPage.length());
        httpServerExchange1.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        Sender sender = httpServerExchange1.getResponseSender();
        sender.send(errorPage);
        return true;
      }
      return false;
    });

    next.handleRequest(httpServerExchange);
  }
}
