package com.gxk.demo;

import java.util.List;

public class Record {

  public final int index;
  public final int start;
  public final int end;

  public final int npcSize;
  public final int userSize;
  public final List<Integer> feedbackSizes;

  public Record(int index, int start, int end, int npcSize, int userSize,
      List<Integer> feedbackSizes) {
    this.index = index;
    this.start = start;
    this.end = end;
    this.npcSize = npcSize;
    this.userSize = userSize;
    this.feedbackSizes = feedbackSizes;
  }
}
