package leetcode.medium;

import java.util.*;


/**
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，
 * 转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
 * 示例 1：
 * 输入：changed = [1,3,4,2,6,8]
 * 输出：[1,3,4]
 * 解释：一个可能的 original 数组为 [1,3,4] :
 * - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 * - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 * - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 * 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 * 示例 2：
 * 输入：changed = [6,3,0,1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * 示例 3：
 * 输入：changed = [1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 */
public class Solution_2007 {
    public int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        if (len % 2 != 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : changed) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(changed); // 关键一步
        for (int i : changed) {
            if (map.get(i) == 0) {
                continue;
            }
            map.put(i, map.get(i) - 1);
            if (map.containsKey(2 * i)) {
                if (map.get(2 * i) == 0) {
                    return new int[]{};
                } else {
                    list.add(i);
                    map.put(2 * i, map.get(2 * i) - 1);
                }
            } else {
                return new int[]{};
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

/*
class Solution {
    public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> cnt = new HashMap<>(changed.length);
        for (int x : changed) {
            cnt.merge(x, 1, Integer::sum);
        }

        // 单独处理 0
        int cnt0 = cnt.getOrDefault(0, 0);
        if (cnt0 % 2 == 1) {
            return new int[0];
        }
        cnt.remove(0);
        int[] ans = new int[changed.length / 2];
        int ansIdx = cnt0 / 2;

        Set<Integer> done = new HashSet<>(cnt.size());
        for (int x : cnt.keySet()) {
            // 如果 x 已处理完毕，或者 x/2 在 cnt 中，则跳过
            if (done.contains(x) || x % 2 == 0 && cnt.containsKey(x / 2)) {
                continue;
            }
            // 把 x, 2x, 4x, 8x, ... 全部配对
            while (cnt.containsKey(x)) {
                // 每次循环，把 cntX 个 x 和 cntX 个 2x 配对
                int cntX = cnt.get(x);
                int cnt2x = cnt.getOrDefault(x * 2, 0);
                // 无法配对，至少要有 cntX 个 2x
                if (cntX > cnt2x) {
                    return new int[0];
                }
                for (int i = 0; i < cntX; i++) {
                    ans[ansIdx++] = x;
                }
                // x 配对完成
                done.add(x);
                if (cntX < cnt2x) {
                    // 还剩下一些 2x
                    cnt.put(x * 2, cnt2x - cntX);
                    x *= 2;
                } else {
                    // 2x 配对完成
                    done.add(x * 2);
                    x *= 4;
                }
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        int[] ans = new int[changed.length / 2];
        int ansIdx = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int x : changed) {
            if (!q.isEmpty()) {
                if (q.peek() < x) { // 无法配对
                    return new int[0];
                }
                if (q.peek() == x) { // 配对成功
                    q.poll(); // 清除一个标记
                    continue;
                }
            }
            if (ansIdx == ans.length) {
                return new int[0];
            }
            ans[ansIdx++] = x;
            q.offer(x * 2); // 添加双倍标记
        }
        return ans;
    }
}
*/
