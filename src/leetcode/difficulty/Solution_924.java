package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给出了一个由 n 个节点组成的网络，用 n × n 个邻接矩阵图 graph 表示。
 * 在节点网络中，当 graph[i][j] = 1 时，表示节点 i 能够直接连接到另一个节点 j。
 * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。
 * 这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 如果从 initial 中移除某一节点能够最小化 M(initial)， 返回该节点。如果有多个节点满足条件，就返回索引最小的节点。
 * 请注意，如果某个节点已从受感染节点的列表 initial 中删除，它以后仍有可能因恶意软件传播而受到感染。
 * 示例 1：
 * 输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 * 输出：0
 * 示例 2：
 * 输入：graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
 * 输出：0
 * 示例 3：
 * 输入：graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
 * 输出：1
 */
public class Solution_924 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);

        // 遍历
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (graph[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        // 寻找最优待删除节点
        Arrays.sort(initial);
        int maxSize = 0, index = -1;
        int m = initial.length;
        int[] fi = new int[m];
        int[] cnt = new int[n];
        for (int i = 0; i < m; i++) {
            fi[i] = uf.find(initial[i]);
            cnt[fi[i]] += 1;
        }
        for (int i = 0; i < m; i++) {
            if (cnt[fi[i]] > 1) continue;
            if (uf.size[fi[i]] > maxSize) {
                maxSize = uf.size[fi[i]];
                index = initial[i];
            }
        }
        return index != -1 ? index : initial[0];
    }

    class UnionFind {
        int[] father;
        int[] size;

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x != father[x]) {
                father[x] = find(father[x]);
            }
            return father[x];
        }

        public void union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) return;
            if (size[fx] < size[fy]) {
                father[fx] = fy;
                size[fy] += size[fx];
            } else {
                father[fy] = fx;
                size[fx] += size[fy];
            }
        }
    }
}
