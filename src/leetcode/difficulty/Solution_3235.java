package leetcode.difficulty;

/**
 * 判断矩形的两个角落是否可达
 * 给你两个正整数 xCorner 和 yCorner 和一个二维整数数组 circles ，
 * 其中 circles[i] = [xi, yi, ri] 表示一个圆心在 (xi, yi) 半径为 ri 的圆。
 * 坐标平面内有一个左下角在原点，右上角在 (xCorner, yCorner) 的矩形。
 * 你需要判断是否存在一条从左下角到右上角的路径满足：路径 完全 在矩形内部，
 * 不会 触碰或者经过 任何 圆的内部和边界，同时 只 在起点和终点接触到矩形。
 * 如果存在这样的路径，请你返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：X = 3, Y = 4, circles = [[2,1,1]]
 * 输出：true
 * 解释：
 * 黑色曲线表示一条从 (0, 0) 到 (3, 4) 的路径。
 * 示例 2：
 * 输入：X = 3, Y = 3, circles = [[1,1,2]]
 * 输出：false
 * 解释：
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 * 示例 3：
 * 输入：X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
 * 输出：false
 * 解释：
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 * 示例 4：
 * 输入：X = 4, Y = 4, circles = [[5,5,1]]
 * 输出：true
 * 解释：
 * 提示：
 * 3 <= xCorner, yCorner <= 109
 * 1 <= circles.length <= 1000
 * circles[i].length == 3
 * 1 <= xi, yi, ri <= 109
 */
public class Solution_3235 {
    public boolean canReachCorner(int X, int Y, int[][] circles) {
        boolean[] vis = new boolean[circles.length];
        for (int i = 0; i < circles.length; i++) {
            long x = circles[i][0], y = circles[i][1], r = circles[i][2];
            if (inCircle(x, y, r, 0, 0) || // 圆 i 包含矩形左下角
                    inCircle(x, y, r, X, Y) || // 圆 i 包含矩形右上角
                    // 圆 i 是否与矩形上边界/左边界相交相切
                    !vis[i] && (x <= X && Math.abs(y - Y) <= r ||
                            y <= Y && x <= r ||
                            y > Y && inCircle(x, y, r, 0, Y)) && dfs(i, X, Y, circles, vis)) {
                return false;
            }
        }
        return true;
    }

    // 判断点 (x,y) 是否在圆 (ox,oy,r) 内
    private boolean inCircle(long ox, long oy, long r, long x, long y) {
        return (ox - x) * (ox - x) + (oy - y) * (oy - y) <= r * r;
    }

    private boolean dfs(int i, int X, int Y, int[][] circles, boolean[] vis) {
        long x1 = circles[i][0], y1 = circles[i][1], r1 = circles[i][2];
        // 圆 i 是否与矩形右边界/下边界相交相切
        if (y1 <= Y && Math.abs(x1 - X) <= r1 ||
                x1 <= X && y1 <= r1 ||
                x1 > X && inCircle(x1, y1, r1, X, 0)) {
            return true;
        }
        vis[i] = true;
        for (int j = 0; j < circles.length; j++) {
            long x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];
            // 在两圆相交相切的前提下，点 A 是否严格在矩形内
            if (!vis[j] &&
                    (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= (r1 + r2) * (r1 + r2) &&
                    x1 * r2 + x2 * r1 < (r1 + r2) * X &&
                    y1 * r2 + y2 * r1 < (r1 + r2) * Y &&
                    dfs(j, X, Y, circles, vis)) {
                return true;
            }
        }
        return false;
    }
}
