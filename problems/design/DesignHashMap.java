
// LC-706
// Easy
// https://leetcode.com/problems/design-hashmap/

//  Design a HashMap without using any built-in hash table libraries.
//
//  Implement the MyHashMap class:
//
//  MyHashMap() initializes the object with an empty map.
//  void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
//  int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
//  void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
//
//
//
//  Example 1:
//
//  Input
//  ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
//  [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
//  Output
//  [null, null, null, 1, -1, null, 1, null, -1]
//
//  Explanation
//  MyHashMap myHashMap = new MyHashMap();
//  myHashMap.put(1, 1); // The map is now [[1,1]]
//  myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
//  myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
//  myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
//  myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
//  myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
//  myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
//  myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
//
//
//
//  Constraints:
//
//  0 <= key, value <= 106
//  At most 104 calls will be made to put, get, and remove.
//
//
//
//  Follow up: Please do not use the built-in HashMap library.


import java.util.BitSet;

public class DesignHashMap {

  // using BitSet
  class MyHashMap {

    private final int[] arr;
    private final BitSet bitSet;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
      this.arr = new int[1000001];
      this.bitSet = new BitSet(1000001);
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
      arr[key] = value;
      bitSet.set(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
     * for the key
     */
    public int get(int key) {
      return bitSet.get(key) ? arr[key] : -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
      bitSet.clear(key);
    }
  }

  // with rehashing
  class HashMap<K, V> {

    public class Node<K, V> {

      final K key;
      V value;
      Node<K, V> next;

      public Node(K key, V value) {
        this.key = key;
        this.value = value;
      }

      public K getKey() {
        return key;
      }

      public void setValue(V value) {
        this.value = value;
      }

      public V getValue() {
        return value;
      }

    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    int size;
    double loadFactor;
    Node<K, V>[] array;

    public HashMap() {
      this(DEFAULT_CAPACITY, LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int cap, double loadFactor) {
      if (cap < 0) {
        throw new IllegalArgumentException();
      }
      array = (Node<K, V>[]) new Node[cap];
      size = 0;
      this.loadFactor = loadFactor;
    }

    public V put(K key, V value) {
      int index = getIndex(key);
      Node<K, V> head = array[index];
      Node<K, V> cur = head;
      while (cur != null) {
        if (equalsKey(key, cur.getKey())) {
          V v = cur.getValue();
          cur.setValue(value);
          return v;
        }
        cur = cur.next;
      }
      Node<K, V> newNode = new Node<>(key, value);
      newNode.next = head;
      array[index] = newNode;
      size++;
      if (needRehashing()) {
        rehashing();
      }
      return null;
    }

    public boolean containsKey(K key) {
      int index = getIndex(key);
      Node<K, V> cur = array[index];
      while (cur != null) {
        if (equalsKey(key, cur.getKey())) {
          return true;
        }
        cur = cur.next;
      }
      return false;
    }

    public boolean containsValue(V value) {
      if (isEmpty()) {
        return false;
      }

      for (Node<K, V> node : array) {
        while (node != null) {
          if (equalsValue(node.value, value)) {
            return true;
          }
          node = node.next;
        }
      }
      return false;
    }

    public V remove(K key) {
      int index = getIndex(key);
      Node<K, V> cur = array[index];
      Node<K, V> pre = null;
      while (cur != null) {
        if (equalsKey(key, cur.getKey())) {
          if (pre != null) {
            pre.next = cur.next;
          } else {
            array[index] = null;
          }
          cur.next = null;
          size--;
          return cur.getValue();
        }
        pre = cur;
        cur = cur.next;
      }
      return null;
    }

    public V get(K key) {
      int index = getIndex(key);
      Node<K, V> cur = array[index];
      while (cur != null) {
        if (equalsKey(key, cur.getKey())) {
          return cur.getValue();
        }
        cur = cur.next;
      }
      return null;
    }

    public int size() {
      return this.size;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    private int getIndex(K key) {
      return hash(key) % array.length;
    }

    private int hash(K key) {
      if (key == null) {
        return 0;
      }
      return key.hashCode() & 0X7FFFFFFF;
    }

    private boolean equalsKey(K k1, K k2) {
      if (k1 == null && k2 == null) {
        return true;
      }
      if (k1 == null || k2 == null) {
        return false;
      }
      return k1 == k2 || k1.equals(k2);
    }

    private boolean equalsValue(V v1, V v2) {
      if (v1 == null && v2 == null) {
        return true;
      }

      if (v1 == null || v2 == null) {
        return false;
      }

      return v1 == v2 || v1.equals(v2);
    }

    private boolean needRehashing() {
      double ratio = size * 1.0 / array.length;
      return ratio >= loadFactor;
    }

    @SuppressWarnings("unchecked")
    private void rehashing() {
      Node<K, V>[] oldArray = array;
      array = (Node<K, V>[]) new Node[array.length * 2];
      size = 0;
      for (Node<K, V> node : oldArray) {
        while (node != null) {
          put(node.getKey(), node.getValue());
          node = node.next;
        }
      }
    }
  }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
