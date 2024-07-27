package leetcode.simple;


/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 */
public class Solution_112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}

//public class Solution_112 {
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//        if (root == null) {
//            return false;
//        }
//        LinkedList<TreeNode> treeNodes = new LinkedList<>();
//        treeNodes.offer(root);
//        while (!treeNodes.isEmpty()) {
//            TreeNode node = treeNodes.poll();
//            int value = node.val;
//            if (node.left != null) {
//                node.left.val += value;
//                treeNodes.add(node.left);
//            }
//            if (node.right != null) {
//                node.right.val += value;
//                treeNodes.add(node.right);
//            }
//            if (node.left == null && node.right == null) {
//                if (value == targetSum) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}