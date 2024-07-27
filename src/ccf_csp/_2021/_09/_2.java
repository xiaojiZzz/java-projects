package ccf_csp._2021._09;

import java.util.Scanner;

public class _2 {
    //差分+前缀和
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n + 1];
        int[] tmps = new int[500019];
        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            nums[i] = scanner.nextInt();
            max = Math.max(max, nums[i]);
            if (nums[i] > nums[i - 1]) {
                tmps[nums[i - 1] + 1]++;
                tmps[nums[i] + 1]--;
            }
        }
        int tmp = 0, ans = 0;
        for (int i = 1; i < max + 1; i++) {
            tmp += tmps[i];
            ans = Math.max(tmp, ans);
        }
        System.out.println(ans);
    }
}

/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n + 2];
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            nums[i] = scanner.nextInt();
            if (nums[i] != 0) {
                set.add(nums[i]);
                map.put(nums[i], map.getOrDefault(nums[i], new ArrayList<>()));
                ArrayList<Integer> arrayList = map.get(nums[i]);
                arrayList.add(i);
            }
        }
        set.remove(0);
        int nowTimes = 0;
        for (int i = 1; i < n + 1; i++) {
            if (nums[i] > 0 && nums[i - 1] == 0) {
                nowTimes++;
            }
        }
        int tmp = nowTimes, max = nowTimes;
        for (Integer integer : set) {
            ArrayList<Integer> arrayList = map.get(integer);
            for (int i = 0; i < arrayList.size(); i++) {
                int idx = arrayList.get(i);
                nums[idx] = 0;
                if ((nums[idx - 1] != 0) && (nums[idx + 1] != 0)) {
                    tmp++;
                }
                if ((nums[idx - 1] == 0) && (nums[idx + 1] == 0)) {
                    tmp--;
                }
            }
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }
}
 */

/*
import java.util.Scanner;

public class Main {
    //运行超时，暴力，70
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int max = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            max = Math.max(max, nums[i]);
        }
        for (int i = 1; i <= max; i++) {
            int maxCnt = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] < i) {
                    nums[j] = 0;
                }
            }
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (nums[j] == 0) {
                    if (flag) {
                        maxCnt++;
                        flag = false;
                    }
                } else {
                    flag = true;
                }
                if (j == n - 1 && nums[j] != 0) {
                    maxCnt++;
                }
            }
            res = Math.max(res, maxCnt);
        }
        System.out.println(res);
    }
}
*/
