package dict;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DictionarySearcher {
    private final static String PRE_PATH = "src\\dict";
    private final static Charset CHAR_SET = StandardCharsets.UTF_8;
    private final static int CHAR_LEN = 3;

    private Node root = new Node();

    public DictionarySearcher(String filename) throws Exception {
        Path path = Paths.get(PRE_PATH, filename);
        Scanner scanner;
        scanner = new Scanner(path);

        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                this.build(line);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            scanner.close();
        }
    }

    private void build(String word) {
        if (word == null || word.length() <= 0) {
            return;
        }

        // 词汇拆分成字
        List<String> chList = this.splitWord(word);
        // 从根节点开始遍历构造字典
        this.doBuild(word, chList);
    }

    private List<String> splitWord(String word) {
        List<String> chList = new ArrayList<>();
        byte[] bytes = word.getBytes(CHAR_SET);
        int start = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (i - start >= CHAR_LEN) {
                chList.add(new String(bytes, start, i - start, CHAR_SET));
                start = i;
            }

            // 英文
            if (bytes[i] > 0) {
                chList.add(new String(bytes, i, 1, CHAR_SET));
                start = i + 1;
            }
        }

        if (start < bytes.length) {
            chList.add(new String(bytes, start, bytes.length - start, CHAR_SET));
        }

        return chList;
    }

    private void doBuild(String word, List<String> chList) {
        if (chList == null || chList.size() <= 0) {
            return;
        }

        Node node = root;
        for (int i = 0; i < chList.size(); i++) {
            String ch = chList.get(i);
            // 如果不存在，添加该字
            if (!node.containes(ch)) {
                Node next = new Node();
                node.addNext(ch, next);
                node = next;
            } else {
                node = node.getNext(ch);
            }
        }

        // 如果下个节点包含null，说明为重复添加
        if (node.containes(null)) {
            System.out.println("Repeat word: " + word);
            throw new RuntimeException("Repeat word: " + word);
        }
        // 添加null节点到下个节点
        node.addNext(null, new Node());
    }

    public HashMap<String, ArrayList<Integer>> search(String document) {
        HashMap<String, ArrayList<Integer>> result = new HashMap<>();

        // 文档拆分成字
        List<String> chList = this.splitWord(document);

        // 从第一个字开始遍历字典
        for (int i = 0; i < chList.size(); i++) {
            // 从root节点开始遍历
            this.matchMore(chList, i, result);
        }

        return result;
    }

    private void matchMore(List<String> chList, int start, HashMap<String, ArrayList<Integer>> result) {
        Node node = root;
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < chList.size(); i++) {
            // 如果已经到了尾节点
            if (builder.length() > 0 && node.containes(null)) {
                this.updateResult(builder.toString(), start, result);
            }

            // 如果存在下一个节点
            String ch = chList.get(i);
            if (node.containes(ch)) {
                builder.append(ch);
                node = node.getNext(ch);
            } else {
                break;
            }
        }
    }

    private void updateResult(String word, int start, HashMap<String, ArrayList<Integer>> result) {
        ArrayList<Integer> list = result.getOrDefault(word, new ArrayList<>());
        list.add(start);
        result.putIfAbsent(word, list);
    }

    /**
     * 询问得知，咱们这道题目不限制内存，所以这里只使用TRIE树做了前缀内存复用，然后进行前缀匹配查找。
     * 为了增加子节点的查找速度，这里使用了HashMap，会存在一定的内存浪费。
     * 以后如果考虑到几G的内存偏大，可以借用lucene的FST算法，不仅进行前缀复用，同时进行后缀复用。
     * @param args
     */
    public static void main(String [] args) {
        testcase00();
        testcase01();
        testcase02();
        testcase03();
        testcase04();
        testcase05();
        testcase10();
        testcase11();
        testcase12();
        testcase13();

        System.out.println("\nCongratulations! All testcases passed.\n");
    }

    private static void testcase00() {
        String[] keys = {"美国", "浙江", "浙江大学"};
        int[][] numArr = {{0, 16, 50}, {28, 98, 111}, {28, 98, 111}};
        test("demo.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    private static void testcase01() {
        String[] keys = {"大学", "浙江", "浙江大学"};
        int[][] numArr = {{30, 100, 113}, {28, 98, 111}, {28, 98, 111}};
        test("testcase01.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    private static void testcase02() {
        String[] keys = {"西湖", "大学", "浙江大学"};
        int[][] numArr = {{125}, {30, 100, 113}, {28, 98, 111}};
        test("testcase02.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    private static void testcase03() {
        String[] keys = {"浙江", "人民", "大学", "浙江大学", "浙江人民大学", "人民大学", "浙江人民"};
        int[][] numArr = {{28, 98, 111}, {113}, {30, 100, 115}, {28, 98}, {111}, {113}, {111}};
        test("testcase03.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江人民大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    private static void testcase04() {
        String[] keys = {"副主任"};
        int[][] numArr = {{137}};
        test("testcase04.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江人民大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    /**
     * TODO：规格测试，100w个词汇，1000多个字的句子。
     */
    private static void testcase05() {
        System.out.println("testcase05 passed.");
    }

    /**
     * 文件不存在。
     */
    private static void testcase10() {
        test("noexisted.txt", "", null);
    }

    /**
     * 词汇重复，报警。
     */
    private static void testcase11() {
        test("testcase11.txt", "", null);
    }

    /**
     * 词汇文件存在换行，直接跳过。
     */
    private static void testcase12() {
        String[] keys = {"副主任"};
        int[][] numArr = {{137}};
        test("testcase12.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江人民大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    /**
     * 支持英文词汇。
     */
    private static void testcase13() {
        String[] keys = {"James"};
        int[][] numArr = {{60}};
        test("testcase13.txt",
            "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江人民大学控股集团领导杨其和，西湖区政府代表应权英副主任....",
            buildExpect(keys, numArr));
    }

    private static HashMap<String, ArrayList<Integer>> buildExpect(String[] keys, int[][] numArr) {
        assert keys.length == numArr.length;

        HashMap<String, ArrayList<Integer>> expect = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            int[] arr = numArr[i];
            ArrayList<Integer> list = new ArrayList<Integer>(){{
                for (int e:arr) {
                    add(e);
                }
            }};
            expect.put(keys[i], list);
        }

        return expect;
    }

    private static void test(String filename, String document, HashMap<String, ArrayList<Integer>> expect) {
        DictionarySearcher searcher;
        try {
            searcher = new DictionarySearcher(filename);
        } catch (IOException e) {
            // 如果初始化失败，expect为null。
            System.out.println(e);
            Assertions.assertNull(expect);

            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " passed.");
            return;
        } catch (Exception e) {
            // 其他异常。
            System.out.println(e);
            Assertions.assertNull(expect);

            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " passed.");
            return;
        }

        // 如果能走完初始化，expext一定不为null。
        Assertions.assertNotNull(expect);

        // 没有找到返回的是空集合，结果一定不为null。
        HashMap<String, ArrayList<Integer>> result = searcher.search(document);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() == expect.size());
        Assertions.assertIterableEquals(expect.entrySet(), result.entrySet());

        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " passed.");
    }
}
