package leetcode.lcr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 同leetcode 40.组合总和II
 */
public class Solution_082 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        Arrays.sort(candidates);
        backtrack(candidates, target, sum, lists, list, 0);
        return lists;
    }

    public void backtrack(int[] candidates, int target, int sum, List<List<Integer>> lists, List<Integer> list, int start) {
        if (sum == target) {
            lists.add(new ArrayList<>(list));
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            sum += candidates[i];
            backtrack(candidates, target, sum, lists, list, i + 1);
            sum -= candidates[i];
            list.remove(list.size() - 1);
        }
    }
}
