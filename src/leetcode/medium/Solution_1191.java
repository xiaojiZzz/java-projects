package leetcode.medium;

/**
 * K 次串联后最大子数组之和
 * 给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
 * 例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
 * 返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
 * 由于 结果可能会很大，需要返回的 109 + 7 的 模 。
 * 示例 1：
 * 输入：arr = [1,2], k = 3
 * 输出：9
 * 示例 2：
 * 输入：arr = [1,-2,1], k = 5
 * 输出：2
 * 示例 3：
 * 输入：arr = [-1,-2], k = 7
 * 输出：0
 * 提示：
 * 1 <= arr.length <= 105
 * 1 <= k <= 105
 * -104 <= arr[i] <= 104
 */
public class Solution_1191 {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = 1000000007;
        if (k == 1) {
            return maxSubArray(arr) % mod;
        }
        int n = arr.length;
        int[] array = new int[2 * n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            array[i] = array[i + n] = arr[i];
        }
        long ans = ((long) Math.max(sum, 0) * (k - 2) + maxSubArray(array)) % mod;
        return (int) ans;
    }

    private int maxSubArray(int[] nums) {
        int pre = 0;
        int res = 0;
        for (int num : nums) {
            pre = Math.max(pre, 0) + num;
            res = Math.max(res, pre);
        }
        return res;
    }
}

/*
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int max = 0, dp = 0;
        int mod = 1000000007;
        for (int num : arr) {
            dp = Math.max(dp, 0) + num;
            max = Math.max(max, dp);
        }
        if (k == 1) {
            return max % mod;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
            dp = Math.max(dp, 0) + num;
            max = Math.max(max, dp);
        }
        return (int) ((max + (long) (k - 2) * Math.max(0, sum)) % mod);
    }
}
*/
