
// LC-705
// Easy
// https://leetcode.com/problems/design-hashset/

import java.util.BitSet;

//  Design a HashSet without using any built-in hash table libraries.
//
//  Implement MyHashSet class:
//
//  void add(key) Inserts the value key into the HashSet.
//  bool contains(key) Returns whether the value key exists in the HashSet or not.
//  void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
//
//  Example 1:
//
//  Input
//  ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
//  [[], [1], [2], [1], [3], [2], [2], [2], [2]]
//  Output
//  [null, null, null, true, false, null, true, null, false]
//
//  Explanation
//  MyHashSet myHashSet = new MyHashSet();
//  myHashSet.add(1);      // set = [1]
//  myHashSet.add(2);      // set = [1, 2]
//  myHashSet.contains(1); // return True
//  myHashSet.contains(3); // return False, (not found)
//  myHashSet.add(2);      // set = [1, 2]
//  myHashSet.contains(2); // return True
//  myHashSet.remove(2);   // set = [1]
//  myHashSet.contains(2); // return False, (already removed)
//
//  Constraints:
//
//  0 <= key <= 106
//  At most 104 calls will be made to add, remove, and contains.
//
//
//  Follow up: Could you solve the problem without using the built-in HashSet library?

public class DesignHashSet {

  // Bitset
  class MyHashSet {

    private final BitSet bitSet;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
      this.bitSet = new BitSet(1000001);
    }

    public void add(int key) {
      bitSet.set(key);
    }

    public void remove(int key) {
      bitSet.clear(key);
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
      return bitSet.get(key);
    }
  }

  // bucket; bit manipulation
  class MyHashSet2 {

    private final int[] set;
    private final int BUCKET_SIZE = 1 << 4;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet2() {
      int MAX_SET_SIZE = 1 << 20;
      this.set = new int[MAX_SET_SIZE / BUCKET_SIZE];
    }

    public void add(int key) {
      int bank = key / BUCKET_SIZE;
      if (bank >= set.length) {
        return;
      }
      set[bank] |= 1 << (key % BUCKET_SIZE);
    }

    public void remove(int key) {
      int bank = key / BUCKET_SIZE;
      if (bank >= set.length) {
        return;
      }
      set[bank] &= ~(1 << (key % BUCKET_SIZE));
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
      int bank = key / BUCKET_SIZE;
      if (bank >= set.length) {
        return false;
      }
      return (set[bank] & (1 << (key % BUCKET_SIZE))) != 0;
    }
  }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}
