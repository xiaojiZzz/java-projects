package leetcode.lcr;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 同leetcode 297.二叉树的序列化与反序列化
 */
public class Solution_048 {
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

