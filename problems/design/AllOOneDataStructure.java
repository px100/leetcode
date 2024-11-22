import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// LC-432
// https://leetcode.com/problems/all-oone-data-structure/submissions/

public class AllOOneDataStructure {

  private Node head;
  private Node tail;
  private Map<String, Node> map;

  /**
   * Initialize your data structure here.
   */
  public AllOOneDataStructure() {
    map = new HashMap<>();
    head = new Node(Integer.MIN_VALUE);
    tail = new Node(Integer.MAX_VALUE);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
   */
  public void inc(String key) {
    Node node = map.get(key);
    if (node == null) { // not exist, insert
      Node first = head.next;
      if (isEmptyList() || first.value != 1) { // list empty or value 1 node not exist
        first = insertNode(head, 1);
      }
      updateKey(first, key);
    } else { // exist, increment
      int value = node.value + 1;
      // remove from current node, insert to next node
      Node next = node.next;
      if (value < next.value) { // next node value equal to new value, insert, or create new node
        next = insertNode(node, value);
      }
      removeKey(node, key);
      updateKey(next, key);
    }
  }

  /**
   * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
   */
  public void dec(String key) {
    Node node = map.get(key);
    if (node == null) {
      return;
    }
    if (node.value == 1) { // remove
      map.remove(key);
      removeKey(node, key);
    } else { // decrement
      int value = node.value - 1;
      // remove from current node, insert to next node
      Node prev = node.prev;
      if (value > prev.value) { // prev node value equal to new value, insert, or create new node
        prev = insertNode(prev, value);
      }
      removeKey(node, key);
      updateKey(prev, key);
    }
  }

  /**
   * Returns one of the keys with maximal value.
   */
  public String getMaxKey() {
    return isEmptyList() ? "" : tail.prev.keys.iterator().next();
  }

  /**
   * Returns one of the keys with Minimal value.
   */
  public String getMinKey() {
    return isEmptyList() ? "" : head.next.keys.iterator().next();
  }

  // insert new node after prev node
  private Node insertNode(Node prev, int value) {
    Node node = new Node(value);
    node.keys = new HashSet<>();
    link(prev, node);
    return node;
  }

  // remove key from node key set
  private void removeKey(Node node, String key) {
    node.keys.remove(key);
    if (node.keys.isEmpty()) {
      unlink(node);
    }
  }

  private void updateKey(Node node, String key) {
    node.keys.add(key);
    map.put(key, node);
  }

  private boolean isEmptyList() {
    return (head.next == tail) && (tail.prev == head);
  }

  private void link(Node prev, Node node) {
    Node next = prev.next;
    prev.next = node;
    node.prev = prev;
    node.next = next;
    next.prev = node;
  }

  private void unlink(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private static class Node {

    int value;
    Node prev;
    Node next;
    Set<String> keys;

    Node(int value) {
      this.value = value;
    }
  }
}
