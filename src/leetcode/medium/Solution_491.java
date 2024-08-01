package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 非递减子序列
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * 示例 1：
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * 示例 2：
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 * 提示：
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class Solution_491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int start) {
        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (path.isEmpty() || nums[i] >= path.get(path.size() - 1)) {
                used.add(nums[i]);
                path.add(nums[i]);
                backtrack(ans, path, nums, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}

/*
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, 0, -100, nums.length);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int start, int last, int len) {
        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }
        // 数组去重
        boolean[] used = new boolean[201];
        for (int i = start; i < len; i++) {
            if (used[nums[i] + 100]) {
                continue;
            }
            if (path.isEmpty() || nums[i] >= last) {
                used[nums[i] + 100] = true;
                path.add(nums[i]);
                backtrack(ans, path, nums, i + 1, nums[i], len);
                path.remove(path.size() - 1);
            }
        }
    }
}
*/
