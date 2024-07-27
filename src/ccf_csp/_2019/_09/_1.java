package ccf_csp._2019._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), M = scanner.nextInt();
        int[][] apple = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M + 1; j++) {
                apple[i][j] = scanner.nextInt();
            }
        }
        int total = 0;
        int[] rest = new int[N];
        int[] remove = new int[N];
        for (int i = 0; i < N; i++) {
            rest[i] = apple[i][0];
            for (int j = 1; j < M + 1; j++) {
                rest[i] += apple[i][j];
                remove[i] -= apple[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            total += rest[i];
        }
        int id = -1;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (remove[i] > max) {
                max = remove[i];
                id = i + 1;
            }
        }
        System.out.print(total + " ");
        System.out.print(id + " ");
        System.out.print(max);
    }
}
