package leetcode.difficulty;

/**
 * 不包含相邻元素的子序列的最大和
 * 给你一个整数数组 nums 和一个二维数组 queries，其中 queries[i] = [posi, xi]。
 * 对于每个查询 i，首先将 nums[posi] 设置为 xi，然后计算查询 i 的答案，该答案为 nums 中 不包含相邻元素 的子序列的 最大 和。
 * 返回所有查询的答案之和。
 * 由于最终答案可能非常大，返回其对 109 + 7 取余 的结果。
 * 子序列 是指从另一个数组中删除一些或不删除元素而不改变剩余元素顺序得到的数组。
 * 示例 1：
 * 输入：nums = [3,5,9], queries = [[1,-2],[0,-3]]
 * 输出：21
 * 解释：
 * 执行第 1 个查询后，nums = [3,-2,9]，不包含相邻元素的子序列的最大和为 3 + 9 = 12。
 * 执行第 2 个查询后，nums = [-3,-2,9]，不包含相邻元素的子序列的最大和为 9 。
 * 示例 2：
 * 输入：nums = [0,-1], queries = [[0,-5]]
 * 输出：0
 * 解释：
 * 执行第 1 个查询后，nums = [-5,-1]，不包含相邻元素的子序列的最大和为 0（选择空子序列）。
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * -105 <= nums[i] <= 105
 * 1 <= queries.length <= 5 * 104
 * queries[i] == [posi, xi]
 * 0 <= posi <= nums.length - 1
 * -105 <= xi <= 105
 */
public class Solution_3165 {
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        // 4 个数分别保存 f00, f01, f10, f11
        long[][] t = new long[2 << (32 - Integer.numberOfLeadingZeros(n))][4];
        build(t, nums, 1, 0, n - 1);

        long ans = 0;
        for (int[] q : queries) {
            update(t, 1, 0, n - 1, q[0], q[1]);
            ans += t[1][3]; // 注意 f11 没有任何限制，也就是整个数组的打家劫舍
        }
        return (int) (ans % 1_000_000_007);
    }

    // 合并左右儿子
    private void maintain(long[][] t, int o) {
        long[] a = t[o * 2];
        long[] b = t[o * 2 + 1];
        t[o][0] = Math.max(a[0] + b[2], a[1] + b[0]);
        t[o][1] = Math.max(a[0] + b[3], a[1] + b[1]);
        t[o][2] = Math.max(a[2] + b[2], a[3] + b[0]);
        t[o][3] = Math.max(a[2] + b[3], a[3] + b[1]);
    }

    // 用 nums 初始化线段树
    private void build(long[][] t, int[] nums, int o, int l, int r) {
        if (l == r) {
            t[o][3] = Math.max(nums[l], 0);
            return;
        }
        int m = (l + r) / 2;
        build(t, nums, o * 2, l, m);
        build(t, nums, o * 2 + 1, m + 1, r);
        maintain(t, o);
    }

    // 把 nums[i] 改成 val
    private void update(long[][] t, int o, int l, int r, int i, int val) {
        if (l == r) {
            t[o][3] = Math.max(val, 0);
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(t, o * 2, l, m, i, val);
        } else {
            update(t, o * 2 + 1, m + 1, r, i, val);
        }
        maintain(t, o);
    }
}
