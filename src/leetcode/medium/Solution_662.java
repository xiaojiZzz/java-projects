package leetcode.medium;

import java.util.*;


/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 * 示例 1：
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 * 示例 2：
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 * 示例 3：
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 */
public class Solution_662 {

    private int ans = 0;
    private List<Integer> list = new ArrayList<>();

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth, int idx) {
        if (node == null) {
            return;
        }
        if (list.size() == depth) {
            list.add(idx);
        }
        ans = Math.max(ans, idx - list.get(depth) + 1);
        dfs(node.left, depth + 1, idx * 2);
        dfs(node.right, depth + 1, idx * 2 + 1);
    }
}

/*
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int max = 0;
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int begin = -1, end = -1;
            for (int i = 0; i < size; i++) {
                TreeNode out = queue.poll();
                int index = map.get(out);
                if (i == 0) {
                    begin = index;
                }
                if (i == size - 1) {
                    end = index;
                }
                if (out.left != null) {
                    queue.offer(out.left);
                    map.put(out.left, index * 2);
                }
                if (out.right != null) {
                    queue.offer(out.right);
                    map.put(out.right, index * 2 + 1);
                }
            }
            max = Math.max(max, end - begin + 1);
        }
        return max;
    }
}
*/
