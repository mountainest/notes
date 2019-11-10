package com.mountain.springboot.autoconfiguration;

// 打包测试

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// 执行该类，会报java.lang.Exception: No runnable methods，待定位。
//@Ignore("not ready yet") // 写在类上，忽略该类。写在方法上，忽略该方法。
@RunWith(Suite.class)
@Suite.SuiteClasses({SingleConfigurationTest.class, PersonInfoTest.class})
public class TestSuites {

}
