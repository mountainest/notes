package file;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public void print() {
        System.out.println("测试");
    }
    public boolean solution(int[] A, int[] B) {
        // write your code in Java SE 8
        if (A == null || B == null) {
            return false;
        }

        int len = A.length;
        for (int i = 0; i < len; i++) {
            int j = i < len - 1 ? i + 1 : 0;
            if (A[i] != B[j]) {
                return false;
            }
        }

        return true;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A == null) {
            throw new NullPointerException("A = null.");
        }

        final int MAX_RESULT = 1000000000;
        int result = 0;
        int arr[] = new int[A.length];

        // 计数
        for (int i = 0; i < A.length; i++) {
            arr[A[i] - 1]++;
        }

        // 从低位开始分配
        int remainNum = 0;
        for (int i = 0; i < A.length; i++) {
            // 有空位需要填充
            if (arr[i] == 0) {
                remainNum++;
            }

            // 需要找空位
            if (arr[i] > 1) {
                CalcInfo info = this.move(arr, i, remainNum);
                remainNum = info.remainNum;
                result += info.moveNum;
                if (result > MAX_RESULT) {
                    return -1;
                }
            }
        }

        return result;
    }

    private CalcInfo move(int[] arr, int curr, int remainNum) {
        int moveNum = 0;
        for (int i = curr - 1; i >= 0 && remainNum > 0; i--) {
            if (arr[i] == 0) {
                arr[curr]--;
                arr[i] = 1;
                moveNum += (curr - i);
                remainNum--;

                if (arr[curr] == 1) {
                    return new CalcInfo(moveNum, remainNum);
                }
            }
        }

        for (int i = curr + 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[curr]--;
                arr[i] = 1;
                moveNum += (i - curr);

                if (arr[curr] == 1) {
                    return new CalcInfo(moveNum, remainNum);
                }
            }
        }

        // 异常
        throw new RuntimeException("data error.");
    }

    private static class CalcInfo {
        private int moveNum;
        private int remainNum;
        CalcInfo(int moveNUm, int remainNum) {
            this.moveNum = moveNUm;
            this.remainNum = remainNum;
        }
    }

    // https://leetcode.cn/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
    public int solve(int[] A, int S){
        if (A == null) {
            return 0;
        }

        final int MAX_RESULT = 1000000000;
        int num = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);

        for (int i = 0; i < A.length; i++) {
            sum += A[i] - S;
            if (map.containsKey(sum)) {
                num += map.get(sum);
                if (num > MAX_RESULT) {
                    return MAX_RESULT;
                }
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return num;
    }

}
