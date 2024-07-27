package ccf_csp._2023._12;

import java.util.Arrays;
import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        long[][] arr = new long[q][2];
        for (int i = 0; i < q; i++) {
            arr[i][0] = scanner.nextLong();
            arr[i][1] = scanner.nextLong();
        }
        long[] ans = new long[q];
        Arrays.fill(ans, 1);
        for (int i = 0; i < q; i++) {
            long n = arr[i][0], k = arr[i][1];
            for (int j = 2; j <= 100000; j++) {
                if (isPrime(j)) {
                    int times = 0;
                    while (n % j == 0) {
                        times++;
                        n /= j;
                    }
                    if (times >= k) {
                        ans[i] *= Math.pow(j, times);
                    }
                }
            }
        }
        for (int i = 0; i < q; i++) {
            System.out.println(ans[i]);
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
