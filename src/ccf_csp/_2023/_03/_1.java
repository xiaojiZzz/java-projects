package ccf_csp._2023._03;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            if (x1 >= 0 && y1 >= 0 && x2 <= a && y2 <= b)
                ans += (x2 - x1) * (y2 - y1);
            else if (x1 < 0 && x2 > 0 && y1 < b && y2 > b)
                ans += (b - y1) * x2;
            else if (x1 < a && a < x2 && y1 < b && b < y2)
                ans += (b - y1) * (a - x1);
            else if (x1 < 0 && 0 < x2 && y1 < 0 && 0 < y2)
                ans += x2 * y2;
            else if (x1 < a && a < x2 && y1 < 0 && 0 < y2)
                ans += (a - x1) * y2;
            else if (x1 >= 0 && x2 <= a && y1 < b && y2 > b)
                ans += (x2 - x1) * (b - y1);
            else if (x1 < a && x2 > a && y1 >= 0 && y2 <= b)
                ans += (y2 - y1) * (a - x1);
            else if (x1 >= 0 && x2 <= a && y1 < 0 && y2 > 0)
                ans += (x2 - x1) * y2;
            else if (y1 >= 0 && y2 <= b && x1 < 0 && x2 > 0)
                ans += (y2 - y1) * x2;
            else if (x1 < 0 && x2 > a && y1 < 0 && y2 > b)
                ans += a * b;
            else if (x1 < 0 && x2 > a && y1 < 0 && y2 > 0 && y2 < b)
                ans += a * y2;
            else if (x1 < 0 && x2 > a && y1 > 0 && y2 < b)
                ans += a * (y2 - y1);
            else if (x1 < 0 && x2 > a && y1 > 0 && y1 < b && y2 > b)
                ans += a * (b - y1);
            else if (y1 < 0 && y2 > b && x1 < 0 && x2 > 0 && x2 < a)
                ans += b * x2;
            else if (y1 < 0 && y2 > b && x1 > 0 && x2 < a)
                ans += b * (x2 - x1);
            else if (y1 < 0 && y2 > b && x1 > 0 && x1 < a && x2 > a)
                ans += b * (a - x1);
        }
        System.out.println(ans);
    }
}
