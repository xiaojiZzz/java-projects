package algorithm_lesson.chapter04;

import java.util.Arrays;

@SuppressWarnings({"all"})
//算法实现题4-6 最优服务次序问题
public class Realization4_6 {
    //最短服务时间优先
    public static int n = 10;
    public static int[] time = {56, 12, 1, 99, 1000, 234, 33, 55, 99, 812};

    public static void minAvgWaitTime(int n, int[] time) {
        Arrays.sort(time);
        for (int i = 1; i < n; i++) {
            time[i] += time[i - 1];
        }
        double t = time[0];
        for (int i = 1; i < n; i++) {
            t += time[i];
        }
        System.out.println("最短平均等待时间为：" + t / n);
    }

    public static void main(String[] args) {
        minAvgWaitTime(n, time);
    }
}
