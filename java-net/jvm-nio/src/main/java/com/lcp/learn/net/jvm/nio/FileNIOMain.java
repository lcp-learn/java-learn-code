package com.lcp.learn.net.jvm.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/4/21-14:09
 */
public class FileNIOMain {

  public static void main(String[] args) {

    RandomAccessFile aFile = null;
    try {
      aFile = new RandomAccessFile("/Users/lichunpeng/tmp/haha2", "rw");
      FileChannel fileChannel = aFile.getChannel();
      ByteBuffer buf = ByteBuffer.allocate(30);
      int bytesRead = fileChannel.read(buf);
      System.out.println(bytesRead);

      // while (bytesRead != -1) {
      buf.flip();
      while (buf.hasRemaining()) {
        System.out.print((char) buf.get());
      }
      // buf.compact();
      // bytesRead = fileChannel.read(buf);
      // }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (aFile != null) {
          aFile.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}
