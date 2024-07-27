package leetcode.medium;


/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 示例 1:
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 * 输入: root = [], key = 0
 * 输出: []
 */
public class Solution_450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            successor.left = root.left;
            return root.right;
        }
        return root;
    }
}

/*
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 寻找对应的对应的前面的节点，以及他的前一个节点
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.val < key) {
                pre = cur;
                cur = cur.right;
            } else if (cur.val > key) {
                pre = cur;
                cur = cur.left;
            } else {
                break;
            }
        }
        if (pre == null) {
            return deleteOneNode(cur);
        }
        if (pre.left != null && pre.left.val == key) {
            pre.left = deleteOneNode(cur);
        }
        if (pre.right != null && pre.right.val == key) {
            pre.right = deleteOneNode(cur);
        }
        return root;
    }

    public TreeNode deleteOneNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.left;
        }
        TreeNode cur = node.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        cur.left = node.left;
        return node.right;
    }
}
*/
