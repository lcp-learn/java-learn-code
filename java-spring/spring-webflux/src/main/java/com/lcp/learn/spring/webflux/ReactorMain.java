package com.lcp.learn.spring.webflux;

import java.util.Arrays;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/10-15:00
 */
public class ReactorMain {

  private static final Logger logger = LoggerFactory.getLogger(ReactorMain.class);

  public static void main(String[] args) {

    Mono<String> mono1 = Mono.just("Alex");
    Mono<Object> mono2 = Mono.empty();

    Flux<String> flux1 = Flux.just("A1", "B1", "C1");
    Flux<String> flux2 = Flux.fromArray(new String[]{"A2", "B2", "C2"});
    Flux<String> flux3 = Flux.fromIterable(Arrays.asList("A3", "B3", "C3"));

    //To subscribe call method

    flux1.subscribe(s -> logger.info("flux1 = {}", s));
    mono1.subscribe(s -> logger.info("mono1 = {}", s));

    flux1.subscribe(new Subscriber<String>() {

      Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
      }

      @Override
      public void onNext(String value) {
        logger.info("flux1 subscription = {}", value);
        subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onComplete() {
        logger.info("complete...");
      }
    });

  }

}
