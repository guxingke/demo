package com.gxk.demo.dynamicproxy.jmh;

import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.dynamicproxy.proxy.ConsumerInvocationHandler;
import com.gxk.demo.dynamicproxy.proxy.ProviderInvocationHandler;
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

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class DynamicProxyBenchmark {

  HelloService service;

  @Setup
  public void setup() {
    HelloService helloService = new HelloServiceImpl();
    Object instance = Proxy.newProxyInstance(DynamicProxyBenchmark.class.getClassLoader(), new Class[] {HelloService.class}, new ProviderInvocationHandler(helloService));

    // bridge
    LocalRegistry.getInstance().register(HelloService.class, instance);

    // consumer
    service = (HelloService) Proxy.newProxyInstance(DynamicProxyBenchmark.class.getClassLoader(), new Class[] {HelloService.class}, new ConsumerInvocationHandler());
  }


  @Benchmark
  public void hello() {
    service.hello("test");
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(DynamicProxyBenchmark.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }
}
