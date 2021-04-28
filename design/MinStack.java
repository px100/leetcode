
// LC-155
// Easy
// https://leetcode.com/problems/min-stack/

//  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//  Implement the MinStack class:
//
//  MinStack() initializes the stack object.
//  void push(val) pushes the element val onto the stack.
//  void pop() removes the element on the top of the stack.
//  int top() gets the top element of the stack.
//  int getMin() retrieves the minimum element in the stack.
//
//
//
//  Example 1:
//
//  Input
//  ["MinStack","push","push","push","getMin","pop","top","getMin"]
//  [[],[-2],[0],[-3],[],[],[],[]]
//
//  Output
//  [null,null,null,null,-3,null,0,-2]
//
//  Explanation
//  MinStack minStack = new MinStack();
//  minStack.push(-2);
//  minStack.push(0);
//  minStack.push(-3);
//  minStack.getMin(); // return -3
//  minStack.pop();
//  minStack.top();    // return 0
//  minStack.getMin(); // return -2
//
//
//
//  Constraints:
//
//  -231 <= val <= 231 - 1
//  Methods pop, top and getMin operations will always be called on non-empty stacks.
//  At most 3 * 104 calls will be made to push, pop, top, and getMin.
//

import java.util.List;
import java.util.Stack;

public class MinStack {

  class MyMinStack {

    /**
     * initialize your data structure here.
     */

    private final Stack<List<Integer>> stack;

    public MyMinStack() {
      this.stack = new Stack<>();
    }

    public void push(int val) {
      if (stack.isEmpty()) {
        stack.push(List.of(val, val));
      } else {
        int min = Math.min(val, stack.peek().get(1));
        stack.push(List.of(val, min));
      }
    }

    public void pop() {
      stack.pop();
    }

    public int top() {
      return stack.peek().get(0);
    }

    public int getMin() {
      return stack.peek().get(1);
    }
  }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
