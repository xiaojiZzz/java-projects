package leetcode.medium;

import java.util.*;


/**
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
 * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
 * 请你返回修改值之后，树的根 root 。
 * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
 * 示例 1：
 * 输入：root = [5,4,9,1,10,null,7]
 * 输出：[0,0,0,7,7,null,11]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
 * 示例 2：
 * 输入：root = [3,1,2]
 * 输出：[0,0,0]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
 */
public class Solution_2641 {
    public TreeNode replaceValueInTree(TreeNode root) {
        // 堂兄弟节点 -> 深度相同父节点不同的节点
        // 根节点无堂兄弟节点 -> root.val = 0
        root.val = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            // 遍历每一层(深度相同)的节点并将对于的val加起来，最后要交换的值-> sum - x - y
            for (TreeNode node : queue) {
                if (node.left != null) {
                    sum += node.left.val;
                }
                if (node.right != null) {
                    sum += node.right.val;
                }
            }
            // 遍历第二遍 -> 将不是堂兄弟节点的val减去
            while (size-- > 0) {
                // 第一遍用的for遍历并没有将队列改变，所以可以复用queue
                TreeNode node = queue.poll();
                // 将父节点下的两个非堂兄弟节点的值记录
                int sumOfxy = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0 : node.right.val);
                // 若该父节点node下的子节点存在则将其更改val并将子节点入队
                if (node.left != null) {
                    node.left.val = sum - sumOfxy;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = sum - sumOfxy;
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}

/*
class Solution {
    private List<Integer> s = new ArrayList<>();

    public TreeNode replaceValueInTree(TreeNode root) {
        dfs1(root, 0);
        root.val = 0;
        dfs2(root, 0);
        return root;
    }

    private void dfs1(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (s.size() <= depth) {
            s.add(0);
        }
        s.set(depth, s.get(depth) + root.val);
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    }

    private void dfs2(TreeNode root, int depth) {
        int l = root.left == null ? 0 : root.left.val;
        int r = root.right == null ? 0 : root.right.val;
        int sub = l + r;
        ++depth;
        if (root.left != null) {
            root.left.val = s.get(depth) - sub;
            dfs2(root.left, depth);
        }
        if (root.right != null) {
            root.right.val = s.get(depth) - sub;
            dfs2(root.right, depth);
        }
    }
}
*/
