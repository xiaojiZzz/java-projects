package algorithm_lesson.chapter02;

import java.util.Scanner;

@SuppressWarnings({"all"})
//算法实现题2-1
public class Realization2_1 {
    public static int mode; //众数
    public static int cnt = 0; //重数
    public static int[] a = new int[10086];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        getMode(0, n);
        System.out.println(mode + " " + cnt);
    }

    public static void getMode(int left, int right) {
        int mid = (left + right) / 2;
        int[] p = new int[]{-1};
        int[] q = new int[]{-1};
        split(left, mid, right, p, q);
        if (q[0] - p[0] + 1 > cnt) {
            cnt = q[0] - p[0] + 1;
            mode = a[mid];
        }
        if (p[0] - left > cnt) {
            getMode(left, p[0] - 1);
        }
        if (right - q[0] - 1 > cnt) {
            getMode(q[0] + 1, right);
        }

    }

    public static void split(int left, int mid, int right, int[] p, int[] q) {
        for (int i = left; i < right; i++) {
            if (a[i] == a[mid]) {
                p[0] = i;
                break;
            }
        }
        for (int i = p[0] + 1; i < right; i++) {
            if (a[i] != a[mid]) {
                q[0] = i - 1;
                break;
            }
        }
    }
}
