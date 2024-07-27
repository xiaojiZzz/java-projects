package leetcode.simple;


/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，返回它的最大深度 3 。
 */
public class Solution_104 {
    //Morris
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode pre = null;
        int depth = 1;
        int maxDepth = Integer.MIN_VALUE;
        while (root != null) {
            if ((pre = root.left) != null) {
                int n = 1;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                    n++;
                }
                if (pre.right == null) {
                    maxDepth = Math.max(maxDepth, depth);
                    pre.right = root;
                    root = root.left;
                    depth++;
                } else {
                    pre.right = null;
                    root = root.right;
                    depth = depth - n;
                }
            } else {
                maxDepth = Math.max(maxDepth, depth);
                root = root.right;
                depth++;
            }
        }
        return maxDepth;
    }
}

/*
class Solution {
    //深度遍历
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxL = maxDepth(root.left);
        int maxR = maxDepth(root.right);
        return Math.max(maxL, maxR) + 1;
    }
}
*/

/*
public class Solution_104 {
    //广度遍历
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        int size = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
*/

/*
class Solution {
    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;

    // 主函数
    //回溯法
    public int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }
        traverse(root.left);
        traverse(root.right);
        // 后序位置
        depth--;
    }
}
*/
