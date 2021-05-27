import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LengthOfLongestSubstringTest {
  @Test
  void test01() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb");
    Assertions.assertTrue(n == 3);
  }

  @Test
  void test02() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("bbbbb");
    Assertions.assertTrue(n == 1);
  }

  @Test
  void test03() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew");
    Assertions.assertTrue(n == 3);
  }

  @Test
  void test04() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("");
    Assertions.assertTrue(n == 0);
  }

  @Test
  void test05() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("pwwkewab");
    Assertions.assertTrue(n == 5);
  }

  @Test
  void test06() {
    int n = LengthOfLongestSubstring.lengthOfLongestSubstring("pwwkekab");
    Assertions.assertTrue(n == 4);
  }
}