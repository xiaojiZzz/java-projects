package ccf_csp._2020._09;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    private static class Node implements Comparable<Node> {

        int id;
        int distance;

        public Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (distance < o.distance || distance == o.distance && id < o.id) {
                return -1;
            } else if (distance == o.distance && id == o.id) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), X = scanner.nextInt(), Y = scanner.nextInt();
        int[][] places = new int[n][2];
        Node[] distances = new Node[n];
        for (int i = 0; i < n; i++) {
            places[i][0] = scanner.nextInt();
            places[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int dis = (X - places[i][0]) * (X - places[i][0]) + (Y - places[i][1]) * (Y - places[i][1]);
            distances[i] = new Node(i + 1, dis);
        }
        Arrays.sort(distances);
        for (int i = 0; i < 3; i++) {
            System.out.println(distances[i].id);
        }
    }
}
