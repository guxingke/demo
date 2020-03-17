package com.gxk.demo;


public class Rectangular {

  public final int x;
  public final int y;
  public final int w;
  public final int h;

  public Rectangular(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Rectangular{");
    sb.append("x=").append(x);
    sb.append(", y=").append(y);
    sb.append(", w=").append(w);
    sb.append(", h=").append(h);
    sb.append('}');
    return sb.toString();
  }
}
