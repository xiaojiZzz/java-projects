package leetcode.medium;

import java.util.HashSet;
import java.util.Set;


/**
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。
 * 采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 * 示例 1：
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 */
public class Solution_904 {
    public int totalFruit(int[] fruits) {
        int ans = 0;
        int left = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < fruits.length; i++) {
            if (set.size() == 0 || set.size() == 1) {
                set.add(fruits[i]);
                ans = Math.max(ans, i - left + 1);
            } else {
                if (set.contains(fruits[i])) {
                    ans = Math.max(ans, i - left + 1);
                    continue;
                } else {
                    set.clear();
                    set.add(fruits[i]);
                    for (int j = i - 1; j >= left; j--) {
                        if (fruits[j] != fruits[i - 1]) {
                            left = j + 1;
                            set.add(fruits[left]);
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int totalFruit(int[] fs) {
        int n = fs.length, ans = 0;
        int[] cnts = new int[n];
        for (int i = 0, j = 0, tot = 0; i < n; i++) {
            if (++cnts[fs[i]] == 1)
                tot++;
            while (tot > 2) {
                if (--cnts[fs[j++]] == 0)
                    tot--;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
*/
