package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 双模幂运算
 * 给你一个下标从 0 开始的二维数组 variables ，其中 variables[i] = [ai, bi, ci, mi]，以及一个整数 target 。
 * 如果满足以下公式，则下标 i 是 好下标：
 * 0 <= i < variables.length
 * ((ai^bi % 10)^ci) % mi == target
 * 返回一个由 好下标 组成的数组，顺序不限 。
 * 示例 1：
 * 输入：variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
 * 输出：[0,2]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [2,3,3,10] ，(23 % 10)3 % 10 = 2 。
 * 2) 对于下标 1 ，variables[1] = [3,3,3,1] ，(33 % 10)3 % 1 = 0 。
 * 3) 对于下标 2 ，variables[2] = [6,1,1,4] ，(61 % 10)1 % 4 = 2 。
 * 因此，返回 [0,2] 作为答案。
 * 示例 2：
 * 输入：variables = [[39,3,1000,1000]], target = 17
 * 输出：[]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [39,3,1000,1000] ，(393 % 10)1000 % 1000 = 1 。
 * 因此，返回 [] 作为答案。
 * 提示：
 * 1 <= variables.length <= 100
 * variables[i] == [ai, bi, ci, mi]
 * 1 <= ai, bi, ci, mi <= 103
 * 0 <= target <= 103
 */
public class Solution_2961 {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        int n = variables.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            if (myPow(myPow(a, b, 10), c, m) == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int myPow(int x, int n, int mod) {
        return quickMul(x, n, mod);
    }

    private int quickMul(int x, int n, int mod) {
        int ans = 1;
        int x_contribute = x;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = (ans * x_contribute) % mod;
            }
            x_contribute = (x_contribute * x_contribute) % mod;
            n /= 2;
        }
        return ans;
    }
}
