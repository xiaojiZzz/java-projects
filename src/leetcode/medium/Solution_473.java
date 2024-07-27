package leetcode.medium;

import java.util.Arrays;


/**
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。
 * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * 示例 1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 */
public class Solution_473 {
    public boolean makesquare(int[] matchsticks) {
        int k = 4;
        int sum = 0;
        for (int i = 0; i < matchsticks.length; i++)
            sum += matchsticks[i];
        if (sum % k != 0)
            return false;
        int target = sum / k;
        // 排序优化
        Arrays.sort(matchsticks);
        int l = 0, r = matchsticks.length - 1;
        while (l <= r) {
            int temp = matchsticks[l];
            matchsticks[l] = matchsticks[r];
            matchsticks[r] = temp;
            l++;
            r--;
        }
        return backtrack(matchsticks, 0, new int[k], k, target);
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
