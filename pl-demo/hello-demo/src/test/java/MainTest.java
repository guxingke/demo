import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void test0() {
    Main main = new Main();
    String ret = main.readAndEval("(println 1)");
    Assert.assertEquals("1", ret);
  }
}
