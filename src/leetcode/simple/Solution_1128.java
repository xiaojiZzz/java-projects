package leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 等价多米诺骨牌对的数量
 * 给你一组多米诺骨牌 dominoes 。
 * 形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者 (a == d 且 b == c) 。
 * 即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * 示例 1：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * 示例 2：
 * 输入：dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
 * 输出：3
 * 提示：
 * 1 <= dominoes.length <= 4 * 104
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 */
public class Solution_1128 {
    public int numEquivDominoPairs(int[][] dominoes) {
        // map 可以换成数组
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] dominoe : dominoes) {
            int x = dominoe[0], y = dominoe[1];
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            int num = 10 * x + y;
            int v = map.getOrDefault(num, 0);
            ans += v;
            map.put(num, v + 1);
        }
        return ans;
    }
}

/*
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int[][] cnt = new int[10][10];
        for (int[] d : dominoes) {
            int a = d[0];
            int b = d[1];
            ans += a <= b ? cnt[a][b]++ : cnt[b][a]++;
        }
        return ans;
    }
}
*/