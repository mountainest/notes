package com.mountain.springboot.autoconfiguration.demo;

import com.mountain.springboot.autoconfiguration.AutoConfigurationApplicationTests;
import org.junit.Test;

public class PrintABCTest extends AutoConfigurationApplicationTests {

  @Test
  public void print() {
    PrintABC.print();
  }
}