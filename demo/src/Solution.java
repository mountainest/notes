import java.util.HashSet;
// 3、无重复字符的最长子串

public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<>();
        int i = 0;
        int j = 1;
        int max = 1;
        set.add(s.charAt(i));

        while (j < s.length()) {
            Character ch = s.charAt(j);
            // 如果不重复，往集合添加元素，右指针右移
            if (!set.contains(ch)) {
                set.add(ch);
                j++;
                continue;
            }

            // 否则，计算最大值。
            if (j - i > max) {
                max = j - i;
            }

            // 左指针移动，并删除元素，直到碰见相等的。
            while (i < j) {
                if (s.charAt(i) == ch) {
                    i++;
                    break;
                } else {
                    set.remove(s.charAt(i));
                    i++;
                }
            }

            j++;
        }

        if (j - i > max) {
            max = j - i;
        }

        return max;
    }
}
