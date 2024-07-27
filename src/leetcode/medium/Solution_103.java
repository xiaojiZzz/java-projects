package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 */
public class Solution_103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (lists.size() % 2 == 0) {
                lists.add(list);
            } else {
                ArrayList<Integer> list_re = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    list_re.add(list.get(list.size() - 1 - i));
                }
                lists.add(list_re);
            }
        }
        return lists;
    }
}

/*
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}
*/

/*
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        travel(root, res, 0);
        return res;
    }

    private void travel(TreeNode cur, List<List<Integer>> res, int level) {
        if (cur == null)
            return;
        //如果res.size() <= level说明下一层的集合还没创建，所以要先创建下一层的集合
        if (res.size() = level) {
            List<Integer> newLevel = new LinkedList<>();
            res.add(newLevel);
        }
        //遍历到第几层我们就操作第几层的数据
        List<Integer> list = res.get(level);
        //这里默认根节点是第0层，偶数层相当于从左往右遍历，
        // 所以要添加到集合的末尾，如果是奇数层相当于从右往左遍历，
        // 要把数据添加到集合的开头
        if (level % 2 == 0)
            list.add(cur.val);
        else
            list.add(0, cur.val);
        //分别遍历左右两个子节点，到下一层了，所以层数要加1
        travel(cur.left, res, level + 1);
        travel(cur.right, res, level + 1);
    }
}
*/
