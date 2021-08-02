import java.util.HashMap;
import java.util.Map;

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
// 设计和实现一个 LRU (最近最少使用) 缓存机制 。
// 实现 LRUCache 类：

// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

// 要求：get和put时间复杂度为 O(1)

import java.util.HashMap;
    import java.util.Map;

public class Solution {
  private static class NodeInfo {
    private NodeInfo pre;
    private NodeInfo next;
    private int key;
    private int value;
  }

  private NodeInfo head;
  private NodeInfo tail;
  private Map<Integer, NodeInfo> map;
  private int capacity;

  public Solution(int capacity) {
    this.capacity = capacity;
    this.head = new NodeInfo();
    this.tail = new NodeInfo();
    head.next = tail;
    tail.pre = head;
    this.map = new HashMap<>();
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }

    NodeInfo node = map.get(key);
    deleteNode(node);
    move2head(node);

    return node.value;
  }

  public void put(int key, int value) {
    if (this.capacity <= 0) {
      return;
    }

    NodeInfo node;
    // 不存在，新增节点插入头部
    if (!map.containsKey(key)) {
      node = new NodeInfo();
      if (this.map.size() >= this.capacity) {
        deleteNode(tail.pre);
      }
      map.put(tail.key, node);
    } else {
      // 更新现有节点，移除中间节点
      node = map.get(key);
      deleteNode(node);
    }
    node.value = value;

    // 移动到头部
    move2head(node);
  }

  private void move2head(NodeInfo node) {
    node.next = this.head.next;
    node.pre = this.head;
    this.head.next.pre = node;
    this.head.next = node;
  }

  private void deleteNode(NodeInfo node) {
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }
}
