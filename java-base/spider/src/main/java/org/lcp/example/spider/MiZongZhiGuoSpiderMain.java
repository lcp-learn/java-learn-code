package org.lcp.example.spider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/11/25-13:17
 */
public class MiZongZhiGuoSpiderMain {

  static String kongge = "&nbsp;&nbsp;&nbsp;&nbsp;";
  static List<String> target = new LinkedList();

  static long start = 1715L;
  static long end = 1864L;
  //    static long end = 418_091L;

  public static void main(String[] args) throws Exception {

    httpSpider(start, end);

    //        target.forEach(item -> System.out.println(item));

    File file = new File("/Users/lichunpeng/tmp/gcd3-3-神农天匦");
    writeFile(file, target);
  }

  private static void httpSpider(long start, long end) throws IOException {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    for (long i = start; i <= end; i++) {
      HttpGet httpGet = new HttpGet("https://www.kanunu8.com/files/terrorist/200909/920/" + i + ".html");
      CloseableHttpResponse response1 = httpclient.execute(httpGet);
      try {
        //                                System.out.println("response getStatusCode = " + response1
        //                                .getStatusLine().getStatusCode());
        HttpEntity entity1 = response1.getEntity();
        String response = EntityUtils.toString(entity1, "GBK");
        handle(response, i);
        //必须完全的consume，否则connection manager可能无法复用连接
        EntityUtils.consume(entity1);
      } finally {
        //必须close response ， 否则无法释放持有的connection
        response1.close();
      }
    }

  }

  private static void handle(String message, long count) {

    String[] lines = message.split("\n");

    int currentLineCount = 0;
    for (String line : lines) {
      currentLineCount++;
      line = line.trim();
      if (line.contains("<div id=\"title\">")) {
        target.add(line.replace("<div id=\"title\">", "").replace("</div>", ""));
      }

      if (line.contains("<div id='content' class=\"connie\">")) {
        target.add(line.replace("<p>", "\n")
            //                        .replace("|來也[全本小说M.LaiYetxt.COM", "")
            .replace("<div id='content' class=\"connie\">　　", ""));
      }
      if (line.startsWith("　　") && line.endsWith("<p>")) {
        target.add(line.replace("<p>", "\n").replace("　　", ""));
      }
    }

    System.out.println("finish:" + count);
  }

  private static void writeFile(File targetFile, List<String> context) throws IOException {
    if (targetFile.exists()) {
      targetFile.delete();
    }

    targetFile.createNewFile();
    FileWriter bufferedWriter = new FileWriter(targetFile);
    context.forEach(line -> {
      try {
        bufferedWriter.write(line + "\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    bufferedWriter.flush();
    bufferedWriter.close();
  }
}
