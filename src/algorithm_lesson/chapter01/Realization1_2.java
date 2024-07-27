package algorithm_lesson.chapter01;

@SuppressWarnings({"all"})
//字典序问题
public class Realization1_2 {
    public int sequence(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int res = 1;
        if (length == 1) {
            return res + (chars[0] - 'a');
        }
        for (int i = 1; i < length; i++) {
            res += (chars[i] - chars[i - 1]) + (26 - i);
        }
        return res;
    }
}
