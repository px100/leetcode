import java.util.function.IntConsumer;

// LC-1195
// https://leetcode.com/problems/fizz-buzz-multithreaded/

public class FizzBuzzMultithreaded {

  class FizzBuzz {

    private int n;

    private int count;

    public FizzBuzz(int n) {
      this.n = n;
      this.count = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
      while (count <= n) {
        synchronized (this) {
          while (count <= n && (count % 3 != 0 || count % 5 == 0)) {
            wait();
          }
          if (count <= n) {
            printFizz.run();
          }
          count++;
          notifyAll();
        }
      }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
      while (count <= n) {
        synchronized (this) {
          while (count <= n && (count % 5 != 0 || count % 3 == 0)) {
            wait();
          }
          if (count <= n) {
            printBuzz.run();
          }
          count++;
          notifyAll();
        }
      }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
      while (count <= n) {
        synchronized (this) {
          while (count <= n && (count % 5 != 0 || count % 3 != 0)) {
            wait();
          }
          if (count <= n) {
            printFizzBuzz.run();
          }
          count++;
          notifyAll();
        }
      }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
      while (count <= n) {
        synchronized (this) {
          while (count <= n && (count % 5 == 0 || count % 3 == 0)) {
            wait();
          }
          if (count <= n) {
            printNumber.accept(count);
          }
          count++;
          notifyAll();
        }
      }
    }
  }
}
