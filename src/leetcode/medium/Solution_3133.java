package leetcode.medium;

/**
 * 给你两个整数 n 和 x 。你需要构造一个长度为 n 的 正整数 数组 nums ，对于所有 0 <= i < n - 1 ，满足 nums[i + 1] 大于 nums[i] ，
 * 并且数组 nums 中所有元素的按位 AND 运算结果为 x 。
 * 返回 nums[n - 1] 可能的 最小 值。
 * 示例 1：
 * 输入：n = 3, x = 4
 * 输出：6
 * 解释：
 * 数组 nums 可以是 [4,5,6] ，最后一个元素为 6 。
 * 示例 2：
 * 输入：n = 2, x = 7
 * 输出：15
 * 解释：
 * 数组 nums 可以是 [7,15] ，最后一个元素为 15 。
 * 提示：
 * 1 <= n, x <= 108
 */
public class Solution_3133 {
    public long minEnd(int n, int x) {
        n--;
        long ans = x;
        // i 指向要填的空位（0处），j 指向（n - 1）
        int i = 0, j = 0;
        // 这里使用位运算把（n - 1）填入 x 二进制表示的 0 处，也可以使用字符串来填充
        while ((n >> j) > 0) {
            // x 的第 i 个比特值是 0，即「空位」
            if ((ans >> i & 1) == 0) {
                // 空位填入 n 的第 j 个比特值
                ans |= (long) (n >> j & 1) << i;
                j++;
            }
            i++;
        }
        return ans;
    }
}

/*
class Solution {
    public long minEnd(int n, int x) {
        n--;
        long ans = x;
        int j = 0;
        for (long t = ~x, lb; (n >> j) > 0; t ^= lb) {
            lb = t & -t;
            ans |= (long) (n >> j++ & 1) * lb;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long minEnd(int n, int x) {
        n--;
        int j = 0;
        long t = ~x;
        long ans = x;
        while ((n >> j) > 0) {
            long lowbit = t & -t;
            int bit = n >> j & 1;
            ans |= bit * lowbit;
            j++;
            t ^= lowbit;
        }
        return ans;
    }
}
*/