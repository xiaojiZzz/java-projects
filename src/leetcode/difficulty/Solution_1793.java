package leetcode.difficulty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
 * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 * 请你返回 好 子数组的最大可能 分数 。
 * 示例 1：
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 * 示例 2：
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 */
public class Solution_1793 {
    // 双指针
    public int maximumScore(int[] nums, int k) {
        int maxValue = 0;
        int l = k - 1, r = k + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = nums[k]; ; i--) {
            while (l >= 0 && i <= nums[l]) {
                l--;
            }
            while (r < nums.length && i <= nums[r]) {
                r++;
            }
            maxValue = Math.max(maxValue, (r - l - 1) * i);
            if (l < 0 && r >= nums.length) {
                break;
            }
        }
        return maxValue;
    }
}

/*
class Solution {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int ans = 0;
        for (int i = nums[k];;) {
            while (left >= 0 && nums[left] >= i) {
                --left;
            }
            while (right < n && nums[right] >= i) {
                ++right;
            }
            ans = Math.max(ans, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
            i = Math.max((left == -1 ? -1 : nums[left]), (right == n ? -1 : nums[right]));
            if (i == -1) {
                break;
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    // 单调栈
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                r[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l[stack.pop()] = i;
            }
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (r[i] > k && l[i] < k) {
                ans = Math.max(ans, (r[i] - l[i] - 1) * nums[i]);
            }
        }
        return ans;
    }
}
*/
