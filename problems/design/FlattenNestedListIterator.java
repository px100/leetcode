import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

// LC-341
// Medium
// https://leetcode.com/problems/flatten-nested-list-iterator/

public class FlattenNestedListIterator {

  /**
   * // This is the interface that allows for creating nested lists. // You should not implement it,
   * or speculate about its implementation public interface NestedInteger {
   * <p>
   * // @return true if this NestedInteger holds a single integer, rather than a nested list. public
   * boolean isInteger();
   * <p>
   * // @return the single integer that this NestedInteger holds, if it holds a single integer //
   * Return null if this NestedInteger holds a nested list public Integer getInteger();
   * <p>
   * // @return the nested list that this NestedInteger holds, if it holds a nested list // Return
   * empty list if this NestedInteger holds a single integer public List<NestedInteger> getList();
   * }
   */

  private interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  // lazy
  public class NestedIterator implements Iterator<Integer> {

    private int pos;
    private Integer cur;
    private NestedIterator iterator;
    private final List<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
      this.pos = 0;
      this.list = nestedList;
    }

    @Override
    public Integer next() {
      if (iterator != null) {
        return iterator.next();
      }
      Integer t = cur;
      cur = null;
      return t;
    }

    @Override
    public boolean hasNext() {
      if (cur != null) {
        return true;
      }
      if (iterator != null) {
        if (iterator.hasNext()) {
          return true;
        }
        iterator = null;
      }
      if (pos >= list.size()) {
        return false;
      }
      NestedInteger ni = list.get(pos++);
      if (ni.isInteger()) {
        cur = ni.getInteger();
        return true;
      }
      iterator = new NestedIterator(ni.getList());
      return hasNext();
    }
  }

  // greedy
  public class NestedIterator2 implements Iterator<Integer> {

    private final Queue<Integer> queue;

    public NestedIterator2(List<NestedInteger> nestedList) {
      this.queue = new ArrayDeque<>();
      helper(nestedList);
    }

    public void helper(List<NestedInteger> nestedList) {
      if (nestedList == null || nestedList.size() == 0) {
        return;
      }
      for (NestedInteger ni : nestedList) {
        if (ni.isInteger()) {
          queue.add(ni.getInteger());
        } else {
          helper(ni.getList());
        }
      }
    }

    @Override
    public Integer next() {
      return queue.poll();
    }

    @Override
    public boolean hasNext() {
      return !queue.isEmpty();
    }
  }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
