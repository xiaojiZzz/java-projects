package leetcode.difficulty;

import java.util.*;


/**
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。
 * 树的根节点是编号为 0 的节点。树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * 实现 TreeAncestor 类：
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 * 示例 1：
 * 输入：
 * ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 * 输出：
 * [null,1,0,-1]
 * 解释：
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
 * treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
 * treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
 */
public class Solution_1483 {
    class TreeAncestor {

        private final int rank = 16;
        private int[][] dp;

        public TreeAncestor(int n, int[] parent) {
            dp = new int[n][rank];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            for (int i = 0; i < n; i++) {
                dp[i][0] = parent[i];
            }
            for (int j = 1; j < rank; j++) {
                for (int i = 0; i < n; i++) {
                    if (dp[i][j - 1] != -1) {
                        dp[i][j] = dp[dp[i][j - 1]][j - 1];
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            for (int i = 0; i < rank; i++) {
                if (((k >> i) & 1) != 0) {
                    node = dp[node][i];
                    if (node == -1) {
                        return -1;
                    }
                }
            }
            return node;
        }
    }

    /*
    class TreeAncestor {

        int[] idx2Ori;
        int[] ori2Idx;;
        int idx = 0;
        int[] level;
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        public TreeAncestor(int n, int[] parent) {
            List<Integer>[] children = new ArrayList[n];
            for (int i = 1; i < parent.length; ++i) {
                if (children[parent[i]] == null) {
                    children[parent[i]] = new ArrayList<>();
                }

                children[parent[i]].add(i);
            }

            idx2Ori = new int[n];
            ori2Idx = new int[n];
            level = new int[n];

            dfs(children, 0, 0);
        }

        private void dfs(List<Integer>[] children, int cur, int curLevel) {

            if (children[cur] != null) {
                for (int c : children[cur]) {
                    dfs(children, c, curLevel + 1);
                }
            }

            idx2Ori[idx] = cur;
            ori2Idx[cur] = idx;
            level[idx] = curLevel;
            List<Integer> levelNodes = levelMap.get(curLevel);
            if (levelNodes == null) {
                levelNodes = new ArrayList<>();
                levelMap.put(curLevel, levelNodes);
            }
            levelNodes.add(idx);
            idx++;
        }

        public int getKthAncestor(int node, int k) {
            int curIdx = ori2Idx[node];
            int curLevel = level[curIdx];
            if (curLevel - k < 0) {
                return -1;
            }

            List<Integer> levelNodes = levelMap.get(curLevel - k);
            int l = 0;
            int r = levelNodes.size() - 1;
            while (l <= r) {
                int m = r - ((r - l + 1) >> 1);
                if (levelNodes.get(m) >= curIdx) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return idx2Ori[levelNodes.get(l)];
        }
    }
    */
}
