import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Reader {

  // (println 1) -> 1
  // (println "xxx") -> xxx
  public static List<String> tokenizer(String code) {
    Pattern pattern = Pattern.compile("[\\s ,]*(~@|[\\[\\]{}()'`~@]|\"(?:[\\\\].|[^\\\\\"])*\"?|;.*|[^\\s \\[\\]{}()'\"`~@,;]*)");
    return new ArrayList<>();
  }
}
