package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Solution_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(nums, 0, lists, list);
        return lists;
    }

    public void backtrack(int[] nums, int begin, List<List<Integer>> lists, List<Integer> list) {
        lists.add(new ArrayList<>(list));
        for (int i = begin; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, i + 1, lists, list);
            list.remove(list.size() - 1);
        }
    }
}

/*class Solution {
    //位运算
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++)
                if (((i >> j) & 1) == 1) sub.add(nums[j]);
            res.add(sub);
        }
        return res;
    }
}*/

/*
class Solution {
    // 双重for
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // 将空集加入到结果集中
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i)); // 复制已有子集
                subset.add(num); // 将当前元素加入到子集中
                res.add(subset); // 将新的子集加入到结果集中
            }
        }
        return res;
    }
}
*/