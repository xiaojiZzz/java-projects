package leetcode.medium;

import java.util.Arrays;


/**
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中：
 *  horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
 * 由于答案可能是一个很大的数字，因此需要将结果 对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 * 示例 2：
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 * 示例 3：
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 */
public class Solution_1465 {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long hm = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        long wm = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            hm = Math.max((horizontalCuts[i] - horizontalCuts[i - 1]), hm);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            wm = Math.max((verticalCuts[i] - verticalCuts[i - 1]), wm);
        }
        return (int) (hm * wm % 1000000009);
    }
}

/*
class Solution {
    int MOD = (int)1e9+7;
    public int maxArea(int h, int w, int[] hs, int[] vs) {
        Arrays.sort(hs);
        Arrays.sort(vs);
        int n = hs.length, m = vs.length;
        int mh = Math.max(hs[0], h - hs[n - 1]), mv = Math.max(vs[0], w - vs[m - 1]);
        for (int i = 1; i < n; i++) mh = Math.max(mh, hs[i] - hs[i - 1]);
        for (int i = 1; i < m; i++) mv = Math.max(mv, vs[i] - vs[i - 1]);
        return (int)((mh * 1L * mv) % MOD);
    }
}
*/
