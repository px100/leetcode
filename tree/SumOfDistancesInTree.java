import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// LC-834
// https://leetcode.com/problems/sum-of-distances-in-tree/

public class SumOfDistancesInTree {

  public int[] sumOfDistancesInTree(int N, int[][] edges) {
    ArrayList[] map = IntStream.range(0, N)
      .<ArrayList<Integer>>mapToObj(i -> new ArrayList<>())
      .toArray(ArrayList[]::new);

    Arrays.stream(edges).forEach(edge -> {
      map[edge[0]].add(edge[1]);
      map[edge[1]].add(edge[0]);
    });

    int[] counts = new int[N];
    int[] distance = new int[N];
    postOrder(0, -1, map, counts, distance);
    preOrder(0, -1, map, counts, distance);

    return distance;
  }

  private void postOrder(int curr, int last, List<Integer>[] map, int[] counts, int[] dis) {
    map[curr].stream()
      .mapToInt(next -> next)
      .filter(next -> next != last)
      .forEach(next -> {
        postOrder(next, curr, map, counts, dis);
        counts[curr] += counts[next];
        dis[curr] += dis[next] + counts[next];
      });
    counts[curr]++;
  }

  private void preOrder(int curr, int last, List<Integer>[] map, int[] counts, int[] dis) {
    map[curr].stream()
      .mapToInt(next -> next)
      .filter(next -> next != last)
      .forEach(next -> {
        dis[next] = dis[curr] - counts[next] + dis.length - counts[next];
        preOrder(next, curr, map, counts, dis);
      });
  }
}
