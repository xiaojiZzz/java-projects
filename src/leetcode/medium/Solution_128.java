package leetcode.medium;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class Solution_128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int ans = 0;
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int times = 0;
            while (set.contains(num)) {
                times++;
                num += 1;
            }
            ans = Math.max(ans, times);
        }
        return ans;
    }
}

/*
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        Arrays.sort(nums);
        int ans = 0;
        int idx = 1;
        int sum = 1;
        while (idx < nums.length) {
            if (nums[idx - 1] + 1 == nums[idx]) {
                sum++;
            } else if (nums[idx - 1] == nums[idx]) {
                idx++;
                continue;
            } else {
                ans = Math.max(ans, sum);
                sum = 1;
            }
            idx++;
        }
        ans = Math.max(ans, sum);
        return ans;
    }
}
*/

/*
class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int n : nums) {
            if (map.containsKey(n)) continue;

            int left = map.containsKey(n - 1) ? map.get(n - 1) : 0;
            int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;

            int len = left + right + 1;
            // 只用来标记 n 已被使用
            map.put(n, 1);
            map.put(n - left, len);
            map.put(n + right, len);

            result = Math.max(result, len);
        }
        return result;
    }
}
*/

/*
class UnionFind {
    // 记录每个节点的父节点
    private Map<Integer, Integer> parent;

    public UnionFind(int[] nums) {
        parent = new HashMap<>();
        // 初始化父节点为自身
        for (int num : nums) {
            parent.put(num, num);
        }
    }

    // 寻找x的父节点，实际上也就是x的最远连续右边界，这点类似于方法2
    public Integer find(int x) {
        // nums不包含x
        if (!parent.containsKey(x)) {
            return null;
        }
        // 遍历找到x的父节点
        while (x != parent.get(x)) {
            // 进行路径压缩，不写下面这行也可以，但是时间会慢些
            parent.put(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    // 合并两个连通分量，在本题中只用来将num并入到num+1的连续区间中
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        parent.put(rootX, rootY);
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind(nums);
        int ans = 0;

        for (int num : nums) {
            // 当num+1存在，将num合并到num+1所在集合中
            if (uf.find(num + 1) != null) {
                uf.union(num, num + 1);
            }
        }

        for (int num : nums) {
            // 找到num的最远连续右边界
            int right = uf.find(num);
            ans = Math.max(ans, right - num + 1);
        }
        return ans;
    }
}
*/
