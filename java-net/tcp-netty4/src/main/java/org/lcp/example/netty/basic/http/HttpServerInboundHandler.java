package org.lcp.example.netty.basic.http;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/5-16:34<br/>
 */
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

  private final Logger logger = LoggerFactory.getLogger(HttpServerInboundHandler.class);

  private HttpRequest httpRequest;

  @Override
  public void channelRead(ChannelHandlerContext channelHandlerContext, Object message) throws Exception {

    //netty处理http请求，为什么HttpRequest和HttpContent是分两次过来的？
    //答案：
    // 应该是客户端给netty发送数据时使用了http chunk，才导致数据分块发送，
    // 要让netty以整体接收的话，在pipeline中添加HttpObjectAggregator。
    // HttpContent.class文件中由说明。
    if (message instanceof HttpRequest) {
      httpRequest = (HttpRequest) message;

      String uri = httpRequest.uri();
      HttpMethod httpMethod = httpRequest.method();
      System.out.println("httpMethod = " + httpMethod);
      System.out.println("Uri:" + uri);
    }

    if (message instanceof HttpContent) {
      HttpContent httpContent = (HttpContent) message;
      ByteBuf byteBuf = httpContent.content();
      System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
      byteBuf.release();

      FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK,
          Unpooled.wrappedBuffer(getData().getBytes(StandardCharsets.UTF_8)));

      ByteBuf responseByteBuf = fullHttpResponse.content();
      fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
      fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseByteBuf.readableBytes());

      if (HttpUtil.isKeepAlive(httpRequest)) {
        fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
      }

      channelHandlerContext.write(fullHttpResponse);
      channelHandlerContext.flush();
    }
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
    channelHandlerContext.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
    logger.error(cause.getMessage());
    channelHandlerContext.close();
  }

  private String getData() {

    StringBuilder responseBody = new StringBuilder();
    responseBody.append("qqqqqqqqqqqqqqqqqqqq");
    return responseBody.toString();
  }
}
