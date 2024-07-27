package leetcode.simple;


/**
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
 * 示例 1:
 * 输入: tree = [7,4,3,null,null,6,19], target = 3
 * 输出: 3
 * 解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
 * 示例 2:
 * 输入: tree = [7], target =  7
 * 输出: 7
 * 示例 3:
 * 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * 输出: 4
 */
public class Solution_1379 {

    TreeNode treeNode = null;
    boolean flag = false;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        inOrder(cloned, target);
        return treeNode;
    }

    public void inOrder(TreeNode node, TreeNode target) {
        if (node == null || flag) {
            return;
        }
        if (node.val == target.val) {
            treeNode = node;
            flag = true;
        }
        inOrder(node.left, target);
        inOrder(node.right, target);
    }
}

/*
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}
*/

/*
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> q1 = new ArrayDeque<TreeNode>(), q2 = new ArrayDeque<TreeNode>();
        q1.offer(original);
        q2.offer(cloned);
        while (q1.size() > 0) {
            TreeNode node1 = q1.poll(), node2 = q2.poll();
            if (node1 == target) {
                return node2;
            }
            if (node1.left != null) {
                q1.offer(node1.left);
                q2.offer(node2.left);
            }
            if (node1.right != null) {
                q1.offer(node1.right);
                q2.offer(node2.right);
            }
        }
        return null; // impossible case
    }
}
*/
