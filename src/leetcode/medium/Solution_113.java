package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 */
public class Solution_113 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        getList(root, new ArrayList<>(), targetSum);
        return ans;
    }

    public void getList(TreeNode node, List<Integer> list, int targetSum) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && node.val == targetSum) {
            list.add(node.val);
            ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(node.val);
        getList(node.left, list, targetSum - node.val);
        getList(node.right, list, targetSum - node.val);
        list.remove(list.size() - 1);
    }
}
