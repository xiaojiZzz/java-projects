package ccf_csp._2021._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), N = scanner.nextInt();
        int[] A = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            A[i + 1] = scanner.nextInt();
        }
        int[] f = new int[N];
        int t = 0, s = 0;
        for (int i = 0; i < N; i++) {
            if (i >= A[s + 1]) {
                if (s < n - 1) {
                    s++;
                }
                if (t < n) {
                    t++;
                }
            }
            f[i] = t;
        }
        int times = 0, index1 = 0, index2 = 0;
        while (index2 < N) {
            while (index2 < N && f[index1] == f[index2]) {
                times++;
                index2++;
            }
            sum += f[index1] * times;
            times = 0;
            index1 = index2;
        }
        System.out.println(sum);
    }
}

/*
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), N = scanner.nextInt();
        int[] A = new int[n + 2];
        A[n + 1] = N;
        for (int i = 0; i < n; i++) {
            A[i + 1] = scanner.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < n + 1; i++) {
            sum += (A[i + 1] - A[i]) * i;
        }
        System.out.println(sum);
    }
}
*/
