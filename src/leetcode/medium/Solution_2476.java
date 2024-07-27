package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 返回数组 answer 。
 * 示例 1 ：
 * 输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * 输出：[[2,2],[4,6],[15,-1]]
 * 解释：按下面的描述找出并返回查询的答案：
 * - 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
 * - 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
 * - 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。
 * 示例 2 ：
 * 输入：root = [4,null,9], queries = [3]
 * 输出：[[-1,4]]
 * 解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 */
public class Solution_2476 {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        inOrder(root);
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        for (Integer query : queries) {
            List<Integer> list = new ArrayList<>();
            int idx = binSearch(ints, query);
            if (idx < ints.length && ints[idx] == query) {
                list.add(query);
                list.add(query);
            } else {
                if (ints.length == 1) {
                    list.add(-1);
                    list.add(-1);
                } else if (idx == 0) {
                    list.add(-1);
                    list.add(ints[0]);
                } else if (idx == ints.length) {
                    list.add(ints[ints.length - 1]);
                    list.add(-1);
                } else {
                    list.add(ints[idx - 1]);
                    list.add(ints[idx]);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    private void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left);
        list.add(treeNode.val);
        inOrder(treeNode.right);
    }

    private int binSearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
