package ccf_csp._2020._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] w = new int[n];
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            score[i] = scanner.nextInt();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += w[i] * score[i];
        }
        if (result < 0) {
            result = 0;
        }
        System.out.println(result);
    }
}
