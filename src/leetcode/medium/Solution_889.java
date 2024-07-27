package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中 任何 一个。
 * 示例 1：
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 */
public class Solution_889 {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        return index(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode index(int[] preorder, int[] postorder, int pre_left, int pre_right, int post_left, int post_right) {
        if (pre_left > pre_right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre_left]);
        if (pre_left == pre_right) {
            return root;
        }
        int index = map.get(preorder[pre_left + 1]);
        int left_size = index - post_left + 1;
        root.left = index(preorder, postorder, pre_left + 1, pre_left + left_size, post_left, left_size);
        root.right = index(preorder, postorder, pre_left + left_size + 1, pre_right, index + 1, post_right);
        return root;
    }
}

/*
class Solution {
    int preIndex = 0, posIndex = 0;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }
}
*/
