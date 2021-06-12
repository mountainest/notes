import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseKGroupTest {

  @Test
  void test01() {
    ListNode head = convert2list(new int[]{1, 2, 3, 4});
    ReverseKGroup demo = new ReverseKGroup();
    ListNode result = demo.reverseKGroup(head, 2);
    Assertions.assertTrue(result.val == 3);
  }

  private ListNode convert2list(int[] arr) {
    ListNode head = new ListNode();
    ListNode node = head;

    for (int i = 0; i < arr.length; i++) {
      node.next = new ListNode(arr[i]);
      node = node.next;
    }

    return head.next;
  }
}
