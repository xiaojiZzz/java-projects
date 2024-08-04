package leetcode.simple;


/**
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 */
public class Solution_572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return is(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean is(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return is(node1.left, node2.left) && is(node1.right, node2.right);
    }
}

/*
class Solution {
    List<Integer> sOrder = new ArrayList<Integer>();
    List<Integer> tOrder = new ArrayList<Integer>();
    int maxElement, lNull, rNull;

    public boolean isSubtree(TreeNode s, TreeNode t) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);

        return kmp();
    }

    public void getMaxElement(TreeNode t) {
        if (t == null) {
            return;
        }
        maxElement = Math.max(maxElement, t.val);
        getMaxElement(t.left);
        getMaxElement(t.right);
    }

    public void getDfsOrder(TreeNode t, List<Integer> tar) {
        if (t == null) {
            return;
        }
        tar.add(t.val);
        if (t.left != null) {
            getDfsOrder(t.left, tar);
        } else {
            tar.add(lNull);
        }
        if (t.right != null) {
            getDfsOrder(t.right, tar);
        } else {
            tar.add(rNull);
        }
    }

    public boolean kmp() {
        int sLen = sOrder.size(), tLen = tOrder.size();
        int[] fail = new int[tOrder.size()];
        Arrays.fill(fail, -1);
        for (int i = 1, j = -1; i < tLen; ++i) {
            while (j != -1 && !(tOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (tOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            fail[i] = j;
        }
        for (int i = 0, j = -1; i < sLen; ++i) {
            while (j != -1 && !(sOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (sOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            if (j == tLen - 1) {
                return true;
            }
        }
        return false;
    }
}
*/

/*
class Solution {
    // 时间复杂度 O(n)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        int hs = getHeight(subRoot);
        return dfs(root, subRoot, hs).getValue();
    }

    // 代码逻辑同 104. 二叉树的最大深度
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        return Math.max(leftH, rightH) + 1;
    }

    // 100. 相同的树
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q; // 必须都是 null
        }
        return p.val == q.val &&
               isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }

    // 返回 node 的高度，以及是否找到了 subRoot
    private Pair<Integer, Boolean> dfs(TreeNode node, TreeNode subRoot, int hs) {
        if (node == null) {
            return new Pair<>(0, false);
        }
        Pair<Integer, Boolean> left = dfs(node.left, subRoot, hs);
        Pair<Integer, Boolean> right = dfs(node.right, subRoot, hs);
        if (left.getValue() || right.getValue()) {
            return new Pair<>(0, true);
        }
        int nodeH = Math.max(left.getKey(), right.getKey()) + 1;
        return new Pair<>(nodeH, nodeH == hs && isSameTree(node, subRoot));
    }
}
*/

/*
class Solution {

    // 时间复杂度 O(n)
    private int subRootDepth = -1;
    private boolean rootContainsSubRoot = false;

    // 判断 a b 两棵树是否相等
    private boolean equals(TreeNode a, TreeNode b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        return a.val == b.val && equals(a.left, b.left) && equals(a.right, b.right);
    }

    // 计算树的深度
    private int getDepth(TreeNode node, TreeNode subRoot) {
        if(rootContainsSubRoot || node == null) return 0;
        int depth = Math.max(getDepth(node.left, subRoot), getDepth(node.right, subRoot)) + 1;

        // subRootDepth 的初值为 -1，depth >= 0
        // 当 depth == subRootDepth 时，说明如果 subRoot 的深度已经计算得出
        // 再看 node 和 subRoot 两棵树是否相等
        rootContainsSubRoot |= depth == subRootDepth && equals(node, subRoot);

        return depth;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        subRootDepth = getDepth(subRoot, subRoot);
        getDepth(root, subRoot);
        return rootContainsSubRoot;
    }
}
*/