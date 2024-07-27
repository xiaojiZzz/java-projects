package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Solution_47 {
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
            return;
        }
        for (int i = 0; i < end; i++) {
            // !used[i - 1] 关键点，保证相同数据的相对位置不变，从而排除重复情况
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
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
