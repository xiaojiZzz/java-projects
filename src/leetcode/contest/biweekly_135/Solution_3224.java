package leetcode.contest.biweekly_135;

/**
 * 使差值相等的最少数组改动次数
 * 给你一个长度为 n 的整数数组 nums ，n 是 偶数 ，同时给你一个整数 k 。
 * 你可以对数组进行一些操作。每次操作中，你可以将数组中 任一 元素替换为 0 到 k 之间的 任一 整数。
 * 执行完所有操作以后，你需要确保最后得到的数组满足以下条件：
 * 存在一个整数 X ，满足对于所有的 (0 <= i < n) 都有 abs(a[i] - a[n - i - 1]) = X 。
 * 请你返回满足以上条件 最少 修改次数。
 * 示例 1：
 * 输入：nums = [1,0,1,2,4,3], k = 4
 * 输出：2
 * 解释：
 * 我们可以执行以下操作：
 * 将 nums[1] 变为 2 ，结果数组为 nums = [1,2,1,2,4,3] 。
 * 将 nums[3] 变为 3 ，结果数组为 nums = [1,2,1,3,4,3] 。
 * 整数 X 为 2 。
 * 示例 2：
 * 输入：nums = [0,1,2,3,3,6,5,4], k = 6
 * 输出：2
 * 解释：
 * 我们可以执行以下操作：
 * 将 nums[3] 变为 0 ，结果数组为 nums = [0,1,2,0,3,6,5,4] 。
 * 将 nums[4] 变为 4 ，结果数组为 nums = [0,1,2,0,4,6,5,4] 。
 * 整数 X 为 4 。
 * 提示：
 * 2 <= n == nums.length <= 105
 * n 是偶数。
 * 0 <= nums[i] <= k <= 105
 */
public class Solution_3224 {
    public int minChanges(int[] nums, int k) {
        int[] cnt = new int[k + 1];
        int[] cnt2 = new int[k + 1];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int p = nums[i];
            int q = nums[n - 1 - i];
            if (p > q) { // 保证 p <= q
                int tmp = p;
                p = q;
                q = tmp;
            }
            cnt[q - p]++;
            cnt2[Math.max(q, k - p)]++;
        }

        int ans = n;
        int sum2 = 0; // 统计有多少对 (p,q) 都要改
        for (int x = 0; x <= k; x++) {
            // 其他 n/2-cnt[x] 对 (p,q) 至少要改一个数，在此基础上，有额外的 sum2 对 (p,q) 还要再改一个数
            ans = Math.min(ans, n / 2 - cnt[x] + sum2);
            // 对于后面的更大的 x，当前的这 cnt2[x] 对 (p,q) 都要改
            sum2 += cnt2[x];
        }
        return ans;
    }
}

/*
class Solution {
    // 差分数组
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[k + 2];
        for (int i = 0; i < n / 2; i++) {
            int p = nums[i];
            int q = nums[n - 1 - i];
            if (p > q) { // 保证 p <= q
                int tmp = p;
                p = q;
                q = tmp;
            }
            int x = q - p;
            int mx = Math.max(q, k - p);
            // [0, x-1] 全部 +1：把 q-p 改成小于 x 的，只需要改 p 或 q 中的一个数
            d[0]++;
            d[x]--;
            // [x+1, mx] 全部 +1：把 q-p 改成大于 x 小于等于 mx 的，也只需要改 p 或 q 中的一个数
            d[x + 1]++;
            d[mx + 1]--;
            // [mx+1, k] 全部 +2：把 q-p 改成大于 mx 的，p 和 q 都需要改
            d[mx + 1] += 2;
        }

        int ans = n;
        int minModify = 0;
        for (int v : d) {
            minModify += v;
            ans = Math.min(ans, minModify);
        }
        return ans;
    }
}
*/
