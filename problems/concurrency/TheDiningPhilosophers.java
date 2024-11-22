
// LC-1226
// https://leetcode.com/problems/the-dining-philosophers/

public class TheDiningPhilosophers {

  class DiningPhilosophers {

    // philosopher 0 - forks 4 and 0,
    // philosopher 1 - forks 0 and 1,
    // philosopher 2 - forks 1 and 2,
    // philosopher 3 - forks 2 and 3,
    // philosopher 4 - forks 3 and 4

    final Object forkLock = new Object();
    boolean[] forks = new boolean[5];

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
      Runnable pickLeftFork,
      Runnable pickRightFork,
      Runnable eat,
      Runnable putLeftFork,
      Runnable putRightFork) throws InterruptedException {

      int left = (philosopher == 0) ? 4 : philosopher - 1;
      int right = philosopher;

      while (true) {
        boolean taken = false;
        synchronized (forkLock) {
          if (!forks[left] && !forks[right]) {
            taken = true;
            forks[left] = true;
            forks[right] = true;
          }
        }
        if (taken) {
          pickLeftFork.run();
          pickRightFork.run();
          eat.run();
          putLeftFork.run();
          putRightFork.run();
          synchronized (forkLock) {
            forks[left] = false;
            forks[right] = false;
          }
          break;
        } else {
          Thread.sleep(1);
        }
      }
    }
  }
}
