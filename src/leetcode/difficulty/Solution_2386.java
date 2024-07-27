package leetcode.difficulty;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * 给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。
 * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）
 * 返回数组的 第 k 大和 。
 * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。
 * 注意：空子序列的和视作 0 。
 * 示例 1：
 * 输入：nums = [2,4,-2], k = 5
 * 输出：2
 * 解释：所有可能获得的子序列和列出如下，按递减顺序排列：
 * - 6、4、4、2、2、0、0、-2
 * 数组的第 5 大和是 2 。
 * 示例 2：
 * 输入：nums = [1,-2,3,4,-10,12], k = 16
 * 输出：10
 * 解释：数组的第 16 大和是 10 。
 */
public class Solution_2386 {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long total = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                total += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);

        long ret = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{nums[0], 0});
        for (int j = 2; j <= k; j++) {
            long[] arr = pq.poll();
            long t = arr[0];
            int i = (int) arr[1];
            ret = t;
            if (i == n - 1) {
                continue;
            }
            pq.offer(new long[]{t + nums[i + 1], i + 1});
            pq.offer(new long[]{t - nums[i] + nums[i + 1], i + 1});
        }
        return total - ret;
    }
}

/*
class Solution {
    public long kSum(int[] nums, int k) {
        long sum = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
            right += nums[i];
        }
        Arrays.sort(nums);

        long left = -1;
        while (left + 1 < right) { // 开区间二分，原理见【前置知识】
            long mid = (left + right) / 2;
            cnt = k - 1; // 空子序列算一个
            dfs(0, mid, nums);
            if (cnt == 0) { // 找到 k 个元素和不超过 mid 的子序列
                right = mid;
            } else {
                left = mid;
            }
        }
        return sum - right;
    }

    private int cnt;

    // 反向递归，增加改成减少，这样可以少传一些参数
    private void dfs(int i, long s, int[] nums) {
        if (cnt == 0 || i == nums.length || s < nums[i]) {
            return;
        }
        cnt--;
        dfs(i + 1, s - nums[i], nums); // 选
        dfs(i + 1, s, nums); // 不选
    }
}
*/

/*
class Solution {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        int[] backupNums = Arrays.copyOf(nums, n);   // 为不直接修改输入，备份一个数组
        long maxSum = 0;       // 最大子序列和，所有正数和
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                maxSum += nums[i];
            }
            backupNums[i] = Math.abs(nums[i]);   // 得到元素的绝对值数组
        }
        Arrays.sort(backupNums); // 对绝对值数组排序

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> a[0] > b[0] ? 1 : -1);   // 小顶堆，队列中存储的是<子序列和，子序列最后一个元素的索引>
        pq.offer(new long[]{0, -1});  // 初始子序列和为0，最后一个索引为-1，表示空子序列
        // 循环k-1次，弹出绝对值前k-1最小子序列和
        for (int i = 0; i < k - 1; i++) {
            long[] p = pq.poll();
            int curr = (int) p[1];    // 当前子序列最后一个元素索引
            if (curr == n - 1) continue;   // 到达末尾跳过处理
            long subSum = p[0] + backupNums[curr + 1];  // 当前子序列和
            if (curr >= 0)
                pq.offer(new long[]{subSum - backupNums[curr], curr + 1});    // 非空子序列，以最后一个元素的下一个元素替换最后一个元素
            pq.offer(new long[]{subSum, curr + 1});   // 加入最后一个元素的下一个元素
        }
        return maxSum - pq.peek()[0];     // 最终堆顶的即为第k小的绝对值和，差值即为第k大子序列和
    }
}
*/
