package org.lcp.example.netty.chat.server.handler;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/9-17:29
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * GlobalEventExecutor.INSTANCE) 是全局的事件执行器，是一个单例
   */
  private final ChannelGroup channelGroup;
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public GroupChatServerHandler(ChannelGroup channelGroup) {
    this.channelGroup = channelGroup;
  }

  /**
   * 将当前channel 加入到  channelGroup 表示连接建立，一旦连接，第一个被执行
   *
   * @param channelHandlerContext
   * @throws Exception
   */
  @Override
  public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    var channel = channelHandlerContext.channel();

    logger.info("handlerAdded-{}", channel.remoteAddress());
    //将该客户加入聊天的信息推送给其它在线的客户端
        /*
        该方法会将 channelGroup 中所有的channel 遍历，并发送 消息，
        我们不需要自己遍历
         */
    channelGroup.add(channel);
    logger.info("channelGroup size:{}", channelGroup.size());

    channelGroup
        .writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天" + simpleDateFormat.format(new Date()) + " \n");
  }

  /**
   * 断开连接, 将xx客户离开信息推送给当前在线的客户
   *
   * @param channelHandlerContext
   * @throws Exception
   */
  @Override
  public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    logger.info("handlerRemoved");

    var channel = channelHandlerContext.channel();

    channelGroup.remove(channel);
    logger.info("channelGroup size:{}", channelGroup.size());

    channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
  }

  /**
   * 表示channel 处于活动状态, 提示 xx上线
   *
   * @param channelHandlerContext
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
    logger.info("{} 上线了~", channelHandlerContext.channel().remoteAddress());
  }

  /**
   * 表示channel 处于不活动状态, 提示 xx离线了
   *
   * @param channelHandlerContext
   * @throws Exception
   */
  @Override
  public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
    logger.info("{} 离线了~", channelHandlerContext.channel().remoteAddress());
  }

  /**
   * 读取数据
   *
   * @param channelHandlerContext
   * @param msg
   * @throws Exception
   */
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
    //获取到当前channel
    var channel = channelHandlerContext.channel();
    //这时我们遍历channelGroup, 根据不同的情况，回送不同的消息

    channelGroup.forEach(channel1 -> {
      var message = EMPTY;
      if (channel != channel1) { //不是当前的channel,转发消息
        message = "[客户]" + channel.remoteAddress() + " 发送了消息：" + msg + "\n";
      } else {//回显自己发送的消息给自己
        message = "[自己]发送了消息" + msg + "\n";
      }
      channel1.writeAndFlush(message);
    });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {

    //关闭通道
    channelHandlerContext.close();
  }
}
