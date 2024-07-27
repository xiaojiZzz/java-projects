package leetcode.lcr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 同leetcode 47.全排列II
 */
public class Solution_084 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, 0, nums.length, lists, list, used);
        return lists;
    }

    public void backtrack(int[] nums, int start, int end, List<List<Integer>> lists, List<Integer> list, boolean[] used) {
        if (start == end) {
            lists.add(new ArrayList<Integer>(list));
        }
        for (int i = 0; i < end; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backtrack(nums, start + 1, end, lists, list, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
