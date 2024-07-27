package leetcode.medium;


/**
 * 给你一个
 * 二进制数组
 * nums 。
 * 如果一个
 * 子数组
 * 中 不存在 两个 相邻 元素的值 相同 的情况，我们称这样的子数组为 交替子数组 。
 * 返回数组 nums 中交替子数组的数量。
 * 示例 1：
 * 输入： nums = [0,1,1,1]
 * 输出： 5
 * 解释：
 * 以下子数组是交替子数组：[0] 、[1] 、[1] 、[1] 以及 [0,1] 。
 * 示例 2：
 * 输入： nums = [1,0,1,0]
 * 输出： 10
 * 解释：
 * 数组的每个子数组都是交替子数组。可以统计在内的子数组共有 10 个。
 */
public class Solution_3101 {
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int[] nor = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nor[i] = nums[i] ^ nums[i + 1];
        }
        long ans = n, len = 0;
        int r = 0;
        while (r < n - 1) {
            while (r < n - 1 && nor[r] == 1) {
                len++;
                r++;
            }
            ans += len + len * (len - 1) / 2;
            len = 0;
            r++;
        }
        return ans;
    }
}

/*
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] != nums[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }
            ans += cnt; // 有 cnt 个右端点下标为 i 的交替子数组
        }
        return ans;
    }
}
*/
