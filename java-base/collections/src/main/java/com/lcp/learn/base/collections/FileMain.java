package com.lcp.learn.base.collections;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class FileMain {

  private static final Logger logger = LoggerFactory.getLogger(FileMain.class);

  public static void main(String[] args) throws Exception {

    // file1();

    file2();

    // file3();
  }

  private static void file3() throws IOException {

    var path = Paths.get("/Users/lichunpeng/tmp/qweasd");
    var logFilePath = Paths.get("/Users/lichunpeng/tmp/log4j2.xml");
    var channel = Files.newByteChannel(path, WRITE);

    var fileInputStream = new FileInputStream(logFilePath.toFile());
    var buffer = ByteBuffer.wrap(fileInputStream.readAllBytes());
    channel.position(INTEGER_ZERO).write(buffer);

  }

  private static void file2() throws Exception {

    var pathValue = "/Users/lichunpeng/project/oschina/lcp-learn";

    // 收集文件
    var logFileList = Files.walk(Paths.get(pathValue), FOLLOW_LINKS).filter(path -> path.toString().endsWith(
            "log4j2.xml") //
            && !path.toString().contains("target")) //不包含target
        .collect(Collectors.toList());

    // log文件原件
    // var logFileInputStream = new FileInputStream(Paths.get("/Users/lichunpeng/tmp/log4j2.xml").toFile());

    // 遍历文件
    logFileList.forEach(logFile -> {
      logger.info("logFile:{}", logFile);
      try {

        FileCopyUtils.copy(Paths.get("/Users/lichunpeng/tmp/log4j2.xml").toFile(), logFile.toFile());
        // var byteBuffer = ByteBuffer.wrap(logFileInputStream.readAllBytes());
        // var targetLogFileChannel = FileChannel.open(logFile, WRITE);
        // targetLogFileChannel
        //         // .position(INTEGER_ZERO)
        //         .write(byteBuffer);
        // targetLogFileChannel.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    // logFileInputStream.close();
  }

  private static void file1() {
    // var path1 = "D:\\tmp\\天子传奇1珍藏版\\";
    var path1 = "";
    var file = new File(path1);

    var subs = file.listFiles();
    assert subs != null;
    for (File sub : subs) {
      var items = sub.listFiles();
      assert items != null;
      for (File item : items) {
        //System.out.println("sub=" + sub.getName() + "\titem = " + item.getName());

        String newName = sub.getAbsolutePath() + File.separator + sub.getName() + "-" + item.getName();
        System.out.println("newName = " + newName);
        item.renameTo(new File(newName));
      }
    }

    // TODO: check_vscode_plug
  }
}
