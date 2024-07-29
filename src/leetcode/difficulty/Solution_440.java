package leetcode.difficulty;

/**
 * 字典序的第 k 小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * 示例 1:
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: 1
 * 提示:
 * 1 <= k <= n <= 109
 */
public class Solution_440 {
    // 抽象成左右两个字典数
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            long left = cur, right = cur + 1;
            long cnt = 0;
            while (left <= n) {
                cnt += Math.min(n - left + 1, right - left);
                left *= 10;
                right *= 10;
            }
            if (cnt <= k) {
                // 换另一棵字典数，第一颗数的总数小于 k
                k -= cnt;
                cur++;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }
}
