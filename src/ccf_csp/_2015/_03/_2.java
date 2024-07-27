package ccf_csp._2015._03;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        int[] key = new int[map.size()];
        int[] temp = new int[map.size()];
        int[] value = new int[map.size()];
        boolean[] visit = new boolean[map.size()];
        int[][] res = new int[map.size()][2];
        Set<Integer> keySet = map.keySet();
        int index = 0;
        for (Integer integer : keySet) {
            key[index] = integer;
            value[index] = map.get(integer);
            temp[index] = map.get(integer);
            index++;
        }
        Arrays.sort(temp);
        int id = 0;
        for (int i = map.size() - 1; i >= 0; i--) {
            for (int j = 0; j < map.size(); j++) {
                if (value[j] == temp[i] && !visit[j]) {
                    res[id][0] = key[j];
                    res[id][1] = value[j];
                    visit[j] = true;
                    id++;
                }
            }
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(res[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/*
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    private static class Node implements Comparable<Node> {
        int val, num;//表示val数字出现了num次

        Node(int val, int num) {
            this.val = val;
            this.num = num;
        }

        @Override
        public int compareTo(Node h) {
            if (num > h.num || (num == h.num && val < h.val))
                return -1;
            else if (num == h.num && val == h.val)
                return 0;
            else
                return 1;
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
        for (int i = 1; i <= n; i++) {
            int x = console.nextInt();
            if (m.containsKey(x)) {//如果已经出现了x，则使x出现的数量加一
                m.put(x, m.get(x) + 1);
            } else//如果还没出现x，则x出现的数量为1
                m.put(x, 1);
        }
        Node[] node = new Node[m.size()];
        //遍历m，将m中的键值对放在node中
        int k = 0;
        Iterator<Entry<Integer, Integer>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            node[k++] = new Node(entry.getKey(), entry.getValue());
        }
        //排序
        Arrays.sort(node);
        for (int i = 0; i < k; i++)
            System.out.println(node[i].val + " " + node[i].num);
        console.close();
    }
}
*/
