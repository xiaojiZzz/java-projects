package leetcode.medium;


/**
 * 给你一个整数数组 nums。
 * 返回两个（不一定不同的）质数在 nums 中 下标 的 最大距离。
 * 示例 1：
 * 输入： nums = [4,2,9,5,3]
 * 输出： 3
 * 解释： nums[1]、nums[3] 和 nums[4] 是质数。因此答案是 |4 - 1| = 3。
 * 示例 2：
 * 输入： nums = [4,8,2,8]
 * 输出： 0
 * 解释： nums[2] 是质数。因为只有一个质数，所以答案是 |2 - 2| = 0。
 */
public class Solution_3115 {
    public int maximumPrimeDifference(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (!isPrime(nums[l])) {
                l++;
            } else {
                break;
            }
        }
        while (r > l) {
            if (!isPrime(nums[r])) {
                r--;
            } else {
                break;
            }
        }
        return r - l + 1;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true; // 2和3是质数
        if (n % 2 == 0 || n % 3 == 0) return false; // 排除偶数和3的倍数
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
