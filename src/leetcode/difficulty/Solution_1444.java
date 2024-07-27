package leetcode.difficulty;


/**
 * 给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。
 * 你需要切披萨 k-1 次，得到 k 块披萨并送给别人。切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，
 * 将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，
 * 需要把剩下来的一块送给最后一个人。
 * 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。
 * 输入：pizza = ["A..","AAA","..."], k = 3
 * 输出：3
 * 解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
 */
public class Solution_1444 {
    public int ways(String[] pizza, int k) {
        final int MOD = (int) 1e9 + 7;
        int m = pizza.length, n = pizza[0].length();
        var sum = new int[m + 1][n + 1]; // 二维后缀和
        var f = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                sum[i][j] = sum[i][j + 1] + sum[i + 1][j] - sum[i + 1][j + 1] + (pizza[i].charAt(j) & 1);
                if (sum[i][j] > 0) f[i][j] = 1; // 初始值
            }
        }

        while (--k > 0) {
            var colS = new int[n]; // colS[j] 表示 f 第 j 列的后缀和
            for (int i = m - 1; i >= 0; i--) {
                int rowS = 0; // f[i] 的后缀和
                for (int j = n - 1; j >= 0; j--) {
                    int tmp = f[i][j];
                    if (sum[i][j] == sum[i][j + 1]) // 左边界没有苹果
                        f[i][j] = f[i][j + 1];
                    else if (sum[i][j] == sum[i + 1][j]) // 上边界没有苹果
                        f[i][j] = f[i + 1][j];
                    else // 左边界上边界都有苹果，那么无论怎么切都有苹果
                        f[i][j] = (rowS + colS[j]) % MOD;
                    rowS = (rowS + tmp) % MOD;
                    colS[j] = (colS[j] + tmp) % MOD;
                }
            }
        }
        return f[0][0];
    }
}

/*
public class Solution_1444 {
    public static final int MOD = (int) 1e9 + 7;

    public int ways(String[] pizza, int k) {
        MatrixSum ms = new MatrixSum(pizza);
        int m = pizza.length, n = pizza[0].length();
        var memo = new int[k][m][n];
        for (int i = 0; i < k; i++)
            for (int j = 0; j < m; j++)
                Arrays.fill(memo[i][j], -1); // -1 表示没有计算过
        return dfs(k - 1, 0, 0, memo, ms, m, n);
    }

    private int dfs(int c, int i, int j, int[][][] memo, MatrixSum ms, int m, int n) {
        if (c == 0) // 递归边界：无法再切了
            return ms.query(i, j, m, n) > 0 ? 1 : 0;
        if (memo[c][i][j] != -1) // 之前计算过
            return memo[c][i][j];
        int res = 0;
        for (int j2 = j; j2 < n; j2++) // 垂直切
            if (ms.query(i, j, m, j2) > 0) // 有苹果
                res = (res + dfs(c - 1, i, j2, memo, ms, m, n)) % MOD;
        for (int i2 = i; i2 < m; i2++) // 水平切
            if (ms.query(i, j, i2, n) > 0) // 有苹果
                res = (res + dfs(c - 1, i2, j, memo, ms, m, n)) % MOD;
        return memo[c][i][j] = res; // 记忆化
    }
}

// 二维前缀和模板（'A' 的 ASCII 码最低位为 1，'.' 的 ASCII 码最低位为 0）
class MatrixSum {
    private final int[][] sum;

    public MatrixSum(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (matrix[i].charAt(j) & 1);
            }
        }
    }

    // 返回左上角在 (r1,c1) 右下角在 (r2-1,c2-1) 的子矩阵元素和（类似前缀和的左闭右开）
    public int query(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }
}*/

/*
class Solution {
    public int ways(String[] pizza, int k) {
        final int MOD = (int) 1e9 + 7;
        MatrixSum ms = new MatrixSum(pizza);
        int m = pizza.length, n = pizza[0].length();
        var f = new int[k][m][n];
        for (int c = 0; c < k; c++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (c == 0) {
                        f[c][i][j] = ms.query(i, j, m, n) > 0 ? 1 : 0;
                        continue;
                    }
                    int res = 0;
                    for (int j2 = j; j2 < n; j2++) // 垂直切
                        if (ms.query(i, j, m, j2) > 0) // 有苹果
                            res = (res + f[c - 1][i][j2]) % MOD;
                    for (int i2 = i; i2 < m; i2++) // 水平切
                        if (ms.query(i, j, i2, n) > 0) // 有苹果
                            res = (res + f[c - 1][i2][j]) % MOD;
                    f[c][i][j] = res;
                }
            }
        }
        return f[k - 1][0][0];
    }
}

// 二维前缀和模板（'A' 的 ASCII 码最低位为 1，'.' 的 ASCII 码最低位为 0）
class MatrixSum {
    private final int[][] sum;

    public MatrixSum(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (matrix[i].charAt(j) & 1);
            }
        }
    }

    // 返回左上角在 (r1,c1) 右下角在 (r2-1,c2-1) 的子矩阵元素和（类似前缀和的左闭右开）
    public int query(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }
}
*/

/*
class Solution {
    int n,m;
    String[] pizza;
    int MOD = (int)(1e9 + 7);
    long[][][] dp;
    public int ways(String[] _pizza, int k) {
        pizza = _pizza;
        n = pizza.length;
        m = pizza[0].length();
        dp = new long[n][m][10];
        return (int)dfs(0,n - 1,0,m - 1,k - 1);
    }
    public long dfs(int si,int ei,int sj,int ej,int t){
        if(si > ei || sj > ej) return 0;
        if(t == 0){
            return check(si,ei,sj,ej) ? 1 : 0;
        }
        if(dp[si][sj][t] != 0) return dp[si][sj][t];
        long ans = 0;
        for(int i = si;i <= ei;i++){
            if(check(si,i,sj,ej)){
                ans += dfs(i + 1,ei,sj,ej,t - 1) % MOD;
            }
        }
        for(int j = sj;j <= ej;j++){
            if(check(si,ei,sj,j)){
                ans += dfs(si,ei,j + 1,ej,t - 1) % MOD;
            }
        }
        dp[si][sj][t] = ans;
        return ans % MOD;
    }
    public boolean check(int si,int ei,int sj,int ej){
        for(int i = si;i <= ei;i++){
            for(int j = sj;j <= ej;j++){
                if(pizza[i].charAt(j) == 'A') return true;
            }
        }
        return false;
    }
}
*/
