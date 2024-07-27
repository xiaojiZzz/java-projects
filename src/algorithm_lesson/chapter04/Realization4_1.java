package algorithm_lesson.chapter04;

import java.util.Arrays;

@SuppressWarnings({"all"})
//算法实现题4-1 会场安排问题
public class Realization4_1 {
//    int main() {
//        int n, i, Begins[10002], Ends[10002];	// Begins开始时间, Ends结束时间
//        cin >> n;
//        for (i = 0; i < n; i++)
//            cin >> Begins[i] >> Ends[i];
//        sort(Begins, Begins + n);		// 升序排序
//        sort(Ends, Ends + n);			// 升序排序
//        int j = 0, ans = 0;
//        for (i = 0; i < n; i++)
//            if (Begins[i] < Ends[j])	// 如果开始时间小于结束时间, 会场数加1
//                ans++;
//            else						// 开始时间大于等于结束时间, 会场不用增加, 换下一家活动
//                j++;
//        cout << ans << endl;
//        return 0;
//    }
    public int minHall(int n, int[] begins, int[] ends) { //n为活动数，begins为活动开始时间，ends为活动结束时间
        Arrays.sort(begins); //升序排列
        Arrays.sort(ends); //升序排列
        int j = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (begins[i] < ends[j]) { //如果开始时间小于结束时间, 会场数加1
                ans++;
            } else { //开始时间大于等于结束时间, 会场不用增加, 换下一家活动
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] begin = {1, 12, 25, 27,36};
        int[] end = {23, 28, 35, 80, 50};
        int i = new Realization4_1().minHall(5, begin, end);
        System.out.println(i);
    }
}
