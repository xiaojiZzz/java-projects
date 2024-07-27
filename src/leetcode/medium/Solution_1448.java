package leetcode.medium;


/**
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * 示例 1：
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 */
public class Solution_1448 {

    private int goodSum = 0;

    public int goodNodes(TreeNode root) {
        int max = Integer.MIN_VALUE;
        dfs(root, max);
        return goodSum;
    }

    private void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        if (node.val >= max) {
            goodSum++;
            max = node.val;
        }
        dfs(node.left, max);
        dfs(node.right, max);
    }
}

/*
class Solution {
    //bfs
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int goodNum = 0;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> maxValQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        maxValQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int maxVal = maxValQueue.poll();
            if (node.val >= maxVal) {
                goodNum++;
            }
            TreeNode leftChild = node.left;
            if (leftChild != null) {
                nodeQueue.offer(leftChild);
                maxValQueue.offer(Math.max(leftChild.val, maxVal));
            }
            TreeNode rightChild = node.right;
            if (rightChild != null) {
                nodeQueue.offer(rightChild);
                maxValQueue.offer(Math.max(rightChild.val, maxVal));
            }
        }
        return goodNum;
    }
}
*/
