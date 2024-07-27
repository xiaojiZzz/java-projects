package leetcode.difficulty;

import java.util.*;


/**
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个
 * 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * 实现 Solution 类:
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 * 示例 1：
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 *                  // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 */
public class Solution_710 {
    class Solution {
        Map<Integer, Integer> b2w;
        Random random;
        int bound;

        public Solution(int n, int[] blacklist) {
            b2w = new HashMap<Integer, Integer>();
            random = new Random();
            int m = blacklist.length;
            bound = n - m;
            Set<Integer> black = new HashSet<Integer>();
            for (int b : blacklist) {
                if (b >= bound) {
                    black.add(b);
                }
            }

            int w = bound;
            for (int b : blacklist) {
                if (b < bound) {
                    while (black.contains(w)) {
                        ++w;
                    }
                    b2w.put(b, w);
                    ++w;
                }
            }
        }

        public int pick() {
            int x = random.nextInt(bound);
            return b2w.getOrDefault(x, x);
        }
    }
}

/*
class Solution {
    List<int[]> list = new ArrayList<>();
    int[] sum = new int[100010];
    int sz;
    Random random = new Random();

    public Solution(int n, int[] bs) {
        Arrays.sort(bs);
        int m = bs.length;
        if (m == 0) {
            list.add(new int[]{0, n - 1});
        } else {
            if (bs[0] != 0) list.add(new int[]{0, bs[0] - 1});
            for (int i = 1; i < m; i++) {
                if (bs[i - 1] == bs[i] - 1) continue;
                list.add(new int[]{bs[i - 1] + 1, bs[i] - 1});
            }
            if (bs[m - 1] != n - 1) list.add(new int[]{bs[m - 1] + 1, n - 1});
        }
        sz = list.size();
        for (int i = 1; i <= sz; i++) {
            int[] info = list.get(i - 1);
            sum[i] = sum[i - 1] + info[1] - info[0] + 1;
        }
    }

    public int pick() {
        int val = random.nextInt(sum[sz]) + 1;
        int l = 1, r = sz;
        while (l < r) {
            int mid = l + r >> 1;
            if (sum[mid] >= val) r = mid;
            else l = mid + 1;
        }
        int[] info = list.get(r - 1);
        int a = info[0], b = info[1], end = sum[r];
        return b - (end - val);
    }
}
*/
