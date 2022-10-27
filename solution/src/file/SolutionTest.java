package file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void test01() {

    int[] a = {1, 2, 1};
    int result = new Solution().solution(a);
    Assertions.assertTrue(result == 2);
  }

  @Test
  void test02() {

    int[] a = {2, 1, 4, 4};
    int result = new Solution().solution(a);
    Assertions.assertTrue(result == 1);
  }

  @Test
  void test03() {

    int[] a = {6, 2, 3, 5, 6, 3};
    int result = new Solution().solution(a);
    Assertions.assertTrue(result == 4);
  }

  @Test
  void test04() {

    int[] a = {2,1,3};
    int result = new Solution().solve(a, 2);
    Assertions.assertTrue(result == 3);
  }

  @Test
  void test05() {

    int[] a = {0,4,3, -1};
    int result = new Solution().solve(a, 2);
    Assertions.assertTrue(result == 2);
  }

  @Test
  void test06() {

    int[] a = {2,1,4};
    int result = new Solution().solve(a, 3);
    Assertions.assertTrue(result == 0);
  }
}