package ccf_csp._2019._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] location = new int[5];
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            int x = nums[i][0], y = nums[i][1];
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                int x1 = nums[j][0], y1 = nums[j][1];
                if (x == x1 && y == y1 - 1) {
                    break;
                }
                if (j == n - 1) {
                    flag = false;
                }
            }
            if (flag) {
                for (int j = 0; j < n; j++) {
                    int x2 = nums[j][0], y2 = nums[j][1];
                    if (x == x2 && y == y2 + 1) {
                        break;
                    }
                    if (j == n - 1) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int j = 0; j < n; j++) {
                    int x3 = nums[j][0], y3 = nums[j][1];
                    if (x == x3 - 1 && y == y3) {
                        break;
                    }
                    if (j == n - 1) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int j = 0; j < n; j++) {
                    int x4 = nums[j][0], y4 = nums[j][1];
                    if (x == x4 + 1 && y == y4) {
                        break;
                    }
                    if (j == n - 1) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int j = 0; j < n; j++) {
                    int x1 = nums[j][0], y1 = nums[j][1];
                    if (x == x1 - 1 && y == y1 - 1) {
                        cnt++;
                    }
                }
                for (int j = 0; j < n; j++) {
                    int x1 = nums[j][0], y1 = nums[j][1];
                    if (x == x1 - 1 && y == y1 + 1) {
                        cnt++;
                    }
                }
                for (int j = 0; j < n; j++) {
                    int x1 = nums[j][0], y1 = nums[j][1];
                    if (x == x1 + 1 && y == y1 - 1) {
                        cnt++;
                    }
                }
                for (int j = 0; j < n; j++) {
                    int x1 = nums[j][0], y1 = nums[j][1];
                    if (x == x1 + 1 && y == y1 + 1) {
                        cnt++;
                    }
                }
                location[cnt]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(location[i]);
        }
    }
}
