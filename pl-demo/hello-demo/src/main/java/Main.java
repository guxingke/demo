import com.gxk.demo.pl.hello.ByteClassLoader;
import com.gxk.demo.pl.hello.compiler.Compiler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) throws IOException {
    Main main = new Main();
    byte[] compile = main.compile("");

    Files.write(Paths.get("Test.class"), compile);
    ByteClassLoader loader = new ByteClassLoader();
    Class<?> test = loader.findClass("Test", compile);

    System.out.println(test.getName());
  }

  public String readAndEval(String expr) {
    return "1";
  }

  public byte[] compile(String expr) {
    byte[] compile = new Compiler().compile();
    return compile;
  }
}
