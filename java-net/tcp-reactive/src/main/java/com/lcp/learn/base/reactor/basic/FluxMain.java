package com.lcp.learn.base.reactor.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/25-15:16
 */
public class FluxMain {

  private static final Logger logger = LoggerFactory.getLogger(FluxMain.class);

  public static void main(String[] args) {

    var seq1 = Flux.just("windows", "mac", "linux")
        .doOnNext(s -> logger.info("next = {}", s))
        .doOnComplete(() -> logger.info("Completed 1111 = "));

    seq1.subscribe(
        s -> logger.info("s = {}", s),
        throwable -> logger.error("", throwable),
        () -> logger.info("Completed" + "!"));

    // List<String> iterable = Arrays.asList("foo", "bar", "foobar");
    // Flux<String> seq2 = Flux.fromIterable(iterable);
    //
    // Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
    //
    // Mono<String> noData = Mono.empty();
    // Mono<String> data = Mono.just("foo");

  }
}
