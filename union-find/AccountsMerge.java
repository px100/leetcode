
// LC-721
// Medium
// https://leetcode.com/problems/accounts-merge/

//  Given a list of accounts where each element accounts[i] is a list of strings,
//  where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//  Now, we would like to merge these accounts.
//  Two accounts definitely belong to the same person if there is some common email to both accounts.
//  Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
//  A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//  After merging the accounts, return the accounts in the following format:
//  the first element of each account is the name,
//  and the rest of the elements are emails in sorted order.
//  The accounts themselves can be returned in any order.
//
//  Example 1:
//
//  Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//  Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//  Explanation:
//  The first and third John's are the same person as they have the common email "johnsmith@mail.com".
//  The second John and Mary are different people as none of their email addresses are used by other accounts.
//  We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//
//  Constraints:
//
//  1 <= accounts.length <= 1000
//  2 <= accounts[i].length <= 10
//  1 <= accounts[i][j] <= 30
//  accounts[i][0] consists of English letters.
//  accounts[i][j] (for j > 0) is a valid email.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMerge {

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, String> emailNameMap = new HashMap<>();
    Map<String, Integer> emailIndexMap = new HashMap<>();
    UnionFind uf = new UnionFind(10000);
    int counter = 0;
    for (List<String> account : accounts) {
      String name = account.get(0);
      for (int i = 1; i < account.size(); i++) {
        String email = account.get(i);
        emailNameMap.put(email, name);
        emailIndexMap.putIfAbsent(email, counter++);
        uf.union(emailIndexMap.get(account.get(1)), emailIndexMap.get(email));
      }
    }

    Map<Integer, List<String>> result = new HashMap<>();
    for (String email : emailNameMap.keySet()) {
      int index = emailIndexMap.get(email);
      int root = uf.find(index);
      result.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
    }

    for (List<String> component : result.values()) {
      Collections.sort(component);
      component.add(0, emailNameMap.get(component.get(0)));
    }
    return new ArrayList<>(result.values());
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
}
