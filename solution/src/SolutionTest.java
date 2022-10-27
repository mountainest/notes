import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void test01() {

    int result = new Solution().solution(1);
    Assertions.assertTrue(result == 0);
  }
}