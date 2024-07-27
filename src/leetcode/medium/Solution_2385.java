package leetcode.medium;

import java.util.*;


/**
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 * 示例 1：
 * 输入：root = [1,5,3,null,4,10,6,9,2], start = 3
 * 输出：4
 * 解释：节点按以下过程被感染：
 * - 第 0 分钟：节点 3
 * - 第 1 分钟：节点 1、10、6
 * - 第 2 分钟：节点5
 * - 第 3 分钟：节点 4
 * - 第 4 分钟：节点 9 和 2
 * 感染整棵树需要 4 分钟，所以返回 4 。
 * 示例 2：
 * 输入：root = [1], start = 1
 * 输出：0
 * 解释：第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
 */
public class Solution_2385 {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        dfs(graph, root);
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{start, 0});
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(start);
        int time = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int nodeVal = arr[0];
            time = arr[1];
            for (int childVal : graph.get(nodeVal)) {
                if (visited.add(childVal)) {
                    queue.offer(new int[]{childVal, time + 1});
                }
            }
        }
        return time;
    }

    public void dfs(Map<Integer, List<Integer>> graph, TreeNode node) {
        graph.putIfAbsent(node.val, new ArrayList<Integer>());
        for (TreeNode child : Arrays.asList(node.left, node.right)) {
            if (child != null) {
                graph.get(node.val).add(child.val);
                graph.putIfAbsent(child.val, new ArrayList<Integer>());
                graph.get(child.val).add(node.val);
                dfs(graph, child);
            }
        }
    }
}
