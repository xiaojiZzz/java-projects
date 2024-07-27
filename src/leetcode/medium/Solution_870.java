package leetcode.medium;

import java.util.*;


/**
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 * 示例 1：
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 */
public class Solution_870 {
    //类比田忌赛马，数组中最小值即下等马，最大值上等马
    //每次用nums1中的下等马去跟nums2中的下等马pk
    //如果干的过就干，干不过就用nums1中的下等马去当炮灰，去干nums2中的上等马
    //在本题中，如果干的过，就用nums2中的下等马的下标当做nums1中的下等马的下标
    //干不过，就用nums2中的上等马的下标当作nums1中的下等马的下标
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int len = nums2.length;
        Integer[] index = new Integer[len];
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
        }
        //注意比较器引用的数组需要是对象，所以不能放基本数据类型的数组
        //根据nums2的值升序，来排序nums2的值的对应下标
        //排序后index[]中第一个元素是nums2中最小值的下标，第二个元素是nums2中第二小值的下标
        Arrays.sort(index, (a, b) -> (nums2[a] - nums2[b]));
        int left = 0;
        int right = len - 1;
        //遍历nums1
        for (int num : nums1) {
            //index[left] 为 nums2中最小值的下标，index[right] 为nums2中最大值的下标
            //如果num比nums2中的最小值大，则本次res中num对应的下标为index[left],然后left++
            //否则本次res中num对应的下标为index[right],然后right--
            int i = num > nums2[index[left]] ? index[left++] : index[right--];
            res[i] = num;
        }
        return res;
    }
}

/*
class Solution {
    方法同上，只是把nums1也索引排序了
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx1[i] = i;
            idx2[i] = i;
        }
        Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);

        int[] ans = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; ++i) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                ans[idx2[left]] = nums1[idx1[i]];
                ++left;
            } else {
                ans[idx2[right]] = nums1[idx1[i]];
                --right;
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<Integer> tset = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) == 1) tset.add(x);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Integer cur = tset.ceiling(nums2[i] + 1);
            if (cur == null) cur = tset.ceiling(-1);
            ans[i] = cur;
            map.put(cur, map.get(cur) - 1);
            if (map.get(cur) == 0) tset.remove(cur);
        }
        return ans;
    }
}
*/

/*
class Solution {
    //利用哈希表来保持索引和值
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums2[i], new ArrayList<>());
            list.add(i);
            map.put(nums2[i], list);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] ans = new int[n];
        for (int l1 = 0, l2 = 0, r2 = n - 1; l1 < n; l1++) {
            int t = nums1[l1] > nums2[l2] ? l2 : r2;
            List<Integer> list = map.get(nums2[t]);
            int idx = list.remove(list.size() - 1);
            ans[idx] = nums1[l1];
            if (t == l2) l2++;
            else r2--;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 给 nums2 降序排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (pair1, pair2) -> (pair2[1] - pair1[1])

        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        // 给 nums1 升序排序
        Arrays.sort(nums1);

        // nums1[left] 是最小值，nums1[right] 是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            //maxval 是 nums2 中的最大值，i 是对应索引
            int i = pair[0], maxval = pair[1];
            if (maxval < nums1[right]) {
                // 如果 nums1[right] 能胜过 maxval，那就自己上
                res[i] = nums1[right];
                right--;
            } else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
*/
