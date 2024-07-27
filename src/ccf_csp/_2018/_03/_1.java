package ccf_csp._2018._03;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int[] step = new int[30];
        int index = 0;
        while (true) {
            int t = scanner.nextInt();
            step[index++] = t;
            if (t == 0) {
                break;
            }
        }
        score += step[0];
        int times = 1;
        for (int i = 1; i < index; i++) {
            if (step[i - 1] == 1) {
                score += step[i];
            } else if (step[i - 1] == 2 && step[i] == 2) {
                times++;
                score += times * 2;
            } else if (step[i - 1] == 2 && step[i] != 2) {
                times = 1;
                score += step[i];
            }
        }
        System.out.println(score);
    }
}
