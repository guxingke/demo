package com.gxk.demo;

import com.gxk.demo.v1.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

  Main main;

  @Before
  public void setup() {
    main = new Main();
  }

  @Test
  public void test() {
    assertTrue(main != null);
  }
}
