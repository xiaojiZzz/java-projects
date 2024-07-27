package leetcode.medium;


/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class Solution_238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 2];
        int[] post = new int[n + 2];
        pre[0] = 1;
        post[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
            post[n - i + 1] = post[n - i + 2] * nums[n - i];
        }
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) {
            ans[i - 1] = pre[i - 1] * post[i + 1];
        }
        return ans;
    }
}

/*
class Solution {
    //优化空间复杂度
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] ans = new int[len];
        ans[0] = 1;
        int tmp = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            ans[i] *= tmp;
        }
        return ans;
    }
}
*/
