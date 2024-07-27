package ccf_csp._2020._06;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        Object[][] dots = new Object[n][3];
        int[][] lines = new int[m][3];
        String[] res = new String[m];
        String[] plus = new String[n];
        String[] minus = new String[n];
        for (int i = 0; i < n; i++) {
            dots[i][0] = scanner.nextInt();
            dots[i][1] = scanner.nextInt();
            dots[i][2] = scanner.next();
        }
        for (int i = 0; i < m; i++) {
            lines[i][0] = scanner.nextInt();
            lines[i][1] = scanner.nextInt();
            lines[i][2] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((int) dots[j][0] * lines[i][1] + (int) dots[j][1] * lines[i][2] + lines[i][0] < 0) {
                    minus[j] = (String) dots[j][2];
                } else {
                    plus[j] = (String) dots[j][2];
                }
            }
            String flag1 = "";
            for (int j = 0; j < n; j++) {
                if (plus[j] != null) {
                    flag1 = plus[j];
                    break;
                }
            }
            boolean flag = true;
            for (int j = 1; j < n; j++) {
                if (plus[j] != null) {
                    if (!plus[j].equals(flag1)) {
                        flag = false;
                        res[i] = "No";
                        break;
                    }
                }
            }
            if (flag) {
                res[i] = "Yes";
            } else {
                Arrays.fill(minus, null);
                Arrays.fill(plus, null);
                continue;
            }
            String flag2 = "";
            for (int j = 0; j < n; j++) {
                if (minus[j] != null) {
                    flag2 = minus[j];
                    break;
                }
            }
            flag = true;
            for (int j = 1; j < n; j++) {
                if (minus[j] != null) {
                    if (!minus[j].equals(flag2)) {
                        flag = false;
                        res[i] = "No";
                        break;
                    }
                }
            }
            if (flag) {
                res[i] = "Yes";
            }
            Arrays.fill(minus, null);
            Arrays.fill(plus, null);
        }
        for (int i = 0; i < m; i++) {
            System.out.println(res[i]);
        }
    }
}
