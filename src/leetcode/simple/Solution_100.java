package leetcode.simple;


/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例1:
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例2:
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 */
public class Solution_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //深度优先遍历
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}

/*
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //广度优先遍历
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode ppoll = queue1.poll();
            TreeNode qpoll = queue2.poll();
            if (ppoll.val != qpoll.val) {
                return false;
            }
            TreeNode left1 = ppoll.left;
            TreeNode right1 = ppoll.right;
            TreeNode left2 = qpoll.left;
            TreeNode right2 = qpoll.right;
            if (left1 == null ^ left2 == null) {
                return false;
            }
            if (right1 == null ^ right2 == null) {
                return false;
            }
            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right2 != null) {
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
*/
