package leetcode.medium;

import java.util.*;


/**
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，
 * 以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。
 * 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 */
public class Solution_449 {
    public class Codec {
        // 后续遍历 + BFS
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<Integer>();
            postOrder(root, list);
            String str = list.toString();
            return str.substring(1, str.length() - 1);
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] arr = data.split(", ");
            Deque<Integer> stack = new ArrayDeque<Integer>();
            int length = arr.length;
            for (int i = 0; i < length; i++) {
                stack.push(Integer.parseInt(arr[i]));
            }
            return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
        }

        private void postOrder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }

        private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
            if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
                return null;
            }
            int val = stack.pop();
            TreeNode root = new TreeNode(val);
            root.right = construct(val, upper, stack);
            root.left = construct(lower, val, stack);
            return root;
        }
    }
}

/*
class Codec {
    // 前序遍历 + BFS
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preOrder(root, list);
        String str = list.toString();
        return str.substring(1, str.length() - 1);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(", ");
        Queue<Integer> queue = new ArrayDeque<Integer>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            queue.offer(Integer.parseInt(arr[i]));
        }
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    private TreeNode construct(int lower, int upper, Queue<Integer> queue) {
        if (queue.isEmpty() || queue.peek() < lower || queue.peek() > upper) {
            return null;
        }
        int val = queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = construct(lower, val, queue);
        root.right = construct(val, upper, queue);
        return root;
    }
}
*/
