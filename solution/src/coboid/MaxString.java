package coboid;

import java.util.HashSet;
import java.util.Set;

public class MaxString {

    /**
     * 给定一个字符串，打印出不存在相同字符的最长子字符串，例如︰输入"abcabbdacbefbac",打印出最长子字符串为"dacbef";
     * @param str
     * @return
     */
    public String solution(String str) {
        String result = "";

        StringBuilder builder = new StringBuilder();
        Set<Character> map = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            // 如果重复
            if (map.contains(str.charAt(i))) {
                // 记录最大的字符串
                if (builder.length() > result.length()) {
                    result = builder.toString();
                }

                // 直接删除该字符之前的所有字符
                boolean endFlg = false;
                while (!endFlg) {
                    if (builder.charAt(0) == str.charAt(i)) {
                        endFlg = true;
                    }

                    map.remove(builder.charAt(0));
                    builder.deleteCharAt(0);
                }
            }

            // 直接添加
            map.add(str.charAt(i));
            builder.append(str.charAt(i));
        }

        return result;
    }
}
