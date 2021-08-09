package com.lcp.learn.base.collections;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-16-15:56
 */
public class MemoryMappedFileInJava {

  private static int count = 100; // 10 MB

  public static void main(String[] args) throws Exception {

    String value = "下面快速总结一下Java内存映射文件和IO\n\n内存映射文件用于对性能要求高的系统中，如繁忙的电子交易系统";
    byte[] byteValue = value.getBytes();

    RandomAccessFile memoryMappedFile = new RandomAccessFile("/Users/lichunpeng/tmp/qq", "rw");

    // Mapping a file into memory
    MappedByteBuffer mappedByteBuffer = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0,
        byteValue.length);

    //        mappedByteBuffer.put(byteValue);

    byte[] target = new byte[byteValue.length];
    mappedByteBuffer.get(target);
    String finalValue = new String(target);
    System.out.println("finalValue = " + finalValue);

    memoryMappedFile.close();
  }
}
