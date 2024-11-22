import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC-2115
// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

public class FindAllPossibleRecipesFromGivenSupplies {

  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    Map<String, List<Integer>> recipeMap = new HashMap<>();
    int[] recipeIngredientCount = new int[recipes.length];
    for (int i = 0; i < recipes.length; i++) {
      for (String ingredient : ingredients.get(i)) {
        recipeIngredientCount[i]++;
        recipeMap.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(i);
      }
    }
    List<String> result = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();
    for (String supply : supplies) {
      queue.offer(supply);
    }
    while (!queue.isEmpty()) {
      String curIngredient = queue.poll();
      if (!recipeMap.containsKey(curIngredient)) {
        continue;
      }
      for (int ingredient : recipeMap.get(curIngredient)) {
        recipeIngredientCount[ingredient]--;
        if (recipeIngredientCount[ingredient] == 0) {
          queue.offer(recipes[ingredient]);
          result.add(recipes[ingredient]);
        }
      }
    }
    return result;
  }
}
