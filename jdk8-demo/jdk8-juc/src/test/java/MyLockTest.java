import com.gxk.demo.juc.MyLock;
import java.util.stream.IntStream;
import org.junit.Test;

public class MyLockTest {

  MyLock lock = new MyLock();

  @Test
  public void testLockAndUnlock() {
    IntStream.range(0, 10)
        .mapToObj(i -> {
          Thread thread = new Thread(this::myBizMethod);
          thread.setDaemon(false);
          return thread;
        })
        .forEach(Thread::start);
  }

  private void myBizMethod() {
    try {
      lock.lock();
      String t = Thread.currentThread().getName();
      IntStream.range(0, 100).forEach(i -> System.out.println("Thread: " + t + ", index: " + i));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}