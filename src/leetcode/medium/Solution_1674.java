package leetcode.medium;

/**
 * 使数组互补的最少操作次数
 * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
 * 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。
 * 例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
 * 返回使数组 互补 的 最少 操作次数。
 * 示例 1：
 * 输入：nums = [1,2,4,3], limit = 4
 * 输出：1
 * 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
 * 示例 2：
 * 输入：nums = [1,2,2,1], limit = 2
 * 输出：2
 * 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
 * 示例 3：
 * 输入：nums = [1,2,1,2], limit = 2
 * 输出：0
 * 解释：nums 已经是互补的。
 * 提示：
 * n == nums.length
 * 2 <= n <= 105
 * 1 <= nums[i] <= limit <= 105
 * n 是偶数。
 */
public class Solution_1674 {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int p = nums[i], q = nums[n - 1 - i];
            if (p > q) {
                int tmp = p;
                p = q;
                q = tmp;
            }
            diff[2] += 2;
            diff[1 + p]--;
            diff[p + q]--;
            diff[p + q + 1]++;
            diff[q + limit + 1]++;
        }
        int ans = n;
        int preSum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            preSum += diff[i];
            ans = Math.min(ans, preSum);
        }
        return ans;
    }
}

/*
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] cnt = new int[2 * limit + 2];
        int[] cnt2 = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int p = nums[i], q = nums[n - 1 - i];
            if (p > q) {
                int tmp = p;
                p = q;
                q = tmp;
            }
            cnt[p + q]++;
            cnt2[2]++;
            cnt2[p + 1]--;
            cnt2[q + limit + 1]++;
        }

        int ans = n;
        int sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            sum += cnt2[i];
            ans = Math.min(ans, n / 2 - cnt[i] + sum);
        }
        return ans;
    }
}
*/
