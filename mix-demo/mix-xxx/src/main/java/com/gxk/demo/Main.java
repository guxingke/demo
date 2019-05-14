package com.gxk.demo;

import java.io.IOException;
import org.jline.builtins.Completers.FileNameCompleter;
import org.jline.reader.Completer;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {

  public static void main(String[] args) throws IOException {
    Terminal terminal = TerminalBuilder.builder()
        .system(true)
        .build();

    Completer ls = new ArgumentCompleter(new StringsCompleter("ls"), new FileNameCompleter(), NullCompleter.INSTANCE);
    Completer env = new ArgumentCompleter(new StringsCompleter("env"), new FileNameCompleter(), NullCompleter.INSTANCE);

    AggregateCompleter completer = new AggregateCompleter(ls, env);

    LineReader lineReader = LineReaderBuilder.builder()
        .terminal(terminal)
        .completer(completer)
        .build();

    String prompt = "sl> ";
    while (true) {
      String line;
      try {
        line = lineReader.readLine(prompt);
        System.out.println(line);
      } catch (UserInterruptException e) {
        // Do nothing
      } catch (EndOfFileException e) {
        System.out.println("/nBye.");
        return;
      }
    }
  }
}
