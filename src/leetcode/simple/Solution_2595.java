package leetcode.simple;

/**
 * 给你一个 正 整数 n 。
 * 用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。
 * 用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。
 * 请注意，在数字的二进制表示中，位下标的顺序 从右到左。
 * 返回整数数组 answer ，其中 answer = [even, odd] 。
 * 示例 1：
 * 输入：n = 50
 * 输出：[1,2]
 * 解释：
 * 50 的二进制表示是 110010。
 * 在下标 1，4，5 对应的值为 1。
 * 示例 2：
 * 输入：n = 2
 * 输出：[0,1]
 * 解释：
 * 2 的二进制表示是 10。
 * 只有下标 1 对应的值为 1。
 * 提示：
 * 1 <= n <= 1000
 */
public class Solution_2595 {
    public int[] evenOddBit(int n) {
        int even = 0, odd = 0;
        int idx = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if ((idx & 1) == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
            n /= 2;
            idx++;
        }
        return new int[]{even, odd};
    }
}

/*
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1) {
            ans[i] += n & 1;
            i ^= 1; // 切换奇偶
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int[] evenOddBit(int n) {
        final int MASK = 0x55555555;
        return new int[]{Integer.bitCount(n & MASK), Integer.bitCount(n & (MASK << 1))};
    }
}
*/
