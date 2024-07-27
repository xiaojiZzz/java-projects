package ccf_csp._2023._05;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] table = new String[n];
        Arrays.fill(table, "");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 8; j++) {
                table[i] += scanner.next();
            }
        }
        int[] count = new int[n];
        Arrays.fill(count, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (table[i].equals(table[j])) {
                    count[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(count[i]);
        }
    }
}

/*
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] chess = new String[110];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= 8; ++j) {
                String temp = scanner.next();
                chess[i] = chess[i] + temp;
            }
            int count = 1;
            for (int j = 1; j < i; ++j) {
                if (chess[j].equals(chess[i]))
                    count++;
            }
            System.out.println(count);
        }
        scanner.close();
    }
}
*/
