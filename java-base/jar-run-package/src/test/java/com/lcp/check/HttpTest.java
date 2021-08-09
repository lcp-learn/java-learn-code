package com.lcp.check;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import org.junit.jupiter.api.Test;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/15-14:51
 */
public class HttpTest {

  @Test
  public void test12() {

    HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(5))
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build();

    HttpRequest getRequest = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://www.baidu.com"))
        // .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like
        // Gecko) Chrome/81.0.4044.122 Safari/537.36")
        .build();

  }

}
