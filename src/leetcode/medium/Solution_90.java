package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class Solution_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, used, 0, list, lists);
        return lists;
    }

    public void backtrack(int[] nums, boolean[] used, int begin, List<Integer> list, List<List<Integer>> lists) {
        if (begin > 1 && !used[begin - 2] && nums[begin - 1] == nums[begin - 2]) {
            return;
        }
        lists.add(new ArrayList<>(list));
        for (int i = begin; i < nums.length; i++) {
            list.add(nums[i]);
            used[i] = true;
            backtrack(nums, used, i + 1, list, lists);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}

/*
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int start) {
        ans.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 跳过当前树层使用过的、相同的元素
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            backtrack(ans, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
*/

/*
class Solution {
    //位运算
    public List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1 << num.length;
        for (int i = 0; i < subsetNum; i++) {
            List<Integer> list = new ArrayList<>();
            boolean illegal = false;
            for (int j = 0; j < num.length; j++) {
                //当前位是 1
                if ((i >> j & 1) == 1) {
                    //当前是重复数字，并且前一位是 0，跳过这种情况
                    if (j > 0 && num[j] == num[j - 1] && (i >> (j - 1) & 1) == 0) {
                        illegal = true;
                        break;
                    } else {
                        list.add(num[j]);
                    }
                }
            }
            if (!illegal) {
                lists.add(list);
            }

        }
        return lists;
    }
}
*/
