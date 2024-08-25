package leetcode.medium;

import java.util.Arrays;


/**
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 */
public class Solution_698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        if (sum % k != 0)
            return false;
        int target = sum / k;
        // 排序优化
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
        return backtrack(nums, 0, new int[k], k, target);
    }

    private boolean backtrack(int[] nums, int index, int[] bucket, int k, int target) {
        // 结束条件优化
        if (index == nums.length)
            return true;
        for (int i = 0; i < k; i++) {
            // 优化点二
            if (i > 0 && bucket[i] == bucket[i - 1])
                continue;
            // 剪枝
            if (bucket[i] + nums[index] > target)
                continue;
            bucket[i] += nums[index];
            if (backtrack(nums, index + 1, bucket, k, target))
                return true;
            bucket[i] -= nums[index];
        }
        return false;
    }
}

/*
import java.util.Arrays;

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        if (sum % k != 0)
            return false;
        int target = sum / k;
        // 排序优化
        Arrays.sort(nums);
        return backtrack(nums, 0, new int[k], k, target);
    }

    private boolean backtrack(int[] nums, int index, int[] bucket, int k, int target) {
        // 结束条件优化
        if (index == nums.length)
            return true;
        for (int i = 0; i < k; i++) {
            // 优化点二
            if (i > 0 && bucket[i] == bucket[i - 1])
                continue;
            // 剪枝，从后往前相加，先分配大的数据，回溯的次数会少很多
            if (bucket[i] + nums[nums.length - 1 - index] > target)
                continue;
            bucket[i] += nums[nums.length - 1 - index];
            if (backtrack(nums, index + 1, bucket, k, target))
                return true;
            bucket[i] -= nums[nums.length - 1 - index];
        }
        return false;
    }
}
*/

/*
class Solution {
    int[] nums;
    int n, t, k;

    public boolean canPartitionKSubsets(int[] _nums, int _k) {
        nums = _nums;
        k = _k;
        int tot = 0;
        for (int x : nums) tot += x;
        if (tot % k != 0) return false; // 可行性剪枝
        Arrays.sort(nums);
        n = nums.length;
        t = tot / k;
        return dfs(n - 1, 0, 0, new boolean[n]);
    }

    boolean dfs(int idx, int cur, int cnt, boolean[] vis) {
        if (cnt == k) return true;
        if (cur == t) return dfs(n - 1, 0, cnt + 1, vis);
        for (int i = idx; i >= 0; i--) {  // 顺序性剪枝
            if (vis[i] || cur + nums[i] > t) continue;  // 可行性剪枝
            vis[i] = true;
            if (dfs(i - 1, cur + nums[i], cnt, vis)) return true;
            vis[i] = false;
            if (cur == 0) return false; // 可行性剪枝
        }
        return false;
    }
}
*/
