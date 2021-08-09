package org.lcp.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/1/13-16:30
 */
public class SocketClient {

  // 搭建客户端
  public static void main(String[] args) throws IOException {
    try {
      Socket socket = new Socket("ubuntu_lts", 8759);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String readline = in.readLine(); // 从系统标准输入读入一字符串
      System.out.println("Client:" + readline);
      socket.close(); // 关闭Socket
    } catch (Exception e) {
      System.out.println("can not listen to:" + e);// 出错，打印出错信息
    }
  }
}
