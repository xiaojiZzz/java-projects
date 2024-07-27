package leetcode.difficulty;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 */
public class Solution_297 {
    public class Codec {
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("None")) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);

            return root;
        }
    }
}

/*
class Solution {
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder res = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    res.append("" + node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    res.append("null");
                }
                res.append(",");
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == "") {
                return null;
            }
            String[] dataList = data.substring(0, data.length()).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!"null".equals(dataList[i])) {
                    node.left = new TreeNode(Integer.parseInt(dataList[i]));
                    queue.offer(node.left);
                }
                i++;
                if (!"null".equals(dataList[i])) {
                    node.right = new TreeNode(Integer.parseInt(dataList[i]));
                    queue.offer(node.right);
                }
                i++;
            }
            return root;
        }
    }
}
*/

/*
class Solution {
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "null";
            }
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return dfs(queue);
        }

        private TreeNode dfs(Queue<String> queue) {
            String val = queue.poll();
            if ("null".equals(val)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = dfs(queue);
            root.right = dfs(queue);
            return root;
        }
    }
}
*/
