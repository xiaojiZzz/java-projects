package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class Solution_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            list.add(candidates[i]);
            backtrack(candidates, target, sum + candidates[i], lists, list, i);
            list.remove(list.size() - 1);
        }
    }
}
