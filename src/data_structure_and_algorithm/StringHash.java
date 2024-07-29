package data_structure_and_algorithm;

import java.util.HashSet;
import java.util.Set;


// 字符串 hash，用于快速查找字符串
// leetcode 187、1044
public class StringHash {

    private String str;
    // 采用 int 类型的数组，溢出不是问题，但如果溢出是倍数关系，会发生 hash 冲突，可以考虑用 long 来代替 int
    private int[] p;
    private int[] h;

    public StringHash(String str) {
        this.str = str;
        int n = str.length();
        p = new int[n + 1];
        h = new int[n + 1];
        p[0] = 1;
        int P = 1313131; // wa 出来的，如果出现了 hash 冲突，可以换更大的数（最好是更大的质数）
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + str.charAt(i);
        }
    }

    public boolean stringHash() {
        // 如果求 str 中子串是否重复出现，可以使用字符串 hash
        // 假设 子串长度为 len = 2
        int len = 2;
        int n = str.length();
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(hash)) {
                return true;
            }
            set.add(hash);
        }
        return false;
    }
}
