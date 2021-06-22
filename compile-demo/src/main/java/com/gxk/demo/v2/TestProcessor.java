package com.gxk.demo.v2;

import com.sun.source.util.Trees;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("*")
public class TestProcessor extends AbstractProcessor {

  private boolean ok = true;

  private final TestScanner scanner;
  private Trees trees;

  private Messager messager;
  public TestProcessor(TestScanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public synchronized void init(final ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    trees = Trees.instance(processingEnvironment);
    messager = processingEnvironment.getMessager();
  }

  public boolean process(
      final Set<? extends TypeElement> types,
      final RoundEnvironment environment
  ) {

    if (!environment.processingOver()) {
      for (final Element element : environment.getRootElements()) {
        try {
          scanner.scan(trees.getPath(element), null);
        } catch (Exception e) {
          messager.printMessage(Kind.ERROR, e.getMessage());
        }
      }
    }
    return true;
  }

  public boolean isOk() {
    return ok;
  }
}
