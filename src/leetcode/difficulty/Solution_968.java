package leetcode.difficulty;

/**
 * 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 */
public class Solution_968 {
    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[2]);
    }

    // int[] -> int[3]
    // int[0]: 该节点有摄像头
    // int[1]: 该节点无摄像头，但是父节点有摄像头
    // int[2]: 该节点无摄像头，但是至少有一个子节点有摄像头
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int[] next = new int[3];
        next[0] = 1 + Math.min(Math.min(left[0], left[1]), left[2]) + Math.min(Math.min(right[0], right[1]), right[2]);
        next[1] = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        next[2] = Math.min(left[0] + right[0], Math.min(left[0] + right[2], left[2] + right[0]));
        return next;
    }
}

/*
class Solution {
    static int ans;

    public int minCameraCover(TreeNode root) {
        ans = 0; // 初始化
        if (f(root) == 0) ans++;
        return ans;
    }

    // 定义 f 函数有三种返回值情况
    // 0：表示 x 节点没有被相机监控，只能依靠父节点放相机
    // 1：表示 x 节点被相机监控，但相机不是放在自身节点上
    // 2：表示 x 节点被相机监控，但相机放在自身节点上
    public static int f(TreeNode x) {
        if (x == null) return 1; // 空树认为被监控，但没有相机
        // 左右递归到最深处
        int l = f(x.left);
        int r = f(x.right);
        // 有任意一个子节点为空，就需要当前节点放相机，不然以后没机会
        if (l == 0 || r == 0) {
            ans++; // 放相机
            return 2;
        }
        // 贪心策略，左右子树都被监控，且没有监控到当前节点，
        // 那么最有利的情况就是将相机放置在当前节点父节点上，
        // 因为这样能多监控可能的子树节点和父父节点
        if (l == 1 && r == 1) return 0;
        // 剩下情况就是左右子树有可能为 2，即当前节点被监控
        return 1;
    }
}
*/
