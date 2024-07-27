package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;


/**
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
 * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。在第一次操作中，将 nums[1] 追加到 arr1 。
 * 在第二次操作中，将 nums[2] 追加到 arr2 。之后，在第 i 次操作中：
 * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
 * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
 * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
 * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
 * 连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
 * 返回整数数组 result 。
 * 示例 1：
 * 输入：nums = [2,1,3,3]
 * 输出：[2,3,1,3]
 * 解释：在前两次操作后，arr1 = [2] ，arr2 = [1] 。
 * 在第 3 次操作中，两个数组中大于 3 的元素数量都是零，并且长度相等，因此，将 nums[3] 追加到 arr1 。
 * 在第 4 次操作中，两个数组中大于 3 的元素数量都是零，但 arr2 的长度较小，因此，将 nums[4] 追加到 arr2 。
 * 在 4 次操作后，arr1 = [2,3] ，arr2 = [1,3] 。
 * 因此，连接形成的数组 result 是 [2,3,1,3] 。
 * 示例 2：
 * 输入：nums = [5,14,3,1,2]
 * 输出：[5,3,1,2,14]
 * 解释：在前两次操作后，arr1 = [5] ，arr2 = [14] 。
 * 在第 3 次操作中，两个数组中大于 3 的元素数量都是一，并且长度相等，因此，将 nums[3] 追加到 arr1 。
 * 在第 4 次操作中，arr1 中大于 1 的元素数量大于 arr2 中的数量（2 > 1），因此，将 nums[4] 追加到 arr1 。
 * 在第 5 次操作中，arr1 中大于 2 的元素数量大于 arr2 中的数量（2 > 1），因此，将 nums[5] 追加到 arr1 。
 * 在 5 次操作后，arr1 = [5,3,1,2] ，arr2 = [14] 。
 * 因此，连接形成的数组 result 是 [5,3,1,2,14] 。
 * 示例 3：
 * 输入：nums = [3,3,3,3]
 * 输出：[3,3,3,3]
 * 解释：在 4 次操作后，arr1 = [3,3] ，arr2 = [3,3] 。
 * 因此，连接形成的数组 result 是 [3,3,3,3] 。
 */
public class Solution_3072 {

    class BinaryIndexedTree {
        // 树状数组 从 1 开始记录
        private int[] tree;

        public BinaryIndexedTree(int n) {
            this.tree = new int[n];
        }

        public int lowBit(int x) {
            return x & -x;
        }

        public void add(int x) {
            while (x < tree.length) {
                tree[x] += 1;
                x += lowBit(x);
            }
        }

        public int prefixSum(int x) {
            int sum = 0;
            while (x > 0) {
                sum += tree[x];
                x -= lowBit(x);
            }
            return sum;
        }
    }

    public int[] resultArray(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(clone);
        int n = nums.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(nums[0]);
        list2.add(nums[1]);
        BinaryIndexedTree tree1 = new BinaryIndexedTree(n + 1);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(n + 1);
        tree1.add(Arrays.binarySearch(clone, nums[0]) + 1);
        tree2.add(Arrays.binarySearch(clone, nums[1]) + 1);
        for (int i = 2; i < n; i++) {
            int num = nums[i];
            int t = Arrays.binarySearch(clone, num) + 1;
            int left = list1.size() - tree1.prefixSum(t);
            int right = list2.size() - tree2.prefixSum(t);
            if (left > right || left == right && list1.size() <= list2.size()) {
                list1.add(num);
                tree1.add(t);
            } else {
                list2.add(num);
                tree2.add(t);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < list1.size(); i++) {
            ans[i] = list1.get(i);
        }
        for (int i = list1.size(); i < n; i++) {
            ans[i] = list2.get(i - list1.size());
        }
        TreeSet<Object> objects = new TreeSet<>();
        return ans;
    }
}
