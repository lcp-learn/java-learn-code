package test;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.lcp.learn.spring.spb.webflux.beans.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/12-13:54
 */
public class WebClientTest {

    @Test
    public void test12() throws InterruptedException {

        WebClient webClient = WebClient.create("http://127.0.0.1:8081");   // 1

        Mono<String> response = webClient
            .get()
            // .uri("/react/hello") // 2
            .uri("/react/flux3") // 2
            .retrieve() // 3
            .bodyToMono(String.class);  // 4

        response.subscribe(System.out::println);    // 5
        SECONDS.sleep(10);  // 6
    }

    @Test
    public void webClientTest2() throws InterruptedException {

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build(); // 1

        webClient
            .get().uri("/user")
            .accept(MediaType.APPLICATION_NDJSON) // 2
            .exchange() // 3
            .flatMapMany(response -> response.bodyToFlux(User.class))   // 4
            .doOnNext(System.out::println)  // 5
                .blockLast();   // 6
    }

    @Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)    // 1
                .retrieve()
                .bodyToFlux(String.class)
                .log()  // 2
                .take(10)   // 3
                .blockLast();
    }
}
