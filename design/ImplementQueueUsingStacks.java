
// LC-232
// Easy
// https://leetcode.com/problems/implement-queue-using-stacks/

//  Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
//
//  Implement the MyQueue class:
//
//  void push(int x) Pushes element x to the back of the queue.
//  int pop() Removes the element from the front of the queue and returns it.
//  int peek() Returns the element at the front of the queue.
//  boolean empty() Returns true if the queue is empty, false otherwise.
//
//  Notes:
//
//  You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
//  Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
//
//  Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
//
//
//
//  Example 1:
//
//  Input
//  ["MyQueue", "push", "push", "peek", "pop", "empty"]
//  [[], [1], [2], [], [], []]
//  Output
//  [null, null, null, 1, 1, false]
//
//  Explanation
//  MyQueue myQueue = new MyQueue();
//  myQueue.push(1); // queue is: [1]
//  myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//  myQueue.peek(); // return 1
//  myQueue.pop(); // return 1, queue is [2]
//  myQueue.empty(); // return false
//
//
//
//  Constraints:
//
//  1 <= x <= 9
//  At most 100 calls will be made to push, pop, peek, and empty.
//  All the calls to pop and peek are valid.
//

import java.util.Stack;

public class ImplementQueueUsingStacks {

  // Two Stacks: Time -> O(1) Push, O(n) Pop worst case but O(1) amortized
  class MyQueue {

    /**
     * Initialize your data structure here.
     */

    private final Stack<Integer> stack1;
    private final Stack<Integer> stack2;

    public MyQueue() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
      stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
      peek();
      return stack2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
      while (stack2.isEmpty()) {
        while (!stack1.isEmpty()) {
          stack2.push(stack1.pop());
        }
      }
      return stack2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
      return stack1.isEmpty() && stack2.isEmpty();
    }
  }

  ///////////////////////////////////////////////////////////////////////////

  // Two Stacks: Time ->O(n) Push, O(1) Pop
  class MyQueue2 {

    /**
     * Initialize your data structure here.
     */

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue2() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
      if (stack1.isEmpty()) {
        stack1.push(x);
      } else {

        // move elements to stack 2
        while (!stack1.isEmpty()) {
          stack2.push(stack1.pop());
        }

        // add new element to stack 1
        stack1.push(x);

        // move back all elements to stack 1
        while (!stack2.isEmpty()) {
          stack1.push(stack2.pop());
        }
      }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
      return stack1.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
      return stack1.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
      return stack1.isEmpty();
    }
  }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
