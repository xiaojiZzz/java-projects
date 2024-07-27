package leetcode.simple;

import java.util.ArrayList;
import java.util.List;


/**
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 * 输入：root = [1]
 * 输出：["1"]
 */
public class Solution_257 {

    List<String> ans;

    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList<>();
        getString(root, "");
        return ans;
    }

    public void getString(TreeNode root, String s) {
        if (root == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.append(root.val + "->");
        if (root.left == null && root.right == null) {
            ans.add(stringBuilder.substring(0, stringBuilder.length() - 2));
            return;
        }
        getString(root.left, stringBuilder.toString());
        getString(root.right, stringBuilder.toString());
    }
}
