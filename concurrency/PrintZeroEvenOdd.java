import java.util.function.IntConsumer;

// LC-1116
// https://leetcode.com/problems/print-zero-even-odd/

public class PrintZeroEvenOdd {

  class ZeroEvenOdd {

    private int n;
    private volatile boolean zeroTurn;
    private volatile boolean oddTurn;

    public ZeroEvenOdd(int n) {
      this.n = n;
      this.zeroTurn = true;
      this.oddTurn = true;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
      synchronized (this) {
        for (int i = 0; i < n; i++) {
          while (!zeroTurn) {
            wait();
          }
          printNumber.accept(0);
          zeroTurn = false;
          notifyAll();
        }
      }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
      synchronized (this) {
        for (int i = 2; i <= n; i += 2) {
          while (zeroTurn || oddTurn) {
            wait();
          }
          printNumber.accept(i);
          zeroTurn = true;
          oddTurn = true;
          notifyAll();
        }
      }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
      synchronized (this) {
        for (int i = 1; i <= n; i += 2) {
          while (zeroTurn || !oddTurn) {
            wait();
          }
          printNumber.accept(i);
          zeroTurn = true;
          oddTurn = false;
          notifyAll();
        }
      }
    }
  }
}
