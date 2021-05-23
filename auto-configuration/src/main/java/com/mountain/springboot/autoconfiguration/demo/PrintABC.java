package com.mountain.springboot.autoconfiguration.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintABC {
  private static class CAS implements Runnable {
    private static AtomicInteger cnt = new AtomicInteger();
    private static String[] threads = {"A", "B", "C"};
    private String threadName;

    public CAS(String threadName) {
      this.threadName = threadName;
    }

    public void run() {
      do {
        int cnt = this.cnt.get();
        if (threads[cnt % 3].equals(this.threadName)) {
          System.out.print(this.threadName);
          if ("C".equals(this.threadName)) {
            System.out.println("");
          }
//          this.cnt.compareAndSet(cnt, cnt + 1);
          this.cnt.set(cnt+1);
        }
      } while (this.cnt.get() < 1000);
    }
  }

  public static void print() {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    pool.execute(new CAS("A"));
    pool.execute(new CAS("B"));
    pool.execute(new CAS("C"));
  }
}
