package ccf_csp._2014._03;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), M = scanner.nextInt();
        int[][] windows = new int[N][4];
        TreeMap<Integer, int[]> treeMap = new TreeMap<>();
        HashMap<int[], Integer> index = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                windows[i][j] = scanner.nextInt();
            }
            treeMap.put(i, windows[i]);
            index.put(windows[i], i + 1);
        }
        int[][] points = new int[M][2];
        for (int i = 0; i < M; i++) {
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int x = points[i][0];
            int y = points[i][1];
            boolean ignore = false;
            for (int j = N - 1; j >= 0; j--) {
                int[] ints = treeMap.get(j);
                if (x >= ints[0] && x <= ints[2] && y >= ints[1] && y <= ints[3]) {
                    System.out.println(index.get(ints));
                    ignore = true;
                    for (int k = j + 1; k < N; k++) {
                        treeMap.put(k - 1, treeMap.get(k));
                    }
                    treeMap.put(N - 1, ints);
                    break;
                }
            }
            if (!ignore) {
                System.out.println("IGNORED");
            }
        }
    }
}
