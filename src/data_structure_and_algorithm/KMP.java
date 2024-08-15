package data_structure_and_algorithm;

// KMP 算法，用于字符串的模式匹配，即在一个文本字符串中查找一个模式字符串的高效字符串匹配算法
// 时间复杂度为 O(m + n) 其中 m 为模式字符串的长度，n 是文本字符串的长度
public class KMP {
    // str1 为文本字符串， str2 为模式字符串，返回在文本字符串中第一个匹配模式字符串的下标
    // 规定：str1.length() >= str2.length() >= 1
    public int getFirstIndex(String str1, String str2) {
        int[] next = getNext(str2);
        int n = str1.length(), m = str2.length();
        int i = 0, j = 0;
        while (i < n) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
            if (j == m) {
                return i - j;
            }
        }
        // 未找到返回 -1
        return -1;
    }

    // 获取 next 数组
    public int[] getNext(String str2) {
        int m = str2.length();
        int[] next = new int[m];
        next[0] = 0;
        int i = 1, j = 0;
        while (i < m) {
            if (str2.charAt(i) == str2.charAt(j)) {
                j++;
                next[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }

    /*
    模版 2
    public int getFirstIndex(String str1, String str2) {
        int[] next = getNext(str2);
        int n = str1.length(), m = str2.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        // 未找到返回 -1
        return -1;
    }

    // 获取 next 数组
    public int[] getNext(String str2) {
        int m = str2.length();
        int[] next = new int[m];
        next[0] = 0;
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && str2.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str2.charAt(i) == str2.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    */
}
