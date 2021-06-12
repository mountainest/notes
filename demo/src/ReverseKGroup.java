public class ReverseKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode h = new ListNode();
    h.next = head;

    ListNode prev = h;
    ListNode groupHead = head;
    ListNode groupTail;

    while (groupHead != null) {
      groupTail = prev;
      for (int i = 0; i < k; i++) {
        groupTail = groupTail.next;
        if (groupTail == null) {
          return h.next;
        }
      }

      // 翻转
      ListNode[] result = reverse(groupHead, groupTail);
      groupHead = result[0];
      groupTail = result[1];

      prev.next = groupHead;

      prev = groupTail;
      groupHead = groupTail.next;
    }

    return h.next;
  }

  private ListNode[] reverse(ListNode head, ListNode tail) {
    ListNode prev = tail.next;
    ListNode node = head;
    ListNode next;
    while (prev != tail) {
      next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }

    return new ListNode[]{tail, head};
  }
}
