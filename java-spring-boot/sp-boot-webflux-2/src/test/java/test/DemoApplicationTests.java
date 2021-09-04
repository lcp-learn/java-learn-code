package test;

import com.lcp.learn.spring.spb.webflux.Webflux2Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-11:40
 */
@RunWith(SpringRunner.class)
@WebFluxTest
@ContextConfiguration(classes = Webflux2Starter.class)
public class DemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllMessagesShouldBeOk() {
        WebTestClient.ResponseSpec responseSpec = webTestClient.get().uri("/").exchange().expectStatus().isOk();
        System.out.println("responseSpec = " + responseSpec.expectBody().toString());
    }
}
