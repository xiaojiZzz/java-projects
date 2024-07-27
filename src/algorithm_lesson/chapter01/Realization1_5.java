package algorithm_lesson.chapter01;

import java.util.Arrays;

@SuppressWarnings({"all"})
//最大间隙问题
public class Realization1_5 {
    public double maxLen(double[] a) {
        int length = a.length;
        Arrays.sort(a);
        double maxLen = 0;
        for (int i = 1; i < length; i++) {
            maxLen = Math.max(a[i] - a[i-1], maxLen);
        }
        return maxLen;
    }
}
