package leetcode.medium;

/**
 * 最深叶节点的最近公共祖先
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * 回想一下：
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 */
public class Solution_1123 {
    TreeNode ans = null;
    int maxDep = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public int dfs(TreeNode node, int depth) {
        if (node == null) {
            maxDep = Math.max(maxDep, depth - 1);
            return depth - 1;
        }
        int left_dep = dfs(node.left, depth + 1);
        int right_dep = dfs(node.right, depth + 1);
        if (left_dep == right_dep && left_dep == maxDep) {
            ans = node;
        }
        return Math.max(left_dep, right_dep);
    }
}

/*
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).getValue();
    }

    private Pair<Integer, TreeNode> dfs(TreeNode node) {
        if (node == null)
            return new Pair<>(0, null);
        var left = dfs(node.left);
        var right = dfs(node.right);
        if (left.getKey() > right.getKey()) // 左子树更高
            return new Pair<>(left.getKey() + 1, left.getValue());
        if (left.getKey() < right.getKey()) // 右子树更高
            return new Pair<>(right.getKey() + 1, right.getValue());
        return new Pair<>(left.getKey() + 1, node); // 一样高
    }
}
*/
