class Solution {
  public List<String> generatePalindromes(String s) {
    int odd = 0;
    Map<Character, Integer> count = new HashMap<>();

    // get character occurrence
    for (final char c : s.toCharArray())
      count.put(c, count.getOrDefault(c, 0) + 1);

    // count odd one
    for (Map.Entry<Character, Integer> entry : count.entrySet())
      if (entry.getValue() % 2 == 1)
        ++odd;

    // can't form any palindrome
    if (odd > 1)
      return new ArrayList<>();

    List<String> ans = new ArrayList<>();
    List<Character> candidates = new ArrayList<>();
    StringBuilder mid = new StringBuilder();

    // get mid and candidates characters
    for (Map.Entry<Character, Integer> entry : count.entrySet()) {
      final char key = entry.getKey();
      final int value = entry.getValue();
      if (value % 2 == 1)
        mid.append(key);
      for (int i = 0; i < value / 2; ++i)
        candidates.add(key);
    }

    // backtracking to generate our ans (strings)
    dfs(candidates, mid, new boolean[candidates.size()], new StringBuilder(), ans);

    return ans;
  }

  // generate all unique palindromes from candidates
  private void dfs(List<Character> candidates, StringBuilder mid, boolean[] used, StringBuilder sb,
                   List<String> ans) {
    if (sb.length() == candidates.size()) {
      ans.add(sb.toString() + mid + sb.reverse().toString());
      sb.reverse();
      return;
    }

    for (int i = 0; i < candidates.size(); ++i) {
      if (used[i])
        continue;
      if (i > 0 && candidates.get(i) == candidates.get(i - 1) && !used[i - 1])
        continue;
      used[i] = true;
      sb.append(candidates.get(i));
      dfs(candidates, mid, used, sb, ans);
      sb.deleteCharAt(sb.length() - 1);
      used[i] = false;
    }
  }
}
