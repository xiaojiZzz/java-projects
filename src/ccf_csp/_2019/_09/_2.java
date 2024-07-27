package ccf_csp._2019._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] apples = new int[N][1001];
        int T = 0, D = 0, E = 0;
        boolean flag = false;
        boolean flagS1 = false; //第一个发生掉落
        boolean flagS2 = false; //第二个发生掉落
        boolean flagE1 = false; //最后一个发生掉落
        boolean flagE2 = false; //倒数第二个发生掉落
        int appleCnt = 0;
        int cycle = 0;
        for (int i = 0; i < N; i++) {
            apples[i][0] = scanner.nextInt();
            appleCnt = 0;
            for (int j = 1; j <= apples[i][0]; j++) {
                apples[i][j] = scanner.nextInt();
                if (apples[i][j] > 0 && apples[i][j - 1] <= 0) {
                    if (appleCnt > apples[i][j]) {
                        if (!flag) {
                            cycle++;
                            D++;
                            if (i == 0) {
                                flagS1 = true;
                            }
                            if (i == 1) {
                                flagS2 = true;
                            }
                            if (i == N - 1) {
                                flagE1 = true;
                            }
                            if (i == N - 2) {
                                flagE2 = true;
                            }
                            flag = true;
                        }
                    }
                    appleCnt = apples[i][j];
                    continue;
                } else if (apples[i][j] > 0 && apples[i][j - 1] > 0) {
                    if (appleCnt > apples[i][j]) {
                        if (!flag) {
                            cycle++;
                            D++;
                            if (i == 0) {
                                flagS1 = true;
                            }
                            if (i == 1) {
                                flagS2 = true;
                            }
                            if (i == N - 1) {
                                flagE1 = true;
                            }
                            if (i == N - 2) {
                                flagE2 = true;
                            }
                            flag = true;
                        }
                    }
                    appleCnt = apples[i][j];
                    continue;
                }
                appleCnt += apples[i][j];
            }
            if (!flag) {
                cycle = 0;
            }
            flag = false;
            T += appleCnt;
            if (cycle == 3) {
                E++;
                cycle--;
            }
        }
        if (flagE2 && flagE1 && flagS1) {
            E++;
        }
        if (flagE1 && flagS2 && flagS1) {
            E++;
        }
        System.out.println(T + " " + D + " " + E);
    }
}
