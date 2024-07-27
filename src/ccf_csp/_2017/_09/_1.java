package ccf_csp._2017._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int cnt = 0;
        N /= 10;
        cnt += N / 5 * 7;
        N %= 5;
        cnt += N / 3 * 4;
        N %= 3;
        cnt += N;
        System.out.println(cnt);
    }
}
