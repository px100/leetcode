
// LC-2076
// Hard
// https://leetcode.com/problems/process-restricted-friend-requests/

// You are given an integer n indicating the number of people in a network. Each person is labeled from 0 to n - 1.
//
// You are also given a 0-indexed 2D integer array restrictions,
// where restrictions[i] = [xi, yi] means that person xi and person yi cannot become friends,
// either directly or indirectly through other people.
//
// Initially, no one is friends with each other.
// You are given a list of friend requests as a 0-indexed 2D integer array requests,
// where requests[j] = [uj, vj] is a friend request between person uj and person vj.
//
// A friend request is successful if uj and vj can be friends.
// Each friend request is processed in the given order (i.e., requests[j] occurs before requests[j + 1]),
// and upon a successful request, uj and vj become direct friends for all future friend requests.
//
// Return a boolean array result, where each result[j] is true if the jth friend request is successful or false if it is not.
//
// Note: If uj and vj are already direct friends, the request is still successful.
//
//    Example 1:
//
//    Input: n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
//    Output: [true,false]
//    Explanation:
//    Request 0: Person 0 and person 2 can be friends, so they become direct friends.
//    Request 1: Person 2 and person 1 cannot be friends since person 0 and person 1 would be indirect friends (1--2--0).
//
//    Example 2:
//
//    Input: n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
//    Output: [true,false]
//    Explanation:
//    Request 0: Person 1 and person 2 can be friends, so they become direct friends.
//    Request 1: Person 0 and person 2 cannot be friends since person 0 and person 1 would be indirect friends (0--2--1).
//
//    Example 3:
//
//    Input: n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
//    Output: [true,false,true,false]
//    Explanation:
//    Request 0: Person 0 and person 4 can be friends, so they become direct friends.
//    Request 1: Person 1 and person 2 cannot be friends since they are directly restricted.
//    Request 2: Person 3 and person 1 can be friends, so they become direct friends.
//    Request 3: Person 3 and person 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--1).
//
//    Constraints:
//
//    2 <= n <= 1000
//    0 <= restrictions.length <= 1000
//    restrictions[i].length == 2
//    0 <= xi, yi <= n - 1
//    xi != yi
//    1 <= requests.length <= 1000
//    requests[j].length == 2
//    0 <= uj, vj <= n - 1
//    uj != vj

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProcessRestrictedFriendRequests {

  public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
    UnionFind uf = new UnionFind(n);
    int requestSize = requests.length;
    boolean[] result = new boolean[requestSize];
    for (int i = 0; i < requestSize; i++) {
      int[] request = requests[i];
      int p1 = uf.find(request[0]);
      int p2 = uf.find(request[1]);
      boolean isFriend = true;
      for (int[] restriction : restrictions) {
        int p3 = uf.find(restriction[0]);
        int p4 = uf.find(restriction[1]);
        if ((p1 == p3 && p2 == p4) || (p1 == p4 && p2 == p3)) {
          isFriend = false;
          break;
        }
      }
      if (isFriend) {
        uf.union(p1, p2);
        result[i] = true;
      }
    }
    return result;
  }

  private class UnionFind {

    private int[] parent;
    private int[] size;
    private int count;

    public UnionFind(int n) {
      this.parent = new int[n + 1];
      this.size = new int[n + 1];
      this.count = n;
      for (int i = 0; i <= n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    public int find(int i) {
      if (i != parent[i]) {
        parent[i] = find(parent[i]);
      }
      return parent[i];
    }

    public void union(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (i == j) {
        return;
      }
      if (size[i] > size[j]) {
        parent[j] = i;
        size[i] += size[j];
      } else {
        parent[i] = j;
        size[j] += size[i];
      }
      count--;
    }

    public int getCount() {
      return count;
    }
  }

  /////////////////////////////////////////////////////////////////////////////////

  public boolean[] friendRequests2(int n, int[][] restrictions, int[][] requests) {
    Map<Integer, Set<Integer>> friendMap = new HashMap<>();
    Map<Integer, Set<Integer>> enemyMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
      Set<Integer> friendSet = new HashSet<>();
      friendSet.add(i);
      friendMap.put(i, friendSet);
      enemyMap.put(i, new HashSet<>());
    }
    for (int[] restriction : restrictions) {
      enemyMap.get(restriction[0]).add(restriction[1]);
      enemyMap.get(restriction[1]).add(restriction[0]);
    }
    int k = 0;
    boolean[] result = new boolean[requests.length];
    for (int[] request : requests) {
      int p1 = request[0];
      int p2 = request[1];
      Set<Integer> friendsP1 = friendMap.get(p1);
      Set<Integer> friendsP2 = friendMap.get(p2);
      Set<Integer> enemiesP1 = enemyMap.get(p1);
      Set<Integer> enemiesP2 = enemyMap.get(p2);
      boolean isFriend = canBeFriends(friendsP1, friendsP2, enemiesP1, enemiesP2);
      result[k++] = isFriend;
      if (isFriend) {
        Set<Integer> friendsUnion = unionSet(friendsP1, friendsP2);
        Set<Integer> enemiesUnion = unionSet(enemiesP1, enemiesP2);
        for (int val : friendsUnion) {
          friendMap.put(val, friendsUnion);
          enemyMap.put(val, enemiesUnion);
        }
      }
    }
    return result;
  }

  private boolean canBeFriends(
      Set<Integer> friendsP1, Set<Integer> friendsP2,
      Set<Integer> enemiesP1, Set<Integer> enemiesP2) {
    for (int p : enemiesP1) {
      if (friendsP2.contains(p)) {
        return false;
      }
    }
    for (int p : enemiesP2) {
      if (friendsP1.contains(p)) {
        return false;
      }
    }
    return true;
  }

  private Set<Integer> unionSet(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> unionSet = new HashSet<>(setA);
    unionSet.addAll(setB);
    return unionSet;
  }
}
