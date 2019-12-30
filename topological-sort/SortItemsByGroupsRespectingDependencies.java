import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// LC-1203
// https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/

public class SortItemsByGroupsRespectingDependencies {

  private static class Tree {

    int num;
    int group;
    boolean visited;
    boolean inserted;
    List<Tree> parents = new LinkedList<>();
  }

  public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    // buildItemInGroups
    Map<Integer, Tree> itemMap = new HashMap<>();
    for (int i = 0; i < group.length; i++) {
      Tree item = new Tree();
      if (group[i] >= 0) {
        item.group = group[i];
        item.num = i;
      } else {
        item.num = i;
        item.group = m++;
      }
      itemMap.put(i, item);
    }
    int i = 0;
    for (List<Integer> beforeItem : beforeItems) {
      for (Integer before : beforeItem) {
        itemMap.get(i).parents.add(itemMap.get(before));
      }
      i++;
    }
    // buildItemDependencies
    Map<Integer, List<Tree>> groupItemsSorted = new HashMap<>();
    for (i = 0; i < m; i++) {
      groupItemsSorted.put(i, new LinkedList<>());
    }

    for (Tree item : itemMap.values()) {
      if (!item.inserted) {
        List<Tree> sortedItemsSource = new LinkedList<>();
        if (!topologicalSort(item, sortedItemsSource, item.group)) {
          return new int[0];
        }
        List<Tree> sortedItemsDestination = groupItemsSorted.get(item.group);
        sortedItemsDestination.addAll(sortedItemsSource);
      }
    }
    // buildGroupTree
    Map<Integer, Tree> groupMap = new HashMap<>();
    for (i = 0; i < m; i++) {
      Tree group0 = new Tree();
      group0.num = i;
      groupMap.put(i, group0);
    }
    for (Tree item : itemMap.values()) {
      for (Tree parent : item.parents) {
        if (item.group != parent.group) {
          Tree group0 = groupMap.get(item.group);
          group0.parents.add(groupMap.get(parent.group));
        }
      }
    }
    // buildGroupDependencies
    List<Tree> sortedGroup = new LinkedList<>();
    for (Tree group0 : groupMap.values()) {
      if (!group0.inserted) {
        List<Tree> sortedItemsSource = new LinkedList<>();
        if (!topologicalSort(group0, sortedItemsSource, -1)) {
          return new int[0];
        }
        sortedGroup.addAll(sortedItemsSource);
      }
    }

    int idx = 0;
    int[] result = new int[n];
    for (Tree _group : sortedGroup) {
      for (Tree item : groupItemsSorted.get(_group.num)) {
        result[idx++] = item.num;
      }
    }

    return result;
  }

  private boolean topologicalSort(Tree node, List<Tree> sorted, int group) {
    if (group != -1 && node.group != group) {
      return true;
    }
    if (node.inserted) {
      return true;
    }
    if (node.visited) {
      return false;
    }

    node.visited = true;
    for (Tree parent : node.parents) {
      if (!topologicalSort(parent, sorted, group)) {
        return false;
      }
    }
    node.inserted = true;
    sorted.add(node);

    return true;
  }
}
