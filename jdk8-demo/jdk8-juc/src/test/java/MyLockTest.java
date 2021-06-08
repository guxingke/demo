import com.gxk.demo.juc.MyLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.junit.Test;

public class MyLockTest {

  MyLock lock = new MyLock();

  AtomicInteger ai = new AtomicInteger(10);

  @Test
  public void testLockAndUnlock() throws InterruptedException {
    IntStream.range(0, 10)
        .mapToObj(i -> {
          Thread thread = new Thread(this::myBizMethod);
          thread.setDaemon(false);
          return thread;
        })
        .forEach(Thread::start);

    while (ai.get() != 0) {
    }
  }

  private void myBizMethod() {
    try {
      lock.lock();
      String t = Thread.currentThread().getName();
      IntStream.range(0, 10).forEach(i -> System.out.println("Thread: " + t + ", index: " + i));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ai.decrementAndGet();
      lock.unlock();
    }
  }
}