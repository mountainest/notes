package com.mountain.springboot.autoconfiguration.demo;

public class Singleton {
  private String str;
  private static class SingleObject {
    private static Singleton instance = new Singleton();
  }
  private Singleton() {}
  public static Singleton getInstance() {
    return SingleObject.instance;
  }

  public void print() {
    System.out.println("内部类单例模式。" + this.str);
  }

  public void setStr(String str) {
    this.str = str;
  }

}
