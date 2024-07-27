package leetcode.simple;


/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 */

//public class Solution_9 {
//    public boolean isPalindrome(int x) {
//        if (x < 0) {
//            return false;
//        }
//        Integer xx = (Integer) x;
//        char[] xxx = xx.toString().toCharArray();
//        int count = xxx.length;
//        for (int i = 0; i < count / 2; i++) {
//            if (xxx[i] != xxx[count-i-1]) {
//                return false;
//            }
//        }
//        return true;
//    }
//}

public class Solution_9 {
    public boolean isPalindrome(int x) {
        if(x < 0)return false;
        //考虑到一些int的反转值可能超出int范围,因此定义一个long表示反转值
        long res = 0;
        int temp = x;//临时变量
        while(temp != 0){
            res = res * 10 + temp % 10;//每一次获取temp的最后一位和现在res*10相加
            temp /= 10;//temp/10能够去除最后一位，知道temp为0时，代表取到了第一位数 结束循环
        }
        return res == x;//判断两数是否相等
    }
}

