import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;


public class FdTest {

  public static void main(String[] args) throws IOException {
    FileOutputStream out = new FileOutputStream(FileDescriptor.out);
    out.write("hello hhhhh".getBytes());
    out.flush();
    out.close();
  }
}
