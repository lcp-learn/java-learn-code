package com.lcp.learn.base.collections;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-10-16:59
 */
public class NetMain {

  private static final Logger logger = LoggerFactory.getLogger(NetMain.class);

  public static void main(String[] args) throws Exception {

    Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
    while (allNetInterfaces.hasMoreElements()) {
      NetworkInterface netInterface = allNetInterfaces.nextElement();
      System.out.println("netInterface.getName():\t" + netInterface.getName());
      Enumeration<InetAddress> inetAddresses = netInterface.getInetAddresses();
      while (inetAddresses.hasMoreElements()) {
        InetAddress inetAddress = inetAddresses.nextElement();
        System.out.println("\tip=" + inetAddress.getHostAddress());
        System.out.println("\tmac=" + getLocalMac(inetAddress));

        // if (inetAddress instanceof Inet4Address) {
        //   System.out.println("\t本机的ip=" + inetAddress.getHostAddress());
        //   break;
        // }
      }
    }

    // Socket soc = new Socket();
    // soc.bind(new InetSocketAddress(nifAddresses.nextElement(), 0));
    // soc.connect(new InetSocketAddress(address, port));

  }

  private static String getLocalMac(InetAddress inetAddress) throws SocketException {
    //获取网卡，获取地址
    byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
    if (mac == null) {
      return "";
    }
    //        System.out.println("mac数组长度：" + mac.length);
    StringBuilder stringBuffer = new StringBuilder();
    for (int i = 0; i < mac.length; i++) {
      if (i != 0) {
        stringBuffer.append("-");
      }
      //字节转换为整数
      int temp = mac[i] & 0xff;
      String str = Integer.toHexString(temp);
      //            System.out.println("每8位:" + str);
      if (str.length() == 1) {
        stringBuffer.append("0").append(str);
      } else {
        stringBuffer.append(str);
      }
    }
    //        System.out.println("本机MAC地址:" + sb.toString().toUpperCase());

    return stringBuffer.toString();
  }

}
