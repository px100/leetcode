
// LC-1115
// https://leetcode.com/problems/print-foobar-alternately/

public class PrintFooBarAlternately {

  class FooBar {

    private int n;
    private volatile boolean justFooIt;

    public FooBar(int n) {
      this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        synchronized (this) {
          while (justFooIt) {
            wait();
          }
          // printFoo.run() outputs "foo". Do not change or remove this line.
          printFoo.run();
          justFooIt = true;
          notifyAll();
        }
      }
    }

    public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        synchronized (this) {
          while (!justFooIt) {
            wait();
          }
          // printBar.run() outputs "bar". Do not change or remove this line.
          printBar.run();
          justFooIt = false;
          notifyAll();
        }
      }
    }
  }
}
