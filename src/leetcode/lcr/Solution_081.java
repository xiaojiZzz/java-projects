package leetcode.lcr;

import java.util.ArrayList;
import java.util.List;


/**
 * 同leetcode 39.组合总和
 */
public class Solution_081 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], res, path, i);
            path.remove(path.size() - 1);
        }
    }
}
