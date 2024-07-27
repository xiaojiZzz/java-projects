package leetcode.simple;

import java.util.*;


/**
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * 示例 1：
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：root = [0]
 * 输出：[0]
 */
public class Solution_501 {

    Map<Integer, Integer> map = new HashMap<>();

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[][] size = new int[map.size()][2];
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            int key = entry.getKey();
            int val = entry.getValue();
            size[idx][0] = key;
            size[idx][1] = val;
            idx++;
        }
        Arrays.sort(size, (a, b) -> b[1] - a[1]);
        List<Integer> list = new ArrayList<>();
        list.add(size[0][0]);
        for (int i = 1; i < size.length; i++) {
            if (size[i][1] == size[i - 1][1]) {
                list.add(size[i][0]);
            } else {
                break;
            }
        }
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        return ints;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        int val = node.val;
        map.put(val, map.getOrDefault(val, 0) + 1);
        dfs(node.left);
        dfs(node.right);
    }
}

/*
class Solution {
    List<Integer> answer = new ArrayList<Integer>();
    int base, count, maxCount;

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode o) {
        if (o == null) {
            return;
        }
        dfs(o.left);
        update(o.val);
        dfs(o.right);
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }
}
*/
