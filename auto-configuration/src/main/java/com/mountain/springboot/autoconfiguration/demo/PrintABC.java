package com.mountain.springboot.autoconfiguration.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintABC {
  private static class NoLock implements Runnable {
    private static volatile int cnt = 0;
    private static String[] threads = {"A", "B", "C"};
    private String threadName;

    public NoLock(String threadName) {
      this.threadName = threadName;
    }

    public void run() {
      do {
        int cnt = this.cnt;
        if (threads[cnt % 3].equals(this.threadName)) {
          System.out.print(this.threadName);
          if ("C".equals(this.threadName)) {
            System.out.println("");
          }
//          this.cnt.compareAndSet(cnt, cnt + 1);
          this.cnt = cnt+1;
        }
      } while (this.cnt < 1000);
    }
  }

  private static void NoLockPrint() {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    pool.execute(new NoLock("A"));
    pool.execute(new NoLock("B"));
    pool.execute(new NoLock("C"));
  }

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

  private static void CASprint() {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    pool.execute(new CAS("A"));
    pool.execute(new CAS("B"));
    pool.execute(new CAS("C"));
  }

  private static class SemaphoreCase {
    private int cnt = 0;
    private Semaphore sa = new Semaphore(1);
    private Semaphore sb = new Semaphore(0);
    private Semaphore sc = new Semaphore(0);

    public void print() {
      ExecutorService pool = Executors.newFixedThreadPool(3);
      pool.execute(()->{
        while (cnt < 9000 ) {
          try {
            sa.acquire(1);
          } catch (InterruptedException e) {
            return;
          }
          System.out.print("A");
          cnt++;
          sb.release();
        }
      });
      pool.execute(()->{
        while (cnt < 9000 ) {
          try {
            sb.acquire(1);
          } catch (InterruptedException e) {
            return;
          }
          System.out.print("B");
          cnt++;
          sc.release();
        }
      });
      pool.execute(()->{
        while (cnt < 9000 ) {
          try {
            sc.acquire(1);
          } catch (InterruptedException e) {
            return;
          }
          System.out.println("C");
          cnt++;
          sa.release();
        }
      });
    }
  }

  public static void print() {
//    CASprint();
    new SemaphoreCase().print();
  }
}
