import java.util.LinkedList;
import java.util.Queue;

// LC-1306
// https://leetcode.com/problems/jump-game-iii/

public class JumpGameIII {

  public boolean canReachBFS(int[] arr, int start) {
    boolean[] visited = new boolean[arr.length];
    visited[start] = true;

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
      int i = queue.poll();
      visited[i] = true;
      if (arr[i] == 0) {
        return true;
      }
      if (i + arr[i] < arr.length && !visited[i + arr[i]]) {
        queue.add(i + arr[i]);
      }
      if (i - arr[i] >= 0 && !visited[i - arr[i]]) {
        queue.add(i - arr[i]);
      }
    }

    return false;
  }

  public boolean canReachDFS(int[] arr, int start) {
    return dfs(arr, start, new boolean[arr.length]);
  }

  private boolean dfs(int[] arr, int start, boolean[] visited) {
    if (start >= arr.length || start < 0) {
      return false;
    }
    if (visited[start]) {
      return false;
    }

    visited[start] = true;
    if (arr[start] == 0) {
      return true;
    }

    return dfs(arr, start + arr[start], visited)
      || dfs(arr, start - arr[start], visited);
  }
}
