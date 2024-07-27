package algorithm_lesson.chapter04;

import java.util.Arrays;

@SuppressWarnings({"all"})
//算法实现题4-5 程序存储问题
public class Realization4_5 {
    public static int l = 50;
    public static int n = 6;
    public static int[] len = {2, 3, 13, 8, 80, 20};

    public static void maxProgram(int n, int l, int[] len) {
        Arrays.sort(len);
        int max = 0;
        for (int i = 0; i < len.length; i++) {
            if (len[i] <= l) {
                max++;
                l -= len[i];
            } else {
                break;
            }
        }
        System.out.println("最多程序为：" + max);
    }

    public static void main(String[] args) {
        maxProgram(n, l, len);
    }
}
