package ccf_csp._2023._03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _3 {
    static final int MAX_N = 2510;
    static int n, m;
    static int[] dnlist = new int[MAX_N];
    static int[] ans = new int[MAX_N];
    static ArrayList<ArrayList<Pair<Integer, Integer>>> lib = new ArrayList<>();
    static HashMap<Integer, TreeSet<Integer>> ulib = new HashMap<>(); // 键值对：<attname,set<DN>>
    static Stack<BitSet> ss = new Stack<>(); // 存每次运算结果

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            lib.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) { // 建表
            StringTokenizer st = new StringTokenizer(br.readLine());
            int DN = Integer.parseInt(st.nextToken());
            int attnum = Integer.parseInt(st.nextToken());
            dnlist[i] = DN;
            lib.get(i).ensureCapacity(attnum);
            for (int j = 0; j < attnum; j++) {
                int attname = Integer.parseInt(st.nextToken());
                int attv = Integer.parseInt(st.nextToken());
                lib.get(i).add(new Pair<>(attname, attv));
                ulib.computeIfAbsent(attname, k -> new TreeSet<>()).add(i);
            }
            Collections.sort(lib.get(i));
        }
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            char[] str = br.readLine().toCharArray();
            int len = str.length;
            for (int r = len - 1; r >= 0; r--) {
                while (r >= 0 && (str[r] == ')' || str[r] == '(')) r--;
                if (r >= 0 && Character.isDigit(str[r])) {
                    int l = r - 1;
                    while (l >= 0 && (Character.isDigit(str[l]) || str[l] == ':' || str[l] == '~')) l--;
                    base_expr(str, l + 1, r + 1);
                    r = l - 1;
                }
                // 集合的交和并
                if (r >= 0 && str[r] == '&')
                    its();
                else if (r >= 0 && str[r] == '|')
                    uni();
            }
            if (!ss.empty()) {
                int index = 0;
                BitSet ansbs = ss.peek();
                for (i = 1; i <= n; i++)
                    if (ansbs.get(i))
                        ans[index++] = dnlist[i];
                Arrays.sort(ans, 0, index);
                for (i = 0; i < index; i++)
                    System.out.print(ans[i] + " ");
            }
            System.out.println();
            while (!ss.empty()) ss.pop();
        }
    }

    static int mystoi(char[] s, int start, int end) {
        int base = 1, sum = 0;
        for (int i = end - 1; i >= start; i--) {
            sum += base * (s[i] - '0');
            base *= 10;
        }
        return sum;
    }

    static void its() { // 交运算
        BitSet b1 = ss.pop();
        b1.and(ss.pop());
        ss.push(b1);
    }

    static void uni() { // 并运算
        BitSet b1 = ss.pop();
        b1.or(ss.pop());
        ss.push(b1);
    }

    static void asrt(int aname, int aval, char oper) { // 断言与反断言
        BitSet b = new BitSet(MAX_N);
        Pair<Integer, Integer> p = new Pair<>(aname, aval);
        TreeSet<Integer> userSet = ulib.get(aname);

        if (userSet != null) {
            for (int user : userSet) { // 获取有该属性的用户
                if (oper == ':') { // 断言
                    if (Collections.binarySearch(lib.get(user), p) >= 0) {
                        b.set(user);
                    }
                } else { // 反断言
                    if (Collections.binarySearch(lib.get(user), p) < 0) {
                        b.set(user);
                    }
                }
            }
        }
        ss.push(b);
    }

    static void base_expr(char[] expr, int start, int end) { // 解析原子表达式
        int op = start;
        while (op < end && expr[op] != ':' && expr[op] != '~') op++;
        int num1, num2;
        num1 = mystoi(expr, start, op);
        num2 = mystoi(expr, op + 1, end);
        asrt(num1, num2, expr[op]);
    }

    static class Pair<T, U> implements Comparable<Pair<T, U>> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair<T, U> o) {
            int cmp1 = ((Comparable<T>) this.first).compareTo(o.first);
            if (cmp1 != 0) return cmp1;
            return ((Comparable<U>) this.second).compareTo(o.second);
        }
    }
}
