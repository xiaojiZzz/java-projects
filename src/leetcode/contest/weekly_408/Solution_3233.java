package leetcode.contest.weekly_408;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计不是特殊数字的数字数量
 * 给你两个 正整数 l 和 r。对于任何数字 x，x 的所有正因数（除了 x 本身）被称为 x 的 真因数。
 * 如果一个数字恰好仅有两个 真因数，则称该数字为 特殊数字。例如：
 * 数字 4 是 特殊数字，因为它的真因数为 1 和 2。
 * 数字 6 不是 特殊数字，因为它的真因数为 1、2 和 3。
 * 返回区间 [l, r] 内 不是 特殊数字 的数字数量。
 * 示例 1：
 * 输入： l = 5, r = 7
 * 输出： 3
 * 解释：
 * 区间 [5, 7] 内不存在特殊数字。
 * 示例 2：
 * 输入： l = 4, r = 16
 * 输出： 11
 * 解释：
 * 区间 [4, 16] 内的特殊数字为 4 和 9。
 * 提示：
 * 1 <= l <= r <= 109
 */
public class Solution_3233 {
    public int nonSpecialCount(int l, int r) {
        int sqrtR = (int) Math.sqrt(r);
        List<Integer> primes = sieveOfEratosthenes(sqrtR);

        int specialCount = 0;
        for (int prime : primes) {
            long square = (long) prime * prime;
            if (square >= l) {
                specialCount++;
            }
        }

        return r - l + 1 - specialCount;
    }

    // 埃氏筛
    private List<Integer> sieveOfEratosthenes(int max) {
        boolean[] isPrime = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}

/*
class Solution {
    private static final int MX = 31622;
    private static final int[] PI = new int[MX + 1];

    static {
        for (int i = 2; i <= MX; i++) {
            if (PI[i] == 0) { // i 是质数
                PI[i] = PI[i - 1] + 1;
                for (int j = i * i; j <= MX; j += i) {
                    PI[j] = -1; // 标记 i 的倍数为合数
                }
            } else {
                PI[i] = PI[i - 1];
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        return r - l + 1 - (PI[(int) Math.sqrt(r)] - PI[(int) Math.sqrt(l - 1)]);
    }
}
*/
