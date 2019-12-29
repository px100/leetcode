
// LC-1117
// https://leetcode.com/problems/building-h2o/

public class BuildingH2O {

  class H2O {

    private volatile int count;

    public H2O() {
      this.count = 1;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
      while (count > 2) {
        Thread.yield();
      }
      // releaseHydrogen.run() outputs "H". Do not change or remove this line.
      releaseHydrogen.run();
      count++;
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
      while (count <= 2) {
        Thread.yield();
      }
      // releaseOxygen.run() outputs "O". Do not change or remove this line.
      releaseOxygen.run();
     count = 1;
    }
  }
}
