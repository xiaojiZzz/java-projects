package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//算法实现题2-5
public class Realization2_5 {
    public static int n = 4;
    public static char[] chars = "aacc".toCharArray();
    public static int num = 0;

    public static void main(String[] args) {
        perm(chars, 0, n);
        System.out.println(num);
    }

    public static void perm(char[] chars, int begin, int end) {
        if (begin == end) {
            num++;
            for (int i = 0; i < end; i++) {
                System.out.print(chars[i]);
            }
            System.out.println();
        } else {
            for (int i = begin; i < end; i++) {
                if (isSwap(chars, begin, i)) {
                    swap(chars, begin, i);
                    perm(chars, begin + 1, end);
                    swap(chars, begin, i);
                }
            }
        }
    }

    public static boolean isSwap(char[] chars, int begin, int p) {
        for (int i = begin; i < p; i++) {
            if (chars[p] == chars[i]) {
                return false;
            }
        }
        return true;
    }

    public static void swap(char[] chars, int begin, int i) {
        char temp;
        temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;
    }
}