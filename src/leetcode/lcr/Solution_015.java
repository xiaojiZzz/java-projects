package leetcode.lcr;

import java.util.ArrayList;
import java.util.List;


/**
 * 同leetcode 438.找到字符串中所有字母异位词
 */
public class Solution_015 {
    //滑动窗口+双指针
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) return res;

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];

        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < n; right++) {
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            while (sCnt[curRight] > pCnt[curRight]) {
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }
            if (right - left + 1 == m) {
                res.add(left);
            }
        }
        return res;
    }
}
