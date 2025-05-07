package leetcode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 到达最后一个房间的最少时间 I
 * 有一个地窖，地窖中有 n x m 个房间，它们呈网格状排布。
 * 给你一个大小为 n x m 的二维数组 moveTime ，其中 moveTime[i][j] 表示在这个时刻 以后 你才可以 开始 往这个房间 移动 。
 * 你在时刻 t = 0 时从房间 (0, 0) 出发，每次可以移动到 相邻 的一个房间。在 相邻 房间之间移动需要的时间为 1 秒。
 * 请你返回到达房间 (n - 1, m - 1) 所需要的 最少 时间。
 * 如果两个房间有一条公共边（可以是水平的也可以是竖直的），那么我们称这两个房间是 相邻 的。
 * 示例 1：
 * 输入：moveTime = [[0,4],[4,4]]
 * 输出：6
 * 解释：
 * 需要花费的最少时间为 6 秒。
 * 在时刻 t == 4 ，从房间 (0, 0) 移动到房间 (1, 0) ，花费 1 秒。
 * 在时刻 t == 5 ，从房间 (1, 0) 移动到房间 (1, 1) ，花费 1 秒。
 * 示例 2：
 * 输入：moveTime = [[0,0,0],[0,0,0]]
 * 输出：3
 * 解释：
 * 需要花费的最少时间为 3 秒。
 * 在时刻 t == 0 ，从房间 (0, 0) 移动到房间 (1, 0) ，花费 1 秒。
 * 在时刻 t == 1 ，从房间 (1, 0) 移动到房间 (1, 1) ，花费 1 秒。
 * 在时刻 t == 2 ，从房间 (1, 1) 移动到房间 (1, 2) ，花费 1 秒。
 * 示例 3：
 * 输入：moveTime = [[0,1],[1,2]]
 * 输出：3
 * 提示：
 * 2 <= n == moveTime.length <= 50
 * 2 <= m == moveTime[i].length <= 50
 * 0 <= moveTime[i][j] <= 109
 */
public class Solution_3341 {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        dist[0][0] = 0;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<State> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new State(0, 0, 0));
        while (!priorityQueue.isEmpty()) {
            State poll = priorityQueue.poll();
            int x = poll.x, y = poll.y, dis = poll.d;
            if (dis > dist[x][y]) {
                continue;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                int newDis = Math.max(moveTime[nx][ny], dis) + 1;
                if (newDis < dist[nx][ny]) {
                    dist[nx][ny] = newDis;
                    priorityQueue.add(new State(nx, ny, newDis));
                }
            }
        }
        return dist[n - 1][m - 1];
    }

    static class State implements Comparable<State> {

        int x;
        int y;
        int d;

        public State(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.d, o.d);
        }
    }
}

/*
// 不使用内部类
class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        dist[0][0] = 0;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        priorityQueue.add(new int[]{0, 0, 0});
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int x = poll[0], y = poll[1], dis = poll[2];
            if (dis > dist[x][y]) {
                continue;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                int newDis = Math.max(moveTime[nx][ny], dis) + 1;
                if (newDis < dist[nx][ny]) {
                    dist[nx][ny] = newDis;
                    priorityQueue.add(new int[]{nx, ny, newDis});
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}
*/
