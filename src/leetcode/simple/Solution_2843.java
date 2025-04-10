package leetcode.simple;

/**
 * 统计对称整数的数目
 * 给你两个正整数 low 和 high 。
 * 对于一个由 2 * n 位数字组成的整数 x ，如果其前 n 位数字之和与后 n 位数字之和相等，则认为这个数字是一个对称整数。
 * 返回在 [low, high] 范围内的 对称整数的数目 。
 * 示例 1：
 * 输入：low = 1, high = 100
 * 输出：9
 * 解释：在 1 到 100 范围内共有 9 个对称整数：11、22、33、44、55、66、77、88 和 99 。
 * 示例 2：
 * 输入：low = 1200, high = 1230
 * 输出：4
 * 解释：在 1200 到 1230 范围内共有 4 个对称整数：1203、1212、1221 和 1230 。
 * 提示：
 * 1 <= low <= high <= 104
 */
public class Solution_2843 {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            String s = String.valueOf(i);
            int len = s.length();
            if ((len & 1) != 0) {
                i = (int) Math.pow(10, len);
            } else {
                int left = Integer.parseInt(s.substring(0, len / 2));
                int right = Integer.parseInt(s.substring(len / 2));
                int leftCnt = getCnt(left);
                int rightCnt = getCnt(right);
                if (leftCnt == rightCnt) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int getCnt(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

/*
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            char[] s = Integer.toString(i).toCharArray();
            int n = s.length;
            if (n % 2 > 0) {
                continue;
            }
            int diff = 0;
            for (int j = 0; j < n / 2; j++) {
                diff += s[j];
            }
            for (int j = n / 2; j < n; j++) {
                diff -= s[j];
            }
            if (diff == 0) {
                ans++;
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    private char[] lowS, highS;
    private int n, m, diffLh;
    private int[][][] memo;

    public int countSymmetricIntegers(int low, int high) {
        lowS = String.valueOf(low).toCharArray();
        highS = String.valueOf(high).toCharArray();
        n = highS.length;
        m = n / 2;
        diffLh = n - lowS.length;

        memo = new int[n][diffLh + 1][m * 18 + 1]; // 注意 start <= diffLh
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        // 初始化 diff = m * 9，避免出现负数导致 memo 下标越界
        return dfs(0, -1, m * 9, true, true);
    }

    private int dfs(int i, int start, int diff, boolean limitLow, boolean limitHigh) {
        if (i == n) {
            return diff == m * 9 ? 1 : 0;
        }

        // start 当 isNum 用
        if (start != -1 && !limitLow && !limitHigh && memo[i][start][diff] != -1) {
            return memo[i][start][diff];
        }

        int lo = limitLow && i >= diffLh ? lowS[i - diffLh] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        // 如果前面没有填数字，且剩余数位个数是奇数，那么当前数位不能填数字
        if (start < 0 && (n - i) % 2 > 0) {
            // 如果必须填数字（lo > 0），不合法，返回 0
            return lo > 0 ? 0 : dfs(i + 1, start, diff, true, false);
        }

        int res = 0;
        boolean isLeft = start < 0 || i < (start + n) / 2;
        for (int d = lo; d <= hi; d++) {
            res += dfs(i + 1,
                       start < 0 && d > 0 ? i : start, // 记录第一个填数字的位置
                       diff + (isLeft ? d : -d), // 左半 +，右半 -
                       limitLow && d == lo,
                       limitHigh && d == hi);
        }

        if (start != -1 && !limitLow && !limitHigh) {
            memo[i][start][diff] = res;
        }
        return res;
    }
}
*/