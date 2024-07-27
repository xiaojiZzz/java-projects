package leetcode.simple;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 */

//public class Solution_14 {
//    public String longestCommonPrefix(String[] strs) {
//        int length = strs.length;
//        int str1Length = strs[0].length();
//        boolean flag = true;
//        int len = 0;
//        if (strs == null || strs.length == 0) {
//            return "";
//        }
//        for (int i = 1; i < length; i++) { //取最小长度
//            if (str1Length > strs[i].length()) {
//                str1Length = strs[i].length();
//            }
//        }
//        for (int i = 0; i < str1Length; i++) {
//            for (int j = 1; j < length; j++) {
//                char a = strs[0].charAt(i);
//                if (a == strs[j].charAt(i)) {
//                    flag = true;
//                } else {
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag == false) {
//                break;
//            } else {
//                len++;
//            }
//        }
//        if (flag == false && len == 0) {
//            return "";
//        } else {
//            return strs[0].substring(0, len);
//        }
//    }
//}

//public class Solution_14 {
//    public String longestCommonPrefix(String[] strs) {
//        if (strs == null || strs.length == 0) {
//            return "";
//        }
//        String prefix = strs[0];
//        int count = strs.length;
//        for (int i = 1; i < count; i++) {
//            prefix = longestCommonPrefix(prefix, strs[i]);
//            if (prefix.length() == 0) {
//                break;
//            }
//        }
//        return prefix;
//    }
//
//    public String longestCommonPrefix(String str1, String str2) {
//        int length = Math.min(str1.length(), str2.length());
//        int index = 0;
//        while (index < length && str1.charAt(index) == str2.charAt(index)) {
//            index++;
//        }
//        return str1.substring(0, index);
//    }
//}

//public class Solution_14 { //分治法
//    public String longestCommonPrefix(String[] strs) {
//        if (strs == null || strs.length == 0) {
//            return "";
//        } else {
//            return longestCommonPrefix(strs, 0, strs.length - 1);
//        }
//    }
//
//    public String longestCommonPrefix(String[] strs, int start, int end) {
//        if (start == end) {
//            return strs[start];
//        } else {
//            int mid = (end - start) / 2 + start;
//            String lcpLeft = longestCommonPrefix(strs, start, mid);
//            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
//            return commonPrefix(lcpLeft, lcpRight);
//        }
//    }
//
//    public String commonPrefix(String lcpLeft, String lcpRight) {
//        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
//        for (int i = 0; i < minLength; i++) {
//            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
//                return lcpLeft.substring(0, i);
//            }
//        }
//        return lcpLeft.substring(0, minLength);
//    }
//}

/*
显然，最长公共前缀的长度不会超过字符串数组中的最短字符串的长度。用 minLength
表示字符串数组中的最短字符串的长度，则可以在 [0,minLength]的范围内通过二分查找得到最长公共前缀的长度。
每次取查找范围的中间值 mid, 判断每个字符串的长度为 mid 的前缀是否相同，
如果相同则最长公共前缀的长度一定大于或等于 mid，如果不相同则最长公共前缀的长度一定小于 mid，
通过上述方式将查找范围缩小一半，直到得到最长公共前缀的长度。
*/
public class Solution_14 { //二分查找
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minlength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minlength = Math.min(minlength, strs[i].length());
        }
        int low = 0, high = minlength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}