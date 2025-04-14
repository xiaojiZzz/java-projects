package leetcode.simple;

/**
 * 统计好三元组
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * 其中 |x| 表示 x 的绝对值。
 * 返回 好三元组的数量 。
 * 示例 1：
 * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * 输出：4
 * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
 * 示例 2：
 * 输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * 输出：0
 * 解释：不存在满足所有条件的三元组。
 * 提示：
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */
public class Solution_1534 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < n; ++k) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            ++cnt;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}

/*
class Solution {
    // 优化
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0, n = arr.length;
        int[] sum = new int[1001];
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1 ; k < n; ++k) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int lj = arr[j] - a, rj = arr[j] + a;
                    int lk = arr[k] - c, rk = arr[k] + c;
                    int l = Math.max(0, Math.max(lj, lk)), r = Math.min(1000, Math.min(rj, rk));
                    if (l <= r) {
                        if (l == 0) {
                            ans += sum[r];
                        } else {
                            ans += sum[r] - sum[l - 1];
                        }
                    }
                }
            }
            for (int k = arr[j]; k <= 1000; ++k) {
                ++sum[k];
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    // 树状数组
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, max = Arrays.stream(arr).max().getAsInt();
        int[] sum = new int[max + 2];
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(sum);
        int ans = 0;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int lj = arr[j] - a, rj = arr[j] + a;
                    int lk = arr[k] - c, rk = arr[k] + c;
                    int l = Math.max(0, Math.max(lj, lk)), r = Math.min(max, Math.min(rj, rk));
                    if (l <= r) {
                        ans += binaryIndexedTree.prefixSum(r + 1) - binaryIndexedTree.prefixSum(l);
                    }
                }
            }
            binaryIndexedTree.add(arr[j] + 1, 1);
        }
        return ans;
    }

    static class BinaryIndexedTree {
        private int n;
        // 原数组
        private int[] nums;
        // 树状数组 从 1 开始记录
        private int[] tree;

        public BinaryIndexedTree(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            this.tree = new int[n + 1];
        }

        public int lowBit(int x) {
            return x & -x;
        }

        public void add(int x, int val) {
            while (x <= n) {
                tree[x] += val;
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
}
*/

/*
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        Integer[] idx = new Integer[arr.length];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);

        int ans = 0;
        for (int j : idx) {
            int y = arr[j];
            List<Integer> left = new ArrayList<>();
            for (int i : idx) {
                if (i < j && Math.abs(arr[i] - y) <= a) {
                    left.add(arr[i]);
                }
            }

            List<Integer> right = new ArrayList<>();
            for (int k : idx) {
                if (k > j && Math.abs(arr[k] - y) <= b) {
                    right.add(arr[k]);
                }
            }

            int k1 = 0;
            int k2 = 0;
            for (int x : left) {
                while (k2 < right.size() && right.get(k2) <= x + c) {
                    k2++;
                }
                while (k1 < right.size() && right.get(k1) < x - c) {
                    k1++;
                }
                ans += k2 - k1;
            }
        }
        return ans;
    }
}
*/