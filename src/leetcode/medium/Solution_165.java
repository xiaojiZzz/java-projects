package leetcode.medium;


/**
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 * 返回规则如下：
 * 如果 version1 < version2 返回 -1，
 * 如果 version1 > version2 返回 1，
 * 除此之外返回 0。
 * 示例 1：
 * 输入：version1 = "1.2", version2 = "1.10"
 * 输出：-1
 * 解释：
 * version1 的第二个修订号为 "2"，version2 的第二个修订号为 "10"：2 < 10，所以 version1 < version2。
 * 示例 2：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：
 * 忽略前导零，"01" 和 "001" 都代表相同的整数 "1"。
 * 示例 3：
 * 输入：version1 = "1.0", version2 = "1.0.0.0"
 * 输出：0
 * 解释：
 * version1 有更少的修订号，每个缺失的修订号按 "0" 处理。
 */
public class Solution_165 {
    public int compareVersion(String version1, String version2) {
        // 注意使用转义字符
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int m = split1.length, n = split2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            String s1 = split1[i];
            String s2 = split2[j];
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            if (i1 < i2) {
                return -1;
            } else if (i1 > i2) {
                return 1;
            }
            i++;
            j++;
        }
        while (i < m) {
            if (Integer.parseInt(split1[i++]) != 0) {
                return 1;
            }
        }
        while (j < n) {
            if (Integer.parseInt(split2[j++]) != 0) {
                return -1;
            }
        }
        return 0;
    }
}

/*
class Solution {
    public int compareVersion(String v1, String v2) {
        String[] ss1 = v1.split("\\."), ss2 = v2.split("\\.");
        int n = ss1.length, m = ss2.length;
        int i = 0, j = 0;
        while (i < n || j < m) {
            int a = 0, b = 0;
            if (i < n) a = Integer.parseInt(ss1[i++]);
            if (j < m) b = Integer.parseInt(ss2[j++]);
            if (a != b) return a > b ? 1 : -1;
        }
        return 0;
    }
}
*/
