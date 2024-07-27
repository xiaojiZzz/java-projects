package ccf_csp._2021._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            B[i] = scanner.nextInt();
        }
        int min = B[0], max = B[0];
        for (int i = 1; i < n; i++) {
            if (B[i] > B[i - 1]) {
                min += B[i];
            }
            max += B[i];
        }
        System.out.println(max);
        System.out.println(min);
    }
}
