package leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 * 例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 * 示例 1：
 * 输入：nums = [1,3]
 * 输出：6
 * 解释：[1,3] 共有 4 个子集：
 * - 空子集的异或总和是 0 。
 * - [1] 的异或总和为 1 。
 * - [3] 的异或总和为 3 。
 * - [1,3] 的异或总和为 1 XOR 3 = 2 。
 * 0 + 1 + 3 + 2 = 6
 * 示例 2：
 * 输入：nums = [5,1,6]
 * 输出：28
 * 解释：[5,1,6] 共有 8 个子集：
 * - 空子集的异或总和是 0 。
 * - [5] 的异或总和为 5 。
 * - [1] 的异或总和为 1 。
 * - [6] 的异或总和为 6 。
 * - [5,1] 的异或总和为 5 XOR 1 = 4 。
 * - [5,6] 的异或总和为 5 XOR 6 = 3 。
 * - [1,6] 的异或总和为 1 XOR 6 = 7 。
 * - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 * 示例 3：
 * 输入：nums = [3,4,5,6,7,8]
 * 输出：480
 * 解释：每个子集的全部异或总和值之和为 480 。
 * 提示：
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 */
public class Solution_1863 {
    public int subsetXORSum(int[] nums) {
        int n = nums.length, sum = 0;
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int num : nums) {
            int size = lists.size();
            for (int i = 0; i < size; i++) {
                List<Integer> list = new ArrayList<>(lists.get(i));
                list.add(num);
                lists.add(list);
            }
        }
        for (List<Integer> list : lists) {
            int s = 0;
            for (Integer num : list) {
                s ^= num;
            }
            sum += s;
        }
        return sum;
    }
}

/*
class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length, sum = 0;
        for (int i = 0; i < (1 << n); i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    s ^= nums[j];
                }
            }
            sum += s;
        }
        return sum;
    }
}
*/

/*
class Solution {
    public int subsetXORSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(lists, list, 0, nums);
        int sum = 0;
        for (List<Integer> ls : lists) {
            int s = 0;
            for (Integer num : ls) {
                s ^= num;
            }
            sum += s;
        }
        return sum;
    }

    private void backtrack(List<List<Integer>> lists, List<Integer> list, int idx, int[] nums) {
        lists.add(new ArrayList<>(list));
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(lists, list, i + 1, nums);
            list.remove(list.size() - 1);
        }
    }
}
*/

/*
class Solution {

    private int res;

    public int subsetXORSum(int[] nums) {
        res = 0;
        dfs(nums, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int idx, int val) {
        if (idx == nums.length) {
            res += val;
            return;
        }
        dfs(nums, idx + 1, val ^ nums[idx]);
        dfs(nums, idx + 1, val);
    }
}
*/

/*
class Solution {
    public int subsetXORSum(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int num : nums) {
            res |= num;
        }
        return res << (n - 1);
    }
}
*/