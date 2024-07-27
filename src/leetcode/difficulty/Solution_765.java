package leetcode.difficulty;


/**
 * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。
 * 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 * 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。
 * 示例 1:
 * 输入: row = [0,2,1,3]
 * 输出: 1
 * 解释: 只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 * 输入: row = [3,2,0,1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 */
public class Solution_765 {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int ans = 0;
        int[] cache = new int[n];
        for (int i = 0; i < n; i++) cache[row[i]] = i;
        for (int i = 0; i < n - 1; i += 2) {
            int a = row[i], b = a ^ 1;
            if (row[i + 1] != b) {
                int src = i + 1, tar = cache[b];
                cache[row[tar]] = src;
                cache[row[src]] = tar;
                swap(row, src, tar);
                ans++;
            }
        }
        return ans;
    }
    void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }
}

/*
class Solution {
    int[] p = new int[70];
    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    public int minSwapsCouples(int[] row) {
        int n = row.length, m = n / 2;
        for (int i = 0; i < m; i++) p[i] = i;
        for (int i = 0; i < n; i += 2) union(row[i] / 2, row[i + 1] / 2);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i == find(i)) cnt++;
        }
        return m - cnt;
    }
}
*/

/*
class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int tot = n / 2;

        List<Integer>[] graph = new List[tot];
        for (int i = 0; i < tot; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i += 2) {
            int l = row[i] / 2;
            int r = row[i + 1] / 2;
            if (l != r) {
                graph[l].add(r);
                graph[r].add(l);
            }
        }
        boolean[] visited = new boolean[tot];
        int ret = 0;
        for (int i = 0; i < tot; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<Integer>();
                visited[i] = true;
                queue.offer(i);
                int cnt = 0;

                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    cnt += 1;

                    for (int y : graph[x]) {
                        if (!visited[y]) {
                            visited[y] = true;
                            queue.offer(y);
                        }
                    }
                }
                ret += cnt - 1;
            }
        }
        return ret;
    }
}
*/
