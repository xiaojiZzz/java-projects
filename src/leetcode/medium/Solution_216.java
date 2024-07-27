package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 * 提示:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class Solution_216 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, n, 1, new ArrayList<>(), 0);
        return ans;
    }

    private void backtrack(int k, int n, int idx, List<Integer> list, int sum) {
        if (list.size() == k && sum == n) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i <= 10 - (k - list.size()); i++) {
            if (sum + i <= n) {
                list.add(i);
                backtrack(k, n, i + 1, list, sum + i);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }
}
