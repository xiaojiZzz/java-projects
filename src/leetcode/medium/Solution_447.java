package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 */
public class Solution_447 {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int i1 = points[i][0], i2 = points[i][1];
                    int j1 = points[j][0], j2 = points[j][1];
                    int k1 = points[k][0], k2 = points[k][1];
                    if ((i1 - j1) * (i1 - j1) + (i2 - j2) * (i2 - j2) == (i1 - k1) * (i1 - k1) + (i2 - k2) * (i2 - k2) ||
                            (j1 - i1) * (j1 - i1) + (j2 - i2) * (j2 - i2) == (j1 - k1) * (j1 - k1) + (j2 - k2) * (j2 - k2) ||
                            (k1 - j1) * (k1 - j1) + (k2 - j2) * (k2 - j2) == (k1 - i1) * (k1 - i1) + (k2 - i2) * (k2 - i2)) {
                        res += 2;
                    }
                }
            }
        }
        return res;
    }
}
*/
