package ccf_csp._2015._12;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] res = new int[n][m];
        int[][] tmp = new int[n][m];
        int[][] tmp2 = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = res[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp2[i][j] = res[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = find(tmp[i]);
            Set<Integer> set = map.keySet();
            for (Integer integer : set) {
                int len = map.get(integer);
                for (int j = integer; j < integer + len; j++) {
                    res[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            HashMap<Integer, Integer> map = find(tmp2[i]);
            Set<Integer> set = map.keySet();
            for (Integer integer : set) {
                int len = map.get(integer);
                for (int j = integer; j < integer + len; j++) {
                    res[j][i] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(res[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static HashMap<Integer, Integer> find(int[] arr) {
        int cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 1;
        for (; right < arr.length; right++) {
            if (arr[left] != arr[right]) {
                cnt = right - left;
                if (cnt >= 3) {
                    map.put(left, cnt);
                }
                left = right;
                cnt = 0;
            }
            if (right == arr.length - 1) {
                cnt = right - left + 1;
                if (cnt >= 3) {
                    map.put(left, cnt);
                }
            }
        }
        return map;
    }
}
