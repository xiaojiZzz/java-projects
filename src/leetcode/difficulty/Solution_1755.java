package leetcode.difficulty;

import java.util.Arrays;

/**
 * 最接近目标值的子序列和
 * 给你一个整数数组 nums 和一个目标值 goal 。
 * 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要最小化绝对差 abs(sum - goal) 。
 * 返回 abs(sum - goal) 可能的 最小值 。
 * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
 * 示例 1：
 * 输入：nums = [5,-7,3,5], goal = 6
 * 输出：0
 * 解释：选择整个数组作为选出的子序列，元素和为 6 。
 * 子序列和与目标值相等，所以绝对差为 0 。
 * 示例 2：
 * 输入：nums = [7,-9,15,-2], goal = -5
 * 输出：1
 * 解释：选出子序列 [7,-9,-2] ，元素和为 -4 。
 * 绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
 * 示例 3：
 * 输入：nums = [1,2,3], goal = -7
 * 输出：7
 * 提示：
 * 1 <= nums.length <= 40
 * -107 <= nums[i] <= 107
 * -109 <= goal <= 109
 */
public class Solution_1755 {
    // 数组数据组合的指针（表示数组的下标）
    private int point = 0;

    public int minAbsDifference(int[] nums, int goal) {
        int mid = nums.length >> 1;
        int[] l = new int[1 << mid];
        int[] r = new int[1 << (nums.length - mid)];

        // 枚举左侧所有组合
        dfs(nums, 0, mid - 1, 0, l);
        point = 0;
        // 枚举右侧所有组合
        dfs(nums, mid, nums.length - 1, 0, r);

        int ans = Integer.MAX_VALUE;
        Arrays.sort(l);
        Arrays.sort(r);
        int left = 0, right = r.length - 1;

        // 组合左侧数据与右侧数据
        while (left < l.length && right >= 0) {
            int t = l[left] + r[right];
            ans = Math.min(ans, Math.abs(t - goal));
            if (t > goal) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }

    // 因为拆分了数据范围，这里的时间复杂度变为 O(2^(n/2)) n 为 nums 的长度
    private void dfs(int[] nums, int start, int end, int sum, int[] arr) {
        arr[point++] = sum;
        for (int i = start; i <= end; i++) {
            dfs(nums, i + 1, end, sum + nums[i], arr);
        }
    }
}
