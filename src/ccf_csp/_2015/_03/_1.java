package ccf_csp._2015._03;

import java.util.*;

//使用scanner.nextLine(); 才不会超出内存限制
public class _1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        short[][] map = new short[n][m];

        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");
            for (int j = 0; j < split.length; ++j) {
                map[i][j] = Short.parseShort(split[j]);
            }
        }

        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                System.out.print(map[i][m - j - 1] + " ");
            }
            System.out.println();
        }
    }
}

