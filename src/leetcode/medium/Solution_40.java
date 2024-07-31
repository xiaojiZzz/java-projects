package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class Solution_40 {
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

/*
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(candidates, target, 0, lists, list, 0);
        return lists;
    }

    public void backtrack(int[] candidates, int target, int sum, List<List<Integer>> lists, List<Integer> list, int start) {
        if (sum == target) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length && sum + candidates[i] <= target; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            backtrack(candidates, target, sum + candidates[i], lists, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
*/
