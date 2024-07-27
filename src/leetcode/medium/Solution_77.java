package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Solution_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(n, k, 1, lists, list);
        return lists;
    }

    public void backtrack(int n, int k, int begin, List<List<Integer>> lists, List<Integer> list) {
        if (list.size() == k) {
            lists.add(new ArrayList<>(list));
            return;
        }
        //剪枝
        for (int i = begin; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            backtrack(n, k, i + 1, lists, list);
            list.remove(list.size() - 1);
        }
    }
}
