import java.util.LinkedList;
import java.util.List;

// 求扑克牌的原始顺序
public class Puke {
  public List<Integer> get(int[] input) {
    int size = input.length;
    LinkedList<Integer> q = new LinkedList<>();
    q.addFirst(input[size - 1]);
    for (int i = 1; i < size; i++) {
      int tmp = q.pollLast();
      q.addFirst(tmp);

      q.addFirst(input[size - i - 1]);
    }

    return q;
  }
}
