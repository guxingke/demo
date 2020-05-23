package com.gxk.demo.juc;

import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import sun.misc.Unsafe;

public class MyLock {

  private static Unsafe unsafe;
  private volatile int state;
  private static long stateOffset;
  private  ConcurrentHashMap<String, Thread> map = new ConcurrentHashMap<>();

  static {
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = (Unsafe) field.get(null);
      stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void lock() {
    while (true) {
      if (unsafe.compareAndSwapInt(this, stateOffset, 0, 1)) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " got the lock");
        break;
      } else {
        map.put(Thread.currentThread().getName(), Thread.currentThread());
        unsafe.park(false, 0);
      }
    }
  }

  public void unlock() {
    unsafe.compareAndSwapInt(this, stateOffset, 1, 0);
    for (Entry<String, Thread> entry : map.entrySet()) {
      unsafe.unpark(entry.getValue());
      map.remove(entry.getKey());
    }
  }
}