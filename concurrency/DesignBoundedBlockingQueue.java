import java.util.LinkedList;
import java.util.Queue;

// LC-1188
// https://leetcode.com/problems/design-bounded-blocking-queue/

public class DesignBoundedBlockingQueue {

  class BoundedBlockingQueue {

    int size;
    Queue<Integer> queue;

    public BoundedBlockingQueue(int capacity) {
      this.queue = new LinkedList<>();
      this.size = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
      synchronized (queue) {
        while (queue.size() == size) {
          queue.wait();
        }
        queue.offer(element);
        queue.notifyAll();
      }
    }

    public int dequeue() throws InterruptedException {
      synchronized (queue) {
        while (queue.size() == 0) {
          queue.wait();
        }
        int num = queue.poll();
        queue.notifyAll();
        return num;
      }
    }

    public int size() {
      return queue.size();
    }
  }
}
