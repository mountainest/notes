import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
  @Test
  void test01() {
    int n = Solution.lengthOfLongestSubstring("abcabcbb");
    Assertions.assertTrue(n == 3);
  }

  @Test
  void test02() {
    int n = Solution.lengthOfLongestSubstring("bbbbb");
    Assertions.assertTrue(n == 1);
  }

  @Test
  void test03() {
    int n = Solution.lengthOfLongestSubstring("pwwkew");
    Assertions.assertTrue(n == 3);
  }

  @Test
  void test04() {
    int n = Solution.lengthOfLongestSubstring("");
    Assertions.assertTrue(n == 0);
  }

  @Test
  void test05() {
    int n = Solution.lengthOfLongestSubstring("pwwkewab");
    Assertions.assertTrue(n == 5);
  }

  @Test
  void test06() {
    int n = Solution.lengthOfLongestSubstring("pwwkekab");
    Assertions.assertTrue(n == 4);
  }
}