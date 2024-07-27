package ccf_csp._2020._06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] strings = s.trim().split(" ");
        int n = Integer.valueOf(strings[0]);
        int a = Integer.valueOf(strings[1]);
        int b = Integer.valueOf(strings[2]);
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a; i++) {
            String line = br.readLine();
            String[] strings1 = line.trim().split(" ");
            int index = Integer.valueOf(strings1[0]);
            int value = Integer.valueOf(strings1[1]);
            map.put(index, value);
        }
        for (int i = 0; i < b; i++) {
            String line = br.readLine();
            String[] strings2 = line.trim().split(" ");
            int index = Integer.valueOf(strings2[0]);
            int value = Integer.valueOf(strings2[1]);
            if (map.containsKey(index)) {
                res += map.get(index) * value;
            }
        }
        System.out.println(res);
    }
}
