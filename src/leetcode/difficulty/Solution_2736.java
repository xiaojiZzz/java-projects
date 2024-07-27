package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你两个长度为 n 、下标从 0 开始的整数数组 nums1 和 nums2 ，另给一下标从 1 开始的二维数组 queries ，其中 queries[i] = [xi, yi] 。
 * 对于第 i 个查询，在所有满足 nums1[j] >= xi 且 nums2[j] >= yi 的下标 j (0 <= j < n) 中，找出 nums1[j] + nums2[j] 的 最大值 ，
 * 如果不存在满足条件的 j 则返回 -1 。
 * 返回数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * 示例 1：
 * 输入：nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
 * 输出：[6,10,7]
 * 解释：
 * 对于第 1 个查询：xi = 4 且 yi = 1 ，可以选择下标 j = 0 ，此时 nums1[j] >= 4 且 nums2[j] >= 1 。nums1[j] + nums2[j] 等于 6 ，
 * 可以证明 6 是可以获得的最大值。
 * 对于第 2 个查询：xi = 1 且 yi = 3 ，可以选择下标 j = 2 ，此时 nums1[j] >= 1 且 nums2[j] >= 3 。nums1[j] + nums2[j] 等于 10 ，
 * 可以证明 10 是可以获得的最大值。
 * 对于第 3 个查询：xi = 2 且 yi = 5 ，可以选择下标 j = 3 ，此时 nums1[j] >= 2 且 nums2[j] >= 5 。nums1[j] + nums2[j] 等于 7 ，
 * 可以证明 7 是可以获得的最大值。
 * 因此，我们返回 [6,10,7] 。
 * 示例 2：
 * 输入：nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
 * 输出：[9,9,9]
 * 解释：对于这个示例，我们可以选择下标 j = 2 ，该下标可以满足每个查询的限制。
 * 示例 3：
 * 输入：nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
 * 输出：[-1]
 * 解释：示例中的查询 xi = 3 且 yi = 3 。对于每个下标 j ，都只满足 nums1[j] < xi 或者 nums2[j] < yi 。因此，不存在答案。
 */
public class Solution_2736 {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }
        Arrays.sort(a, (x, y) -> y[0] - x[0]);

        Integer[] qid = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            qid[i] = i;
        }
        Arrays.sort(qid, (i, j) -> queries[j][0] - queries[i][0]);

        int[] ans = new int[queries.length];
        List<int[]> st = new ArrayList<>();
        int j = 0;
        for (int i : qid) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && a[j][0] >= x; j++) { // 下面只需关心 a[j][1]
                while (!st.isEmpty() && st.get(st.size() - 1)[1] <= a[j][0] + a[j][1]) { // a[j][1] >= st.get(st.size()-1)[0]
                    st.remove(st.size() - 1);
                }
                if (st.isEmpty() || st.get(st.size() - 1)[0] < a[j][1]) {
                    st.add(new int[]{a[j][1], a[j][0] + a[j][1]});
                }
            }
            int p = lowerBound(st, y);
            ans[i] = p < st.size() ? st.get(p)[1] : -1;
        }
        return ans;
    }

    // 开区间写法，原理请看 b23.tv/AhwfbS2
    private int lowerBound(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) >>> 1;
            if (st.get(mid)[0] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }
}

/*
public class Solution_2736 {
    //超时
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int len = queries.length, l = nums1.length;
        int[] ans = new int[len];
        Integer[] indexs = IntStream.range(0, l).boxed().toArray(Integer[]::new);
        Arrays.sort(indexs, (a, b) -> {
            if (nums1[a] == nums1[b]) {
                return nums2[b] - nums2[a];
            } else {
                return nums1[b] - nums1[a];
            }
        });
        int idx = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1];
            for (int i = 0; i < l; i++) {
                if (nums1[indexs[i]] >= x) {
                    if (nums2[indexs[i]] >= y) {
                        ans[idx] = Math.max(ans[idx], nums1[indexs[i]] + nums2[indexs[i]]);
                    } else {
                        if (i == l - 1 && ans[idx] == 0) {
                            ans[idx] = -1;
                            break;
                        }
                        continue;
                    }
                } else {
                    if (ans[idx] == 0) {
                        ans[idx] = -1;
                    }
                    break;
                }
            }
            idx++;
        }
        return ans;
    }
}
*/

/*
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        Arrays.fill(c, -1);
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = -1;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] nums = new int[n][0];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1[i], nums2[i]};
        }
        Arrays.sort(nums, (a, b) -> b[0] - a[0]);
        Arrays.sort(nums2);
        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][0] - queries[i][0]);
        int[] ans = new int[m];
        int j = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i : idx) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && nums[j][0] >= x; ++j) {
                int k = n - Arrays.binarySearch(nums2, nums[j][1]);
                tree.update(k, nums[j][0] + nums[j][1]);
            }
            int p = Arrays.binarySearch(nums2, y);
            int k = p >= 0 ? n - p : n + p + 1;
            ans[i] = tree.query(k);
        }
        return ans;
    }
}
*/
