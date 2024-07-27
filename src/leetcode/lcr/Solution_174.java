package leetcode.lcr;

import java.util.ArrayList;
import java.util.List;

/**
 * 寻找二叉搜索树中的目标节点
 * 某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
 * 示例 1：
 * 输入：root = [7, 3, 9, 1, 5], cnt = 2
 * 7
 * / \
 * 3   9
 * / \
 * 1   5
 * 输出：7
 * 示例 2：
 * 输入: root = [10, 5, 15, 2, 7, null, 20, 1, null, 6, 8], cnt = 4
 * 10
 * / \
 * 5   15
 * / \    \
 * 2   7    20
 * /   / \
 * 1   6   8
 * 输出: 8
 * 提示：
 * 1 ≤ cnt ≤ 二叉搜索树元素个数
 */
public class Solution_174 {

    List<Integer> list = new ArrayList<>();

    public int findTargetNode(TreeNode root, int cnt) {
        dfs(root);
        return list.get(list.size() - cnt);
    }

    private void dfs(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        dfs(treeNode.left);
        list.add(treeNode.val);
        dfs(treeNode.right);
    }
}

/*
class Solution {
    int res, cnt;

    public int findTargetNode(TreeNode root, int cnt) {
        this.cnt = cnt;
        dfs(root);
        return res;
    }

    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (cnt == 0) return;
        if (--cnt == 0) res = root.val;
        dfs(root.left);
    }
}
*/

/*
class Solution {
    int num = 0;

    public int findTargetNode(TreeNode root, int cnt) {
        if (root == null || num >= cnt) return 0;
        int right = findTargetNode(root.right, cnt);
        num++;
        if (num == cnt) {
            return root.val;
        }
        int left = findTargetNode(root.left, cnt);
        return right == 0 ? left : right;
    }
}
*/
