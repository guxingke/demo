package com.gxk.demo.staticproxy.jmh;

import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.register.LocalRegistry;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class StaticProxyBenchmark {

  com.gxk.demo.staticproxy.client.HelloServiceProxy clientProxy;

  @Setup
  public void setup() {
    LocalRegistry.getInstance().register(HelloService.class, new HelloServiceImpl());
    clientProxy = new com.gxk.demo.staticproxy.client.HelloServiceProxy();
  }

  @Benchmark
  public void hello() {
    clientProxy.hello("test");
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(StaticProxyBenchmark.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }
}
