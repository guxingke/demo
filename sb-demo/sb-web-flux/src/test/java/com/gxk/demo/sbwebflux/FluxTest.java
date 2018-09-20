package com.gxk.demo.sbwebflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class FluxTest {

  @Test
  public void test() {
    Flux.range(0, 100)
      .log()
      .doOnRequest(System.out::println)
      .map(it -> {
        if (it == 4) {
          throw new RuntimeException("got 4");
        }
        return it;
      })
      .subscribe(
        it -> System.out.println("xxx" + it),
        err -> System.err.println(err),
        () -> System.out.println("done"),
        s -> s.request(20)
      );
  }

  @Test
  public void testGen() {
    Flux<String> flux = Flux.generate(
      () -> 0,
      (state, sink) -> {
        sink.next("3 x " + state + " = " + 3 * state);
        if (state == 10) {
          sink.complete();
        }
        return state + 1;
      });

    flux.subscribe(it -> System.out.println(it));
  }

  @Test
  public void testGen2() {
    Flux<String> flux = Flux.generate(
      AtomicLong::new,
      (state, sink) -> {
        long i = state.getAndIncrement();
        sink.next("3 x " + i + " = " + 3*i);
        if (i == 10) sink.complete();
        return state;
      }, (state) -> System.out.println("state: " + state));

    flux.subscribe(it -> System.out.println(it));
  }

  @Test
  public void testPublishOn() {
    Scheduler s = Schedulers.newParallel("xx", 4);

    final Flux<String> flux = Flux
      .range(1, 3)
      .map(i -> 1 + i)
      .publishOn(s)
      .map(i -> "value " + i);

    new Thread(() -> flux.subscribe(System.out::println));
  }
}
