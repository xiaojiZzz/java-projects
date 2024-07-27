package algorithm_lesson.chapter04;

import java.util.Arrays;

@SuppressWarnings({"all"})
//算法实现题4-7 多处最优服务次序问题
public class Realization4_7 {
    public static int n = 10;
    public static int s = 2;
    public static int[] time = {56, 12, 1, 99, 1000, 234, 33, 55, 99, 812};

    public static void minAvgWaitTime(int n, int s, int[] time) {
        Arrays.sort(time);
        int j = 0; //j为1， 2，分别在两个服务点服务
        int[] se = new int[s]; //服务结束时间
        int[] sc = new int[s]; //等待时间
        for (int i = 0; i < n; i++) {
            se[j] += time[i];
            sc[j] += se[j];
            j++;
            if (j == s) {
                j = 0;
            }
        }
        double sum = 0;
        for (int i = 0; i < s; i++) {
            sum += sc[i];
        }
        System.out.println("最短平均等待时间为：" + sum / n);
    }

    public static void main(String[] args) {
        minAvgWaitTime(n, s, time);
    }
}
