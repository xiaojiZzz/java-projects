package leetcode.simple;


/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 */
public class Solution_110 {
    //自底向上
    public boolean isBalanced(TreeNode root) {
        return balanced(root) != -1;
    }

    private int balanced(TreeNode node) {
        if (node == null) return 0;
        int leftHeight, rightHeight;
        if ((leftHeight = balanced(node.left)) == -1
                || (rightHeight = balanced(node.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

//public class Solution_110 {
//    //自顶向下
//    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return true;
//        } else {
//            return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//        }
//    }
//
//    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int maxL = maxDepth(root.left);
//        int maxR = maxDepth(root.right);
//        return Math.max(maxL, maxR) + 1;
//    }
//}

//public class Solution_110 {
//    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        int maxDep = 0;
//        LinkedList<TreeNode> treeNodes = new LinkedList<>();
//        treeNodes.offer(root);
//        while (!treeNodes.isEmpty()) {
//            TreeNode node = treeNodes.poll();
//            int leftDep = 0;
//            int rightDep = 0;
//            if (node.left != null) {
//                treeNodes.offer(node.left);
//                leftDep = maxDepth(node.left);
//            }
//            if (node.right != null) {
//                treeNodes.offer(node.right);
//                rightDep = maxDepth(node.right);
//            }
//            maxDep = Math.max(maxDep, Math.abs((leftDep - rightDep)));
//        }
//        if (maxDep <= 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int maxL = maxDepth(root.left);
//        int maxR = maxDepth(root.right);
//        return Math.max(maxL, maxR) + 1;
//    }
//}
