package leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 * 节点的 层数 等于该节点到根节点之间的边数。
 * 示例 1：
 * 输入：root = [2,3,5,8,13,21,34]
 * 输出：[2,5,3,8,13,21,34]
 * 解释：
 * 这棵树只有一个奇数层。
 * 在第 1 层的节点分别是 3、5 ，反转后为 5、3 。
 * 示例 2：
 * 输入：root = [7,13,11]
 * 输出：[7,11,13]
 * 解释：
 * 在第 1 层的节点分别是 13、11 ，反转后为 11、13 。
 * 示例 3：
 * 输入：root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * 输出：[0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * 解释：奇数层由非零值组成。
 * 在第 1 层的节点分别是 1、2 ，反转后为 2、1 。
 * 在第 3 层的节点分别是 1、1、1、1、2、2、2、2 ，反转后为 2、2、2、2、1、1、1、1 。
 */
public class Solution_2415 {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        boolean isOdd = false;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<TreeNode> arr = new ArrayList<TreeNode>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    arr.add(node);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            if (isOdd) {
                for (int l = 0, r = sz - 1; l < r; l++, r--) {
                    int temp = arr.get(l).val;
                    arr.get(l).val = arr.get(r).val;
                    arr.get(r).val = temp;
                }
            }
            isOdd ^= true;
        }
        return root;
    }
}

/*
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        // 从根节点的左右子节点开始递归，左右子节点是第一层，奇数次，需要翻转
        dfs(root.left, root.right, true);
        return root;
    }

    private void dfs(TreeNode node1, TreeNode node2, boolean isSwap){
        if(node1 == null)return;       // 空节点直接返回
        if(isSwap){
            // 需要交换值，交换
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
        isSwap = !isSwap;       // 因为只翻转奇数层，因此标志位交替变化
        dfs(node1.left, node2.right, isSwap);     // node1的左子节点和node2的右子节点是一对
        dfs(node1.right, node2.left, isSwap);     // node1的右子节点和node2的左子节点是一对
    }
}
*/

