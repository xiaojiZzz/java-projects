package ccf_csp._2017._03;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += weights[i];
            if (sum >= k) {
                sum = 0;
                cnt++;
            }
        }
        if (sum > 0) {
            cnt++;
        }
        System.out.println(cnt);
    }
}
