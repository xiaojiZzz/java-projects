package leetcode.medium;


/**
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 * 返回小偷的 最小 窃取能力。
 * 示例 1：
 * 输入：nums = [2,3,5,9], k = 2
 * 输出：5
 * 解释：
 * 小偷窃取至少 2 间房屋，共有 3 种方式：
 * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
 * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
 * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
 * 因此，返回 min(5, 9, 9) = 5 。
 * 示例 2：
 * 输入：nums = [2,7,9,3,1], k = 2
 * 输出：2
 * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
 */
public class Solution_2560 {
    public int minCapability(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);
        }
        while (left + 1 < right) { // 开区间写法
            int mid = (left + right) >>> 1;
            if (check(nums, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int k, int mx) {
        int f0 = 0, f1 = 0;
        for (int x : nums) {
            if (x > mx) {
                f0 = f1;
            } else {
                int tmp = f1;
                f1 = Math.max(f1, f0 + 1);
                f0 = tmp;
            }
        }
        return f1 >= k;
    }
}

/*
class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);
        }
        while (left + 1 < right) { // 开区间写法
            int mid = (left + right) >>> 1;
            if (check(nums, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int k, int mx) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= mx) {
                cnt++; // 偷 nums[i]
                i++; // 跳过下一间房子
            }
        }
        return cnt >= k;
    }
}
*/

/*
class Solution {
    public int minCapability(int[] nums, int k) {
        int lower = Arrays.stream(nums).min().getAsInt();
        int upper = Arrays.stream(nums).max().getAsInt();
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            int count = 0;
            boolean visited = false;
            for (int x : nums) {
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }
            if (count >= k) {
                upper = middle - 1;
            } else {
                lower = middle + 1;
            }
        }
        return lower;
    }
}
*/

/*
class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 1, right = (int) 1e9;
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int[] dp = new int[n + 1];
            dp[1] = nums[0] <= mid ? 1 : 0;
            for (int i = 2; i <= n; i++) {
                if (nums[i - 1] > mid) {
                    dp[i] = dp[i - 1];
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + 1);
                }
            }
            if (dp[n] >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
*/
