package leetcode.medium;

import java.util.*;


/**
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 * 示例 1：
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 */
public class Solution_654 {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode index(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        TreeNode root = new TreeNode(max);
        int index = map.get(max);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, index));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, index + 1, nums.length));
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return index(nums);
    }
}

/*
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(nums[l]);
        }
        int max = nums[l];
        int idx = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, l, idx - 1);
        root.right = build(nums, idx + 1, r);
        return root;
    }
}
*/

/*
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while(!deque.isEmpty()) {
                TreeNode topNode = deque.peekLast();
                if (topNode.val > node.val) {
                    deque.addLast(node);
                    topNode.right = node;
                    break;
                } else {
                    deque.removeLast(); // 出栈操作
                    node.left = topNode;
                }
            }
            if (deque.isEmpty()) deque.addLast(node);
        }
        return deque.peek();
    }
}
*/

/*
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while (!stack.isEmpty()) {
                TreeNode topNode = stack.peekFirst();
                if (topNode.val > node.val) {
                    stack.push(node);
                    topNode.right = node;
                    break;
                } else {
                    stack.pop(); // 出栈操作
                    node.left = topNode;
                }
            }
            if (stack.isEmpty()) stack.push(node);
        }
        return stack.peekLast();
    }
}
*/

/*
class Solution {
    // 利用数组实现
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode[] deque = new TreeNode[1001];
        int tail = 0;
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while(tail != 0) {
                TreeNode topNode = deque[tail - 1];
                if (topNode.val > node.val) {
                    deque[tail++] = node;
                    topNode.right = node;
                    break;
                } else {
                    deque[--tail] = null; // 出栈操作
                    node.left = topNode;
                }
            }
            if (tail == 0) deque[tail++] = node;
        }
        return deque[0];
    }
}
*/
