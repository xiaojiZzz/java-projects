package leetcode.difficulty;

/**
 * 统计满足 K 约束的子字符串数量 II
 * 给你一个 二进制 字符串 s 和一个整数 k。
 * 另给你一个二维整数数组 queries ，其中 queries[i] = [li, ri] 。
 * 如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
 * 字符串中 0 的数量最多为 k。
 * 字符串中 1 的数量最多为 k。
 * 返回一个整数数组 answer ，其中 answer[i] 表示 s[li..ri] 中满足 k 约束 的子字符串的数量。
 * 示例 1：
 * 输入：s = "0001111", k = 2, queries = [[0,6]]
 * 输出：[26]
 * 解释：
 * 对于查询 [0, 6]， s[0..6] = "0001111" 的所有子字符串中，
 * 除 s[0..5] = "000111" 和 s[0..6] = "0001111" 外，其余子字符串都满足 k 约束。
 * 示例 2：
 * 输入：s = "010101", k = 1, queries = [[0,5],[1,4],[2,3]]
 * 输出：[15,9,3]
 * 解释：
 * s 的所有子字符串中，长度大于 3 的子字符串都不满足 k 约束。
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 是 '0' 或 '1'
 * 1 <= k <= s.length
 * 1 <= queries.length <= 105
 * queries[i] == [li, ri]
 * 0 <= li <= ri < s.length
 * 所有查询互不相同
 */
public class Solution_3261 {
    public long[] countKConstraintSubstrings(String S, int k, int[][] queries) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] left = new int[n];
        long[] sum = new long[n + 1];
        int[] cnt = new int[2];
        int l = 0;
        for (int i = 0; i < n; i++) {
            cnt[s[i] & 1]++;
            while (cnt[0] > k && cnt[1] > k) {
                cnt[s[l++] & 1]--;
            }
            // 记录合法子串右端点 i 对应的最小左端点 l
            left[i] = l;
            // 计算 i-left[i]+1 的前缀和
            sum[i + 1] = sum[i] + i - l + 1;
        }

        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int ql = queries[i][0];
            int qr = queries[i][1];
            // 如果区间内所有数都小于 ql，结果是 j=qr+1
            int j = lowerBound(left, ql - 1, qr + 1, ql);
            ans[i] = sum[qr + 1] - sum[j] + (long) (j - ql + 1) * (j - ql) / 2;
        }
        return ans;
    }

    private int lowerBound(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}

/*
class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int[] count = new int[2];
        int[] right = new int[n];
        Arrays.fill(right, n);
        long[] prefix = new long[n + 1];
        for (int i = 0, j = 0; j < n; ++j) {
            count[s.charAt(j) - '0']++;
            while (count[0] > k && count[1] > k) {
                count[s.charAt(i) - '0']--;
                right[i] = j;
                i++;
            }
            prefix[j + 1] = prefix[j] + j - i + 1;
        }

        long[] res = new long[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0], r = queries[q][1];
            int i = Math.min(right[l], r + 1);
            long part1 = (long) (i - l + 1) * (i - l) / 2;
            long part2 = prefix[r + 1] - prefix[i];
            res[q] = part1 + part2;
        }
        return res;
    }
}
*/