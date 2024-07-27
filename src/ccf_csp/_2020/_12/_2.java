package ccf_csp._2020._12;

import java.util.Arrays;
import java.util.Scanner;

public class _2 {
    private static class Node implements Comparable<Node> {
        int val;
        int num;

        Node(int val, int num) {
            this.val = val;
            this.num = num;
        }

        @Override
        public int compareTo(Node h) {
            if (val < h.val)
                return -1;
            else if (val == h.val)
                return 0;
            else
                return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        Node[] nodes = new Node[m];
        for (int i = 0; i < m; i++) {
            nodes[i] = new Node(scanner.nextInt(), scanner.nextInt());
        }
        Arrays.sort(nodes);
        int[] nums = new int[m];
        int num1 = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (nodes[i].num == 1) {
                num1++;
                nums[i] += num1;
            }
        }
        int num0 = 0;
        if (nodes[0].num == 0) {
            num0++;
        }
        for (int i = 1; i < m; i++) {
            if (nodes[i].val > nodes[i - 1].val) {
                nums[i] += num0;
            }
            if (nodes[i].num == 0) {
                num0++;
            }
        }
        int max = 0;
        int idx = -1;
        for (int i = 0; i < m; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                idx = nodes[i].val;
            }
        }
        System.out.println(idx);
    }
}

/*
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    //超时，70
    private static class Node implements Comparable<Node> {
        int val, num;//表示val数字出现了num次

        Node(int val, int num) {
            this.val = val;
            this.num = num;
        }

        @Override
        public int compareTo(Node h) {
            if (num > h.num || (num == h.num && val > h.val))
                return -1;
            else if (num == h.num && val == h.val)
                return 0;
            else
                return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[][] A = new int[m][2];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            A[i][0] = scanner.nextInt();
            A[i][1] = scanner.nextInt();
            set.add(A[i][0]);
        }
        Node[] nodes = new Node[set.size()];
        int idx = 0;
        for (Integer integer : set) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if ((A[i][0] < integer && A[i][1] == 0) || (A[i][0] >= integer && A[i][1] == 1)) {
                    cnt++;
                }
            }
            nodes[idx++] = new Node(integer, cnt);
        }
        Arrays.sort(nodes);
        System.out.println(nodes[0].val);
    }
}
*/
