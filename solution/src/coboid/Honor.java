package coboid;

import java.util.*;
import java.util.stream.Collectors;

public class Honor {
    private static final int SHIFT_NUM = 10;
    private Set<String> okSet = new HashSet<>();
    private List<String> okList = new ArrayList<>();
    private List<String> errorList = new ArrayList<>();

    public static void main(String[] args) {
        Honor main = new Honor();
        main.solve();
    }

    private void solve() {
        // 分别找出合法和非法字符串
        this.select();
        // 对合法字符串分别左移10次
        List<String> shiftList = this.leftShift(okList);
        // 排序
        List<String> lastList = shiftList.stream().sorted().collect(Collectors.toList());
        // 打印输出结果
        this.output(shiftList, lastList);
    }

    private void output(List<String> shiftList, List<String> lastList) {
        // 输出合法字符串并去重
        okList.stream().forEach(str -> System.out.print(str + " "));
        System.out.println();
        // 输出非法字符串
        errorList.stream().forEach(str -> System.out.print(str + " "));
        System.out.println();
        // 移位
        shiftList.stream().forEach(str -> System.out.print(str + " "));
        System.out.println();
        // 输出最终结果
        lastList.stream().forEach(str -> System.out.print(str + " "));
        System.out.println();
    }

    private List<String> leftShift(List<String> okList) {
        return okList.stream().map(str -> {
            StringBuilder builder = new StringBuilder(str);
            for (int i = 0; i < SHIFT_NUM; i++) {
                char ch = builder.charAt(0);
                builder.deleteCharAt(0);
                builder.append(ch);
            }

            return builder.toString();
        }).collect(Collectors.toList());
    }

    private void select() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (!this.procLine(sc.nextLine())) {
                return;
            }
        }
    }

    private boolean procLine(String line) {
        if (line == null || line.length() <= 0) {
            return false;
        }

        // 连续空格
        boolean spaceCharFlg = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (!spaceCharFlg && Character.isSpaceChar(line.charAt(i))) {
                this.doWord(builder.toString());
                spaceCharFlg = true;
                builder = new StringBuilder();
            } else if (!Character.isSpaceChar(line.charAt(i))) {
                // 如果不是空白
                builder.append(line.charAt(i));
                spaceCharFlg = false;
            }
        }

        this.doWord(builder.toString());

        return true;
    }

    private void doWord(String word) {
        if (word == null || word.length() <= 0) {
            return;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetterOrDigit(word.charAt(i))) {
                errorList.add(word);
                return;
            }
        }

        if (!okSet.contains(word)) {
            okSet.add(word);
            okList.add(word);
        }
    }
}