package leetcode.medium;


/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 */
public class Solution_230 {
    private int ans = 0;
    private int idx = 0;

    public int kthSmallest(TreeNode root, int k) {
        idx = k;
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode node) {
        if (node == null || idx == 0) {
            return;
        }
        inOrder(node.left);
        idx--;
        if (idx == 0) {
            ans = node.val;
        }
        inOrder(node.right);
    }
}

/*
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }
}

class MyBst {
    TreeNode root;
    Map<TreeNode, Integer> nodeNum;

    public MyBst(TreeNode root) {
        this.root = root;
        this.nodeNum = new HashMap<TreeNode, Integer>();
        countNodeNum(root);
    }

    // 返回二叉搜索树中第k小的元素
    public int kthSmallest(int k) {
        TreeNode node = root;
        while (node != null) {
            int left = getNodeNum(node.left);
            if (left < k - 1) {
                node = node.right;
                k -= left + 1;
            } else if (left == k - 1) {
                break;
            } else {
                node = node.left;
            }
        }
        return node.val;
    }

    // 统计以node为根结点的子树的结点数
    private int countNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
        return nodeNum.get(node);
    }

    // 获取以node为根结点的子树的结点数
    private int getNodeNum(TreeNode node) {
        return nodeNum.getOrDefault(node, 0);
    }
}
*/
